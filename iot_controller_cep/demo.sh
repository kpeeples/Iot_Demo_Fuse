#!/usr/bin/env bash
#
# Created by Juergen Hoffmann <buddy@redhat.com>
# extended by Patrick Steiner <psteiner@redhat.com>
#

# This script builds all required docker images.

set -e
NAME=$(basename $0)
declare -A DOCKER_IMAGE

DOCKER_IMAGE["CEP:IMAGE_NAME"]="workspace_iotcontrollercep"

function remove_image {
  IMAGE=$1

  echo "Removing $IMAGE"

  # grab the image id hash
  IMAGE_ID=$(docker images | grep -w $IMAGE | awk '{ print $3; }')

  # Only try removing the images if there is a pre-built image
  if [ ! -z "$IMAGE_ID" ]; then

  echo "found $IMAGE_ID"

    if [ $(docker ps -a | grep $IMAGE_ID | awk '{ print $1; }' | wc -l) -gt 0 ]; then
      # remove all running and stopped containers based of the image
      docker rm -f $(docker ps -a | grep $IMAGE_ID | awk '{ print $1; }')
    fi

    if [ $(docker ps -a | grep $IMAGE | awk '{ print $1; }' | wc -l) -gt 0 ]; then
      # In case we still have the named reference
      docker rm -f $(docker ps -a | grep $IMAGE | awk '{ print $1; }')
    fi

    # finally remove the image
    docker rmi $IMAGE_ID
  fi
}

function build_image {
  IMAGE=$1
  pushd ./${IMAGE}_Image >/dev/null
  echo "Building ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]}"

  docker build -q --rm -t ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} .
  popd > /dev/null

}

function connect_image {
  IMAGE=$1
  CONTAINER_ID=$(docker ps | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | cut -c1-13 )
  PID=$(docker inspect --format '{{ .State.Pid }}' $CONTAINER_ID)
  
  echo "Connecting ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} with CONTAINER_ID <$CONTAINER_ID> and PID <$PID>"

  sudo nsenter -m -u -n -i -p -t $PID
}

function commit_image {
  IMAGE=$1
  CONTAINER_ID=$(docker ps | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | cut -c1-13 )
  PID=$(docker inspect --format '{{ .State.Pid }}' $CONTAINER_ID)
  
  echo "Commit ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} with CONTAINER_ID <$CONTAINER_ID>"

  docker commit $CONTAINER_ID ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]}  
}

function get_ip {
  IMAGE=$1
  CONTAINER_ID=$(docker ps | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | cut -c1-13 )
  IP=$(docker inspect --format '{{ .NetworkSettings.IPAddress }}' $CONTAINER_ID)
  
  echo "IP adress of ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} is <$IP>"
}

function stop_image {
  IMAGE=$1
  if [ $(docker ps | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | wc -l) -gt 0 ]; then
    echo "Stopping all Images matching ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]}"
    docker stop $(docker ps | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | awk '{ print $1; }')

    if [ ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} == ${DOCKER_IMAGE["HEISE_FSW:IMAGE_NAME"]} ]; then
	echo "Removing ${DOCKER_IMAGE["HEISE_FSW:IMAGE_NAME"]}"
	docker rm $(docker ps -a | grep ${DOCKER_IMAGE["${IMAGE}:IMAGE_NAME"]} | awk '{ print $1; }')
    fi 

  fi
}

case "$1" in
remove)
   echo "Removing cep Image"
   remove_image ${DOCKER_IMAGE["CEP:IMAGE_NAME"]}
   ;;
start)
   echo "Starting ${DOCKER_IMAGE["CEP:IMAGE_NAME"]}"
   echo docker run --link ${DOCKER_IMAGE["CEP:LINK_TO_NAME"]}:${DOCKER_IMAGE["CEP:LINK_TO_NAME"]} -e PROVIDER_URL=iotcontrollercep  -d ${DOCKER_IMAGE["CEP:IMAGE_NAME"]} 

   docker run --link ${DOCKER_IMAGE["CEP:LINK_TO_NAME"]}:${DOCKER_IMAGE["CEP:LINK_TO_NAME"]} -e PROVIDER_URL=iotcontrollercep  -d ${DOCKER_IMAGE["CEP:IMAGE_NAME"]} 
   ;;
connect)
   echo "Connecting into running ${DOCKER_IMAGE["CEP:IMAGE_NAME"]} container"
   connect_image "CEP"
   ;;
commit)
  commit_image "CEP"
  ;;
ip)
   get_ip "CEP"
   ;;
build)
   build_image "CEP"
   ;;
stop)
   stop_image "CEP"
   ;;
status)
    docker ps
    ;;
help)
    echo "usage: ${NAME} (remove|start|build|status|connect|ip)"
    ;;
*)
    echo "usage: ${NAME} (remove|start|build|status|connect|ip)"
    exit 1
esac

