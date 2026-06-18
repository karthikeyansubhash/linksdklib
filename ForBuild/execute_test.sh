#!/bin/bash

while getopts ":p:i:" opt; do
  case $opt in
     p) path="$OPTARG"
     ;;
     i) ip="$OPTARG"
     ;;
     \?) echo "Invalid option -$OPTARG" >&2
     ;;
  esac
done

echo "IP is $ip"

$path/SSP/gradlew \
    -p $path/SSP \
    :Apps:SmartUxSdkTestSuite:clean \
    :Apps:SmartUxSdkTestSuite:assembleAndroidTest

python -u $path/build/smartuxsdk_prepare_test.py \
   --support=$path/out/Libs/SmartUXServices-release.apk \
   --ip=$ip \
   --clean

rm -f "%path/SSP/Apps/SmartUxSdkTestSuite/build/outputs/androidTest-results/connected/TEST-samsung-printer-tablet\ -\ 4.2.2-SmartUxSdkTestSuite-.xml"

adb kill-server && adb start-server
for i in {1..25}; do { adb connect $ip; sleep 5; } done &
adb connect $ip

$path/SSP/gradlew \
    -p $path/SSP \
    :Apps:SmartUxSdkTestSuite:connectedAndroidTest

# If out results file exists
if [ -f "%path/SSP/Apps/SmartUxSdkTestSuite/build/outputs/androidTest-results/connected/TEST-samsung-printer-tablet\ -\ 4.2.2-SmartUxSdkTestSuite-.xml" ]
then
    exit 1
fi

adb devices

# Run again in case if first run has failed due to connection
adb connect $ip
for i in {1..25}; do { adb connect $ip; sleep 5; } done &

$path/SSP/gradlew \
    -p $path/SSP \
    :Apps:SmartUxSdkTestSuite:connectedAndroidTest

#adb devices
wait