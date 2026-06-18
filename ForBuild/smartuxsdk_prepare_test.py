#!/usr/bin/env python2
# -*- coding: cp1252 -*-
"""       smart-ux-sdk-prepare-test:

             smartuxsdk_prepare_test.py

Environment to launch, execute and obtain results
of SmartUxSdk test suite.

September 1, 2015

Copyright (c) 2015 by Samsung Electronics, Inc All rights reserved.

This software is the confidential and proprietary information of Samsung
Electronics, Inc. ("Confidential Information"). You shall not disclose such
Confidential Information and shall use it only in accordance with the terms
of the license agreement you entered into with Samsung.
"""
import subprocess
import threading
import sys
import getopt
import platform
import io
import os
import time
import shutil
import os.path
import imp
import module_locator

from datetime import datetime


class TimeoutSubprocess(object):
    """Class to execute some process with timeout"""

    def __init__(self, cmd, repeat=1, delay=0, display_text='', wait=True, out=True):
        """
        Constructor!
        :param cmd: to be executed
        :param repeat: number of repeats
        :param delay: between repeats
        :param display_text: to display after each execution
        :param wait True if we need to wait for timeout in a sync manner
        :param out False if don't need to handle out (to avoid process lock), True by default
        :return: new instance
        """
        self.cmd = cmd
        self.process = None
        self.repeat = repeat
        self.delay = delay
        self.display_text = display_text
        self.wait = wait
        self.out = out
        self.stdout = ''
        self.stderr = ''

    def run(self, timeout=0):
        def target():
            si = None
            if os.name == 'nt':
                # PIPE here is security hole, we need to deliver output somehow ya know
                si = subprocess.STARTUPINFO()
                si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
            for run_count in range(self.repeat):
                if self.out:
                    self.process = subprocess.Popen(self.cmd, stdout=subprocess.PIPE, startupinfo=si)
                    self.stdout, self.stderr = self.process.communicate()
                else:
                    self.process = subprocess.Popen(self.cmd, startupinfo=si)

                if self.delay != 0:
                    if self.out:
                        print self.display_text, self.stdout
                    else:
                        print self.display_text
                    time.sleep(self.delay)

        thread = threading.Thread(target=target)
        thread.start()

        # Simple control to not require user to provide timeout
        if timeout < self.repeat * self.delay:
            timeout = self.repeat * (self.delay + 10)

        if self.wait:
            thread.join(timeout)
            if thread.is_alive():
                if self.process is not None:
                    try:
                        self.process.terminate()
                    except OSError:
                        print "Failed to terminate process, skip"
                thread.join()

        if self.process is not None:
            return self.process.returncode, self.stdout, self.stderr
        else:
            return 1, self.stdout, self.stderr


class StoppableLogThread(threading.Thread):
    """Thread class with a stop() method. The thread itself has to check
    regularly for the stopped() condition."""

    def __init__(self, logfile, adb_path):
        super(StoppableLogThread, self).__init__()
        self._stop = threading.Event()
        self.logfile = logfile
        self.adb_path = adb_path

    def stop(self):
        self._stop.set()

    def stopped(self):
        return self._stop.isSet()

    def run(self):
        si = None
        if os.name == 'nt':
            # PIPE here is security hole, we need to deliver output somehow ya know
            si = subprocess.STARTUPINFO()
            si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
        p = subprocess.Popen([self.adb_path, "logcat", "-v", "time"], universal_newlines=True, stdout=self.logfile,
                             startupinfo=si)
        while p.poll() is None and not self.stopped():
            time.sleep(0.5)
        # Stop logging
        try:
            p.terminate()
        except OSError:
            print "Failed to cancel logging process!"
            sys.exit(1)

        self.logfile.close()


