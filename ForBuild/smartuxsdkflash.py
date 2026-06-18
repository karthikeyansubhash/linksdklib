#!/usr/bin/env python2

__author__ = 's.starchenko'
"""       smart-ux-sdk-flash:

             smartuxsdkflash.py

Environment to download and flash one rome fro provided device.

March 31, 2015

Copyright (c) 2015 by Samsung Electronics, Inc All rights reserved.

This software is the confidential and proprietary information of Samsung
Electronics, Inc. ("Confidential Information"). You shall not disclose such
Confidential Information and shall use it only in accordance with the terms
of the license agreement you entered into with Samsung.
"""

# Note: selenium is used because of webdriver
import sys
import getopt
import os
import time
import ftputil
import module_locator

from selenium import webdriver
from selenium.webdriver.firefox.firefox_binary import FirefoxBinary
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait # available since 2.4.0
from selenium.webdriver.support import expected_conditions as ExpectedConditions # available since 2.26.0
from selenium.common.exceptions import TimeoutException, NoSuchElementException


def print_usage_info():
    """Prints usage info"""
    print "Usage of the SmartUxSdkFlash:\n\
    v. 0.0.1 2015(c) by Samsung Electronics, Inc All rights reserved.\n\
    Helper script for downloading and flashing latest rom from predefined location to provided device.\n\
    command line: smartuxsdkflash.py [--help] --ip=ip_of_device --model=M4580 --branch=MX4RC2\n\
    \n\
    --ip      -i   Ip of device to flash to\n\
    --model   -m   Devices model\n\
    --branch  -b   Model branch to flash from\
    --help    -h   Display this help\n\
    \n\
    Example:\n\
    > smartuxsdkflash.py --ip=10.88.197.251 --model=M4580 --branch=MX4RC2\n\
    "


def spinning_cursor(range_val):
    """ Helps to show spinning cursor"""
    while True:
        for cursor in range(range_val):
            if cursor % 70 == 0:
                yield '\n.'
            else:
                yield '.'


def counted(fn):
    def wrapper(*args, **kwargs):
        wrapper.called += 1
        return fn(*args, **kwargs)

    wrapper.called = 0
    wrapper.__name__ = fn.__name__
    return wrapper


@counted
def ftp_download_dummy_callback(chunk):
    if ftp_download_dummy_callback.called % 70 == 0:
        next_sym = '.\n'
    else:
        next_sym = '.'
    print next_sym,


def remove_all_files_with_ext(path):
    """ Removes all files with hds extension from the path """
    for root, dirs, files in os.walk(path):
        for currentFile in files:
            exts = ['.hds']
            if any(currentFile.lower().endswith(ext) for ext in exts):
                print 'remove old onerom', currentFile
                os.remove(os.path.join(root, currentFile))


def download_onerom(my_path, arg_model, arg_branch):
    """
    Downloads onerom file for specified model and branch. Onerom get saved in my_path
    :param my_path: path to save onerom to (ending with '/')
    :param arg_model: model to download onerom for
    :param arg_branch: branch to download onerom for
    :return: path of downloaded onerom
    """
    # Compose ftp path
    print "Connecting to FTP for firmware..."
    ftp_ip = "10.88.194.92"
    ftp_path = "SubFW/" + arg_model + "/TGIF/ONEROM/tgif/"

    onerom_file = None
    onerom_short_name = None

    with ftputil.FTPHost(ftp_ip, "everest", "autobuild") as ftp_host:
        ftp_host.chdir(ftp_path)
        files = ftp_host.listdir(ftp_host.curdir)

        for name in files:
            if ftp_host.path.isfile(name) and name.endswith("hds"):
                # Remote name, local name, binary mode
                onerom_file = my_path + name
                print "Found onerom file", name
                onerom_short_name = name[0:-4]
                # Remove previous files if they exists
                remove_all_files_with_ext(my_path)

                print 'Downloading onerom',
                ftp_host.download(name, onerom_file, callback=ftp_download_dummy_callback)
                print '\n'
                print "Downloaded onerom file to", onerom_file

                print "\nConfiguration:"
                print "[BUILD]", name[:-4]
                break

    return onerom_file, onerom_short_name


def sws_execute_login(browser):
    """
    Executes login to SWS using provided browser
    :param browser: to be used for login
    :return:
    """
    elem = browser.find_element_by_id("GNB_4")
    print "Click Login..."
    elem.click()
    time.sleep(10)

    print "Input credentials"

    # Input login
    browser.switch_to.frame("frame_id_Login")
    elem = browser.find_element_by_id("IDUserId")
    elem.send_keys("admin")
    # Input password
    elem = browser.find_element_by_id("IDUserPw")
    elem.send_keys("admin@12")

    # Press login button
    elem = browser.find_element_by_id("IDLogin")
    print "Click Login..."
    elem.click()
    # Wait for SWS to be update
    time.sleep(20)
    # Switch back tro main frame
    browser.switch_to.default_content()
    print "Logged in"


