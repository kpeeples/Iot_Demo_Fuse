#!/bin/bash

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


