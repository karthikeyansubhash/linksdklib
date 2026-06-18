#!/usr/bin/env python2

__author__ = 'sdk'
"""       compose-out-folder:
             composeoutfolder.py

Copyright (c) 2018 HP, Inc All rights reserved.
"""
import os
import os.path
import stat
import errno
import shutil
import string
import module_locator

from smartuxsdk_prepare_test import TimeoutSubprocess


def silentremove(filename):
    try:
        if os.path.isdir(filename):
            shutil.rmtree(filename, ignore_errors=True)
        else:
            os.remove(filename)
    except OSError as e: # this would be "except OSError, e:" before Python 2.6
        if e.errno != errno.ENOENT: # errno.ENOENT = no such file or directory
            raise # re-raise exception if a different error occurred


def move_rename(src, desc):
    """ Move sampleconfig for release"""
    try:
        if os.path.isdir(src):
            print "Failed to move " + src + " because of directory"
            return
        else:
            shutil.move(src, desc)
    except shutil.Error as e:
        print "Failed to move " + src + " because of " + e.message


def copy(my_path, out_folder, src, out_specific_folder, clear_build=False):
    src = my_path + src

    # Compose this file out path
    if not os.path.exists(src):
        print "Error! Artifact " + src + " wasn't prepared! \n"
        return

    out_name = out_folder + "/" + out_specific_folder + "/" + os.path.basename(src)

    try:
        os.makedirs(os.path.dirname(out_name))
    except OSError:
        pass

    try:
        if os.path.isdir(src):
            silentremove(out_name)
            shutil.copytree(src, out_name)
            if clear_build:
                silentremove(out_name + "/build")
        else:
            shutil.copy(src, os.path.dirname(out_name))
    except shutil.Error as e:
        print "Failed to copy " + src + " because of " + e.message


def copyRaw(my_path, out_folder, src, out_specific_folder, clear_build=False):
    src = my_path + src

    # Compose this file out path
    if not os.path.exists(src):
        print "Error! Artifact " + src + " wasn't prepared! \n"
        return

    out_name = out_folder + "/" + out_specific_folder

    try:
        os.makedirs(os.path.dirname(out_name))
    except OSError:
        pass

    try:
        if os.path.isdir(src):
            silentremove(out_name)
            shutil.copytree(src, out_name)
            if clear_build:
                silentremove(out_name + "/build")
        else:
            shutil.copy(src, os.path.dirname(out_name))
    except shutil.Error as e:
        print "Failed to copy " + src + " because of " + e.message


def makeWritable(path):
    for root, dirs, files in os.walk(path):
        for fname in files:
            full_path = os.path.join(root, fname)
            os.chmod(full_path, stat.S_IRUSR | stat.S_IWUSR | stat.S_IRGRP | stat.S_IWGRP | stat.S_IROTH | stat.S_IWOTH)  


my_path = module_locator.module_path() + "/"
android_home = os.environ['ANDROID_HOME']

classpath_string = [
    android_home + "extras/android/m2repository/com/android/support/support-annotations/24.1.0/support-annotations-24.1.0.jar:"
	]


