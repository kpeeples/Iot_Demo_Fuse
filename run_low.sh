#!/bin/bash

export deviceType="1"
export deviceID="1"
export initialValue="51"
export count="1"
export waitTime="1"

echo "Starting the producer to send messages "
java -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar producer/target/iot_producer-jar-with-dependencies.jar
