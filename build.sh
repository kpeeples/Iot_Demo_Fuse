#!/bin/bash

echo "-Building deloyables"
echo "-- iot_controller_receiver"
pushd . > /dev/null
cd iot_controller_receiver
mvn clean install > /dev/null
popd > /dev/null

echo "-- iot_controller_router"
pushd . > /dev/null
cd iot_controller_router
mvn clean install > /dev/null
popd > /dev/null

echo "-- iot_controller_temperature"
pushd . > /dev/null
cd iot_controller_temperature
mvn clean install > /dev/null
popd > /dev/null

echo "-- iot_controller_cep"
pushd . > /dev/null
cd iot_controller_cep
mvn clean install > /dev/null
popd > /dev/null

echo "-- iot_datacenter_receiver"
pushd . > /dev/null
cd iot_datacenter_receiver
mvn clean install > /dev/null
popd > /dev/null

echo "-- iot_datacenter_monitor"
pushd . > /dev/null
cd iot_datacenter_monitor
mvn clean install > /dev/null
popd > /dev/null

echo "-Building all images for Fuse IoT Demo"
echo "-- Building Base Image"
pushd .
cd Base
docker build --rm -t psteiner/base .
popd

echo "-- Building Fuse Image"
pushd .
cd Fuse
docker build --rm -t psteiner/fuse .
popd

echo "-- Building docker-compose based images"
docker-compose build


