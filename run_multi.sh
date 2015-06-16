#!/bin/bash

export deviceType="1"
export deviceID="1"
export initialValue="70"
export count="100"
export waitTime="1"

echo "Starting the producer to send messages "
java -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar producer/target/iot_producer-jar-with-dependencies.jar > /dev/null 2>&1 &

export deviceID="2"
export initialValue="100"
java -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar producer/target/iot_producer-jar-with-dependencies.jar > /dev/null 2>&1 &

export deviceID="3"
export initialValue="20"
java -DdeviceType=$deviceType -DdeviceID=$deviceID -DinitialValue=$initialValue -Dcount=$count -DwaitTime=$waitTime -jar producer/target/iot_producer-jar-with-dependencies.jar > /dev/null 2>&1 &