def main():
    """ Great Main Function """
    # Read input params
    try:
        opts, args = getopt.getopt(sys.argv[1:], "hi:m:b:",
                                   ["help", "ip=", "model=", "branch="])
    except getopt.GetoptError:
        print_usage_info()
        sys.exit(2)

    if os.name == 'nt':
        my_path = module_locator.module_path() + "\\"
    else:
        my_path = module_locator.module_path() + "/"

    arg_ip = None
    arg_model = None
    arg_branch = None

    for opt, arg in opts:
        if opt in ("-i", "--ip"):
            arg_ip = arg
        elif opt in ("-m", "--model"):
            arg_model = arg
        elif opt in ("-b", "--branch"):
            arg_branch = arg
        elif opt in ("-h", "--help"):
            print_usage_info()
            sys.exit(0)

    if arg_ip is None:
        print "Error! --ip argument should be presented!\n"
        print_usage_info()
        sys.exit(1)

    if arg_model is None:
        print "Error! --model argument should be presented!\n"
        print_usage_info()
        sys.exit(1)

    if arg_branch is None:
        print "Error! --branch argument should be presented!\n"
        print_usage_info()
        sys.exit(1)

    onerom_file, onerom_shortname = download_onerom(my_path, arg_model, arg_branch)

    if onerom_file is None or not os.path.isfile(onerom_file):
        print "Failed to obtain onerom file, check model, branch and ability to connect to ftp://10.88.194.92"
        print_usage_info()
        sys.exit(1)

    if os.name == 'nt':
        binary = FirefoxBinary("d:\\tools\\ff\\firefox.exe")
        ff = webdriver.Firefox(firefox_binary=binary)
    else:
        ff = webdriver.Firefox()

    # Load SWS page
    print "Open Browser to execute device flash"
    ff.get("http://" + arg_ip + "/sws/index.sws")
    assert "SyncThru Web Service" in ff.title
    print "Opened SWS with", ff.title, "title"

    sws_execute_login(ff)

    print "Select Maintenance"
    elem = ff.find_element_by_id("sws_topMenuName_Maintenance")
    elem.click()
    time.sleep(15)

    print "Select Application"
    ff.switch_to.frame("ruifw_LeftFrm")
    try:
        elem = ff.find_element_by_id("leftTreeMenu-node-1-3-1")
        elem = elem.find_element_by_class_name("menu_table_tr_td")
    except NoSuchElementException:
        elem = None

    if elem is None or elem.text != 'Application':
        print "Select", elem.text, "which is not Application"
        try:
            elem = ff.find_element_by_id("leftTreeMenu-node-1-2-1")
            elem = elem.find_element_by_class_name("menu_table_tr_td")
        except NoSuchElementException:
            elem = None
        print "Selected again:", elem.text
    if elem is None:
        print "Not able to find Applications element! Exit."
        sys.exit(1)

    elem.click()
    time.sleep(15)

    print "Select Applications -> Add"
    ff.switch_to.default_content()
    ff.switch_to.frame("ruifw_MainFrm")
    elem = ff.find_element(By.ID, "addBtn_2")
    elem.click()
    time.sleep(15)

    print "Input onerom path", onerom_file
    ff.switch_to.default_content()
    ff.switch_to.frame("frame_installFileList")
    elem = ff.find_element(By.ID, "file")
    elem.send_keys(onerom_file)
    time.sleep(2)

    print "Select Add..."
    elem = ff.find_element(By.ID, "okbtn_2")
    elem.click()

    print "Wait for FW validation"
    try:
        WebDriverWait(ff, 10 * 60)\
            .until(ExpectedConditions.presence_of_element_located((By.ID, "subTitle1")))
    except TimeoutException:
        print "FW validation failed!"
        ff.quit()
        print "Critical Error! Not able to flash! Assistance in flash / script correction / proper build are needed"
        sys.exit(1)

    time.sleep(5)

    print "Validation finished!"
    print "Select OK to start flash"
    elem = ff.find_element(By.ID, "okbtn_2")
    elem.click()

    ff.switch_to.default_content()

    print "Wait for flash to finish..."
    try:
        WebDriverWait(ff, 15 * 60)\
            .until(ExpectedConditions.frame_to_be_available_and_switch_to_it((By.ID, "frame_completeAlert")))
    except TimeoutException:
        print "FW validation failed!"
        ff.quit()
        print "Critical Error! Not able to flash! Assistance in flash / script correction / proper build is needed"
        sys.exit(1)

    print "Flash is done!"

    # Wait 10 min for reload and hibernation
    print "Waiting for device to reboot and come back online",

    dots = spinning_cursor(200)

    for _ in range(200):
        print dots.next(),
        time.sleep(3)

    print '\n'
    ff.refresh()

    print "Wait for printer to load..."
    try:
        WebDriverWait(ff, 15 * 60)\
            .until(ExpectedConditions.presence_of_element_located((By.ID, "GNB_4")))
    except TimeoutException:
        print "Looks like printer hasn't been rebooted properly"
        ff.quit()
        print "Critical Error! Not able to detect printer after flash!" \
              " Assistance in flash / script correction / proper build is needed"
        sys.exit(1)

    ff.close()
    print "\n++++++ Flash", onerom_file, "completed successfully ++++++\n\n"


if __name__ == "__main__":
    main()