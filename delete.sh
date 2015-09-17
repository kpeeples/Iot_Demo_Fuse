#! /bin/sh

oc delete template iot-demo
oc delete all --selector="application=iotcontrollerreceiver"
# oc delete project iotdemo
