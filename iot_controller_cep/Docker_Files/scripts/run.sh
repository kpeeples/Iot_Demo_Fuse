#!/bin/bash
#
# Little helper for the installation of Red Hat JBoss Fuse in a Docker container
#

# echo "Start Fuse and wait for start procedure to end"
# $HOME/$FUSE_LOCATION/bin/start

# $HOME/$FUSE_LOCATION/bin/status
# while [ "$?" != "0" ]
# do
#    echo "."
#   sleep 10
#   $HOME/$FUSE_LOCATION/bin/status
# done

echo "Now let's deploy the bundle"
java -jar $HOME/tmp/$APPL