def generate_javadoc():
    """ Generates SDK Javadocs into output folder"""
    if os.name == 'nt':
        my_path = module_locator.module_path() + "\\"
    else:
        my_path = module_locator.module_path() + "/"

    out_folder = my_path + "/../out/LinkForDevice/Docs/WorkpathLib-javadoc/"
    try:
        os.makedirs(os.path.dirname(out_folder))
    except OSError:
        pass

    print "Generating JavaDocs..."

    p = TimeoutSubprocess(["javadoc",
                           "-verbose",
                           "-protected",
                           "-splitindex",
                           "-encoding", "UTF-8",
                           "-charset", "UTF-8",
                           "-docencoding", "UTF-8",
                           #"-linkoffline", "http://d.android.com/reference",
                           "-padExcludeTag", "hide",
                           "-bootclasspath", android_home + "platforms/android-26/android.jar:" + my_path + "../out/LinkForAll/Libs/JetAdvantageLinkLib/libs/JetAdvantageLinkLibrary.jar",
                           "-windowtitle", "HP Workpath SDK API Reference Guide",
                           "-docletpath", my_path + "publishedApiDoclet1.5-0.4.jar",
                           "-doclet", "de.kruis.padoclet.PublishedApiDoclet",
                           #"-linkoffline", "http://d.android.com/reference", "file:" + android_home + "docs/reference/",
                           #"@" + my_path + "javadoc_args.txt",
                           "-classpath", string.join(classpath_string, ''),
                           "-d", out_folder,
                           "@" + my_path + "javadoc_args.txt",
                           ]
    )

    print "with command\n" + str(p.cmd)

    returncode, stdout, stderr = p.run(timeout=200)

    print stdout

    if returncode == 0:
        print "Generated JavaDocs"
    else:
        print "Seems generation failed [", returncode, "]: failed to generate JavaDocs, but try to create jar anyway"

    if not os.path.exists(out_folder + "overview-summary.html"):
        print "Summary page is not generated! Generation Failed"
        return

    summary_file = out_folder + "overview-summary.html"
    f = open(summary_file, "r")
    contents = f.readlines()
    f.close()

    contents.insert(64, "<div class=\"header\">\n<h1 class=\"title\">HP Workpath SDK API Reference Guide</h1>\n</div>")

    f = open(summary_file, "w")
    contents = "".join(contents)
    f.write(contents)
    f.close()

    # Copy resource files to final javadocs location

    print "Update javadoc summary title"

    print "Compose API_Reference.jar"

    p = TimeoutSubprocess(["jar", "-cvf", my_path + "/../out/LinkForDevice/Docs/WorkpathLib-javadoc.jar", "-C", out_folder, "."])

    returncode, stdout, stderr = p.run(timeout=200)

    print stdout



    out_folder_mobile = my_path + "/../out/LinkForMobile/Docs/WorkpathLibMobile-javadoc/"

    try:
        os.makedirs(os.path.dirname(out_folder_mobile))
    except OSError:
        pass

    print "Generating JavaDocs for mobile..."

    p = TimeoutSubprocess(["javadoc",
                           "-verbose",
                           "-protected",
                           "-splitindex",
                           "-encoding", "UTF-8",
                           "-charset", "UTF-8",
                           "-docencoding", "UTF-8",
                           #"-linkoffline", "http://d.android.com/reference",
                           "-padExcludeTag", "hide,deviceOnly",
                           "-bootclasspath", android_home + "platforms/android-26/android.jar:" + my_path + "../out/LinkForAll/Libs/JetAdvantageLinkLib/libs/JetAdvantageLinkLibrary.jar",
                           "-windowtitle", "HP Workpath SDK API Reference Guide",
                           "-docletpath", my_path + "publishedApiDoclet1.5-0.4.jar",
                           "-doclet", "de.kruis.padoclet.PublishedApiDoclet",
                           #"-linkoffline", "http://d.android.com/reference", "file:" + android_home + "docs/reference/",
                           #"@" + my_path + "javadoc_args.txt",
                           "-classpath", string.join(classpath_string, ''),
                           "-d", out_folder_mobile,
                           "@" + my_path + "javadoc_args_mobile.txt",
                           ]
    )

    print "with command\n" + str(p.cmd)

    returncode, stdout, stderr = p.run(timeout=200)

    print stdout

    if returncode == 0:
        print "Generated JavaDocs"
    else:
        print "Seems generation failed [", returncode, "]: failed to generate JavaDocs, but try to create jar anyway"

    if not os.path.exists(out_folder_mobile + "overview-summary.html"):
        print "Summary page is not generated! Generation Failed"
        return

    summary_file = out_folder_mobile + "overview-summary.html"
    f = open(summary_file, "r")
    contents = f.readlines()
    f.close()

    contents.insert(64, "<div class=\"header\">\n<h1 class=\"title\">HP Workpath SDK API Reference Guide</h1>\n</div>")

    f = open(summary_file, "w")
    contents = "".join(contents)
    f.write(contents)
    f.close()


    # Copy resource files to final javadocs location

    print "Update javadoc summary title"

    print "Compose API_Reference.jar"

    p = TimeoutSubprocess(["jar", "-cvf", my_path + "/../out/LinkForMobile/Docs/WorkpathLibMobile-javadoc.jar", "-C", out_folder_mobile, "."])

    returncode, stdout, stderr = p.run(timeout=200)

    print stdout


