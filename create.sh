#!/bin/sh

# oc new-project iotdemo
oc create -f iot-demo.json
oc process iot-demo  | oc create -f -
