#!/bin/bash
#
# Little helper for the installation of Red Hat JBoss Fuse in a Docker container
#
echo "Change some configuration"
echo sed -i "s/=amq/=$BROKER_NAME/" $HOME/$FUSE_LOCATION/etc/io.fabric8.mq.fabric.server-broker.cfg
sed -i "s/=amq/=$BROKER_NAME/" $HOME/$FUSE_LOCATION/etc/io.fabric8.mq.fabric.server-broker.cfg

echo "Start Fuse and wait for start procedure to end"
$HOME/$FUSE_LOCATION/bin/start

$HOME/$FUSE_LOCATION/bin/status
while [ "$?" != "0" ]
do
   echo "."
   sleep 10
   $HOME/$FUSE_LOCATION/bin/status
done

sleep 10

echo "Now let's deploy the bundle and some prereqs"
$HOME/$FUSE_LOCATION/bin/client "osgi:install -s wrap:mvn:commons-dbcp/commons-dbcp/1.4"
sleep 10
$HOME/$FUSE_LOCATION/bin/client "osgi:install -s wrap:mvn:org.postgresql/postgresql/9.3-1102-jdbc41"
sleep 10
$HOME/$FUSE_LOCATION/bin/client "osgi:install -s file://$HOME/tmp/$BUNDLE_NAME"
