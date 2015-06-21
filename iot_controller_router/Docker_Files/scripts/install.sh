#!/bin/bash
#
# Little helper for the installation of Red Hat JBoss Fuse in a Docker container
#

# echo "Building deployables"
# mvn clean install

echo "Change some configuration"
echo sed -i "s/=amq/=$BROKER_NAME/" $HOME/$FUSE_LOCATION/etc/org.fusesource.mq.fabric.server-default.cfg
sed -i "s/=amq/=$BROKER_NAME/" $HOME/$FUSE_LOCATION/etc/org.fusesource.mq.fabric.server-default.cfg

echo "Start Fuse and wait for start procedure to end"
$HOME/$FUSE_LOCATION/bin/start

echo "Giving Fuse a little time to start"
sleep 20

$HOME/$FUSE_LOCATION/bin/status
while [ "$?" != "0" ]
do
   sleep 10
   echo "Checking if Fuse has now started"
   $HOME/$FUSE_LOCATION/bin/status
done

echo "Now let's deploy the bundle"
echo $HOME/$FUSE_LOCATION/bin/client "osgi:install --start file://$HOME/tmp/$BUNDLE_NAME"
$HOME/$FUSE_LOCATION/bin/client "osgi:install --start file://$HOME/tmp/$BUNDLE_NAME"