def print_usage_info():
    """Prints usage info"""
    print "Usage of the SmartUxSdkTestSuiteLauncher:\n\
    v. 0.0.1 2015(c) by Samsung Electronics, Inc All rights reserved.\n\
    Helper script for launch of automated integration and verification tests on Android-based OPE.\n\
    command line: smartuxsdktest.py [--help] --apk=path_to_test_apk [--support=path_to_support_apk] --ip=ip_of_device [--out=output_file_name] [--android=path_to_android_tools]\n\
    \n\
    --apk       -a   Full path to SmartUxSdkTestSuite.apk\n\
    --support   -s   Full path to SmartUXLibSupport.apk, can be missed\n\
    --type      -t   Type of tests to run: all, auto, manual\n\
    --class     -c   Class to run, then you don't want to run whole suite (e.g. com.sec.android.smartuxsdktestsuite.tests.copier.CopierServiceTests)\n\
    --ip        -i   Ip of printer to run tests on\n\
    --out       -o   Preferred output file name (.txt will be appended), can be missed\n\
    --setup     -u   Location of special setup files (IpsendLib.py, pcl_noaction.prn, TGIF_UI_Automator_Tests.jar), for same directory use . here or omit the parameter\n\
    --clean     -e   Provide this flag in order to clean up Downloads folder before tests execution, true by default\n\
    --help      -h   Display this help\n\
    \n\
    --android -d   Path to Android SDK ADB tool, if tool is not able to auto-detect one, can be specified only first time \n\
    \n\
    Example:\n\
    > smartuxsdktest.py --apk=SmartUxSdkTestSuite.apk --ip=10.88.192.68"


def check_adb(adb_path):
    """
        Tries path for adb execution,
        returns false if not adb of exception
    """
    exists = False
    si = None
    if os.name == 'nt':
        # PIPE here is security hole, we need to deliver output somehow ya know
        si = subprocess.STARTUPINFO()
        si.dwFlags |= subprocess.STARTF_USESHOWWINDOW
    else:
        WindowsError = None

    try:
        p = subprocess.Popen([adb_path, "version"], stdout=subprocess.PIPE, startupinfo=si)
        p.wait()

        if p.returncode == 0:
            exists = True
    except WindowsError:
        # Nothing to do here
        pass
    except OSError:
        print "Check adb failed!"
        pass

    return exists


def find_adb(adb_path):
    """ try to search adb in common installation paths """
    paths_doze = ["c:\\Program Files (x86)\\Android\\android-sdk\\platform-tools\\adb",
                  "c:\\android\\sdk\\platform-tools\\adb",
                  "d:\\Program Files (x86)\\Android\\android-sdk\\platform-tools\\adb",
                  "d:\\Program Files\\Android\\android-sdk\\platform-tools\\adb",
                  "d:\\tools\\android\\sdk\\platform-tools\\adb", "%HOMEPATH%\\android-sdk\\platform-tools\\adb",
                  "%HOMEPATH%\\android\\platform-tools\\adb"]
    paths_nix = ["/usr/local/android-sdk/platform-tools/adb", "/opt/android-sdk/platform-tools/adb",
                 "/srv/android-sdk/platform-tools/adb", "~/android-sdk-linux/adb"]
    paths = None
    adb_path_out = "adb"

    # detect os and use appropriate paths
    if platform.system() == 'Windows':
        paths = paths_doze
    else:
        paths = paths_nix

    for path in [adb_path + "\platform_tools\adb"] + paths:
        if check_adb(path):
            adb_path = path
            # no further processing is needed, we've found working path, just write it to our file
            props_file = open("local.properties", "w")
            props_file.write(adb_path)
            props_file.close()
            break

    return adb_path


def launch_logging(adb_path, logfile):
    """Launches thread for writing logcat output to some special file."""
    thread = StoppableLogThread(logfile=logfile, adb_path=adb_path)
    thread.start()
    return thread


def set_log_props(adb_path):
    """Sets props via adb to provide more reach logging """
    # Lsit can be updated
    log_tags = ["XAP_LOG_LEVEL_TAG",
                "Joblet",
                "CopyletCP",
                "Copylet",
                "Scanlet",
                "ScanToLet",
                "UpClient",
                "UpClientTS",
                "UpError",
                "ErrorHandler",
                "EventHandler",
                "JobEventHandler",
                "[DEH][JobConfirmIS]"
                "DEH"]

    print "Enable additional logging..."

    for tag in log_tags:
        p = TimeoutSubprocess([adb_path, "shell", "setprop", "log.tag." + tag, "VERBOSE"])
        returncode, stdout, stderr = p.run(timeout=20)
        if returncode == 0:
            print "Enabled", tag, "verbose logging."
        else:
            print "[", returncode, "]: failed to enable VERBOSE logging for", tag

    print "\n"


def clean_download_folder(adb_path):
    """Cleans /sdcard/Download folder"""
    p = TimeoutSubprocess([adb_path, "shell", "rm", "-r", "/sdcard/Download/*"])
    returncode, stdout, stderr = p.run(timeout=20)

    if returncode == 0:
        print "Cleaned Download folder"
    else:
        print "[", returncode, "]: failed to clean Download folder"