def main():
    """ Main Function """
    if os.name == 'nt':
        my_path = module_locator.module_path() + "\\"
    else:
        my_path = module_locator.module_path() + "/"

    out_folder = my_path + "/../out/"

    # Clear all existing artifacts in workspace
    silentremove(out_folder)
    os.makedirs(out_folder)

    # Copy services apk and library aar
    # LinkForDevice
    #   - Docs
    #   - Libs
    #   - Samples
    # LinkForMobile
    #   - Docs
    #   - Libs
    #   - Samples
    # LinkForAll
    #   - Docs
    #   - Libs

    # Copy lib for device (1/3)
    copy(my_path, out_folder, "../build/modules/Library-JetAdvantageLinkLib/outputs/aar/JetAdvantageLinkLib-releasePanel.aar", "LinkForDevice/Libs")
    move_rename(out_folder+"/LinkForDevice/Libs/JetAdvantageLinkLib-releasePanel.aar", out_folder+"/LinkForDevice/Libs/WorkpathLib.aar")

    # Copy lib for mobile(2/3)
    copy(my_path, out_folder, "../build/modules/Library-JetAdvantageLinkLib/outputs/aar/JetAdvantageLinkLib-releaseMobile.aar", "LinkForMobile/Libs")
    move_rename(out_folder+"/LinkForMobile/Libs/JetAdvantageLinkLib-releaseMobile.aar", out_folder+"/LinkForMobile/Libs/WorkpathLib.aar")

    # Copy lib for all (3/3)
    copy(my_path, out_folder, "../build/modules/Library-JetAdvantageLinkLib/outputs/aar/JetAdvantageLinkLib-release.aar", "LinkForAll/Libs")
    move_rename(out_folder+"/LinkForAll/Libs/JetAdvantageLinkLib-release.aar", out_folder+"/LinkForAll/Libs/JetAdvantageLinkLib.aar")
    copy(my_path, out_folder, "../build/modules/Services-JetAdvantageLinkServices/outputs/mapping/release/mapping.txt", "LinkForAll/Libs")
    copy(my_path, out_folder, "../Libs/JetAdvantageLinkLib", "LinkForAll/Libs", True)
    copy(my_path, out_folder, "../build/modules/Build-JetAdvantageLinkInternalLibs/libs/JetAdvantageLinkLibrary.jar", "LinkForAll/Libs/JetAdvantageLinkLib/libs")

    # Move aar in sample for device (1/2)
    copy(my_path, out_folder, "../build/modules/Library-JetAdvantageLinkLib/outputs/aar/JetAdvantageLinkLib-releasePanel.aar", "LinkForDevice/Samples/source/JetAdvantageLinkLib")
    move_rename(out_folder+"/LinkForDevice/Samples/source/JetAdvantageLinkLib/JetAdvantageLinkLib-releasePanel.aar", out_folder+"/LinkForDevice/Samples/source/JetAdvantageLinkLib/JetAdvantageLinkLib.aar")

    # Move aar in sample for mobile (2/2)
    copy(my_path, out_folder, "../build/modules/Library-JetAdvantageLinkLib/outputs/aar/JetAdvantageLinkLib-releaseMobile.aar", "LinkForMobile/Samples/source/JetAdvantageLinkLib")
    move_rename(out_folder+"/LinkForMobile/Samples/source/JetAdvantageLinkLib/JetAdvantageLinkLib-releaseMobile.aar", out_folder+"/LinkForMobile/Samples/source/JetAdvantageLinkLib/JetAdvantageLinkLib.aar")

    # copy generated Android javadoc
    copyRaw(my_path, out_folder, "../build/modules/API-JetAdvantageLinkApi/docs/javadoc/public/release", "LinkForAll/Docs/Android_API_Reference")
    
    generate_javadoc()


if __name__ == "__main__":
    main()
