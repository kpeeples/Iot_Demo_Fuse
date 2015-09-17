#!/bin/bash

export deviceType="1"
export deviceID="1"
export initialValue="70"
export count="50"
export waitTime="1"
export messageFormat="XML"
export receiverURL="iot-demo.cloudapps.example.com"
export defaultPort=":30061"


echo "Starting the producer to send messages "
java -DreceiverURL=$receiverURL -DdefaultPort=$defaultPort -DmessageFormat=$messageFormat -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar producer/target/iot_producer-jar-with-dependencies.jar