def spinning_cursor():
    """ Helps to show spinning cursor"""
    while True:
        for cursor in '|/-\\':
            yield cursor


def reverse_readline(filename, buf_size=8192):
    """a generator that returns the lines of a file in reverse order"""
    with open(filename) as fh:
        segment = None
        offset = 0
        fh.seek(0, os.SEEK_END)
        total_size = remaining_size = fh.tell()
        while remaining_size > 0:
            offset = min(total_size, offset + buf_size)
            fh.seek(-offset, os.SEEK_END)
            buffer = fh.read(min(remaining_size, buf_size))
            remaining_size -= buf_size
            lines = buffer.split('\n')
            # the first line of the buffer is probably not a complete line so
            # we'll save it and append it to the last line of the next buffer
            # we read
            if segment is not None:
                # if the previous chunk starts right from the beginning of line
                # do not concact the segment to the last line of new chunk
                # instead, yield the segment first 
                if buffer[-1] is not '\n':
                    lines[-1] += segment
                else:
                    yield segment
            segment = lines[0]
            for index in range(len(lines) - 1, 0, -1):
                yield lines[index]
        yield segment


# For development in Python IDLE
# sys.argv = ["launcher.py", "--apk", "SmartUxSdkTestSuite.apk", "--ip", "10.88.192.68", "--android", "c:\\Program Files (x86)\\Android\\android-sdk\\"]


def wake_up_and_connect(arg_setup_files_dir, arg_ip, adb_path):
    """
    Wake ups and connects to the printer via adb
    """
    # Try wakeup printer, otherwise connections via ADB will not be possible
    if os.path.exists(arg_setup_files_dir + "IpsendLib.py") and os.path.exists(arg_setup_files_dir + "pcl_noaction.prn"):
        ipsendlib = imp.load_source('ipsendLib', arg_setup_files_dir + "IpsendLib.py")
        retval = ipsendlib.executeIpPrnSend(arg_ip, arg_setup_files_dir + "pcl_noaction.prn")
        print "Device " + arg_ip + " wake up with result " + str(retval) + "\n"
        # give device some time to really wakeup
        time.sleep(20)

    # Connect adb to the printer
    print "Connecting to", arg_ip, "..."
    p = TimeoutSubprocess([adb_path, "connect", arg_ip])
    returncode, stdout, stderr = p.run(timeout=20)

    if returncode != 0 or "unable to connect" in stdout:
        print "[", returncode, "]: unable to connect to ", arg_ip, ":5555"
        sys.exit(1)

    print stdout


def main():
    """ Great Main Function """
    # Read input params
    try:
        opts, args = getopt.getopt(sys.argv[1:], "hea:s:t:c:i:o:d:u:",
                                   ["help", "clean", "apk=", "ip=", "type=", "class=", "out=", "android=", "support=", "setup="])
    except getopt.GetoptError:
        print_usage_info()
        sys.exit(2)

    my_path = module_locator.module_path() + "/"

    arg_ip = None
    arg_class = None
    arg_type = "all"
    arg_support_apk = ''
    arg_setup_files_dir = my_path
    arg_clean_downloads = True

    for opt, arg in opts:
        if opt in ("-i", "--ip"):
            arg_ip = arg
        elif opt in ("-d", "--android"):
            arg_adb_path = arg
            props_file = open("local.properties", "w")
            props_file.write(arg)
            props_file.close()
        elif opt in ("-s", "--support"):
            arg_support_apk = arg
        elif opt in ("-t", "--type"):
            # Validate type right here and now
            if not arg in ("all", "manual", "auto"):
                print "Error! unsupported test type was requested. Supported values are: all, auto, manual"
                print_usage_info()
                sys.exit(0)
            arg_type = arg
        elif opt in ("-c", "--class"):
            arg_class = arg
        elif opt in ("-u", "--setup"):
            arg_setup_files_dir = arg
        elif opt in ("-h", "--help"):
            print_usage_info()
            sys.exit(0)
        elif opt in ("-e", "--clean"):
            arg_clean_downloads = True

    if arg_ip is None:
        print "Error! --ip argument should be presented!\n"
        print_usage_info()
        sys.exit(0)

    # Check if adb is presented
    try:
        props_file = open("local.properties", "r")
        adb_path = props_file.read()
        props_file.close()
        print "Read properties..."
    except IOError:
        # okay, no file exists
        adb_path = "adb"

    if adb_path is None or len(adb_path) < 3:
        adb_path = "adb"

    if not check_adb(adb_path):
        adb_path = find_adb(adb_path)

    if len(adb_path) < 3:
        print "No adb found. Please add Android SDK to arguments with --android key.\n"
        print_usage_info()
        sys.exit(0)

    print "adb found in:", adb_path

    # Restart adb
    print "Restarting adb..."
    p = TimeoutSubprocess([adb_path, "kill-server"])
    returncode, stdout, stderr = p.run(timeout=20)
    print stdout
    p = TimeoutSubprocess([adb_path, "start-server"])
    returncode, stdout, stderr = p.run(timeout=20)
    print stdout

    if not arg_setup_files_dir.endswith('/'):
        arg_setup_files_dir = arg_setup_files_dir + "/"

    wake_up_and_connect(arg_setup_files_dir, arg_ip, adb_path)

    set_log_props(adb_path)

    if arg_clean_downloads:
        clean_download_folder(adb_path)

    # Try to login as admin in order to be able to install apks
    if os.path.exists(arg_setup_files_dir + "TGIF_UI_Automator_Tests.jar"):
        print "Let's login as Admin to intstall stuff..."
        print "Pushing ", "TGIF_UI_Automator_Tests.jar", "into the device..."
        p = TimeoutSubprocess([adb_path, "push", arg_setup_files_dir + "TGIF_UI_Automator_Tests.jar", "/data/local/tmp"])
        returncode, stdout, stderr = p.run(timeout=120)
        print "Show login / logout screen..."
        p = TimeoutSubprocess([adb_path, "shell", "am", "broadcast", "-a", "net.xoaframework.intent.action.LOGIN_CLICKED"])
        returncode, stdout, stderr = p.run(timeout=10)
        time.sleep(15)
        # Ensure that login is shown
        p = TimeoutSubprocess([adb_path, "shell", "am", "broadcast", "-a", "net.xoaframework.intent.action.LOGIN_CLICKED"])
        returncode, stdout, stderr = p.run(timeout=15)
        time.sleep(15)
        print "Input credentials..."
        p = TimeoutSubprocess([adb_path, "shell", "uiautomator", "runtest", "TGIF_UI_Automator_Tests.jar", "-c", "com.sec.tgif.ui.aa.login.LoginUIDevice", "-e", "userId", 
"admin", "-e", "password", "admin@12", "-e", "domain", "Local"])
        returncode, stdout, stderr = p.run(timeout=120)
        time.sleep(30)
        # Now press HOME just in case we've been already logged in and now observing 'Logout' screen
        print 'Press HOME'
        p = TimeoutSubprocess([adb_path, "shell", "input", "keyevent", "3"])
        returncode, stdout, stderr = p.run(timeout=30)
        time.sleep(10)

    # Install support apk to the device
    if arg_support_apk:
        print "Installing ", arg_support_apk, "into the device..."
        # unsintall first, because might have different signature
        p = TimeoutSubprocess([adb_path, "uninstall", "com.sec.android.ssp.app.support"])
        p.run(timeout=120)
        # Now do real installation
        p = TimeoutSubprocess([adb_path, "install", "-r", arg_support_apk])
        returncode, stdout, stderr = p.run(timeout=120)

    if returncode != 0:
        print "[", returncode, "]: Failed to install apk into ", arg_ip
        # TODO: sys.exit(1)

    print stdout

    # Restart ADB and reconnect to the device just in case it's get offline
    print "Restarting adb..."
    p = TimeoutSubprocess([adb_path, "kill-server"])
    returncode, stdout, stderr = p.run(timeout=20)
    print stdout
    wake_up_and_connect(arg_setup_files_dir, arg_ip, adb_path)
    time.sleep(10)

    # Press HOME to keep the device awake
    if os.path.exists(arg_setup_files_dir + "TGIF_UI_Automator_Tests.jar"):
        print 'Press HOME'
        p = TimeoutSubprocess([adb_path, "shell", "input", "keyevent", "3"])
        returncode, stdout, stderr = p.run(timeout=10)

    print "Finished helper script \n"

if __name__ == "__main__":
    main()
