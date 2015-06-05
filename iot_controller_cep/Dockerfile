FROM    psteiner/fsw60_base

MAINTAINER psteiner <psteiner@redhat.com>

USER root
run chown -R psteiner:psteiner $HOME/tmp

# copy and prepare run-script
ADD script/startup.sh $HOME/tmp/startup.sh
RUN chmod +x $HOME/tmp/startup.sh
RUN chown -R psteiner:psteiner $HOME/tmp

USER psteiner

# transfer sources
WORKDIR $HOME/tmp
ADD target $HOME/tmp/
ADD config/standalone-full.xml $HOME/tmp/standalone-full.xml


# copy config
RUN mv $HOME/tmp/standalone-full.xml $HOME/fsw/jboss-eap-6.1/standalone/configuration

# copy jar
RUN mv $HOME/tmp/iot_controller_cep-jar-with-dependencies.jar $HOME/iot_controller_cep.jar

# cleanup
# RUN rm -rf $HOME/tmp/src
# RUN rm -rf $HOME/tmp/pom.xml

EXPOSE 8080 9990 4447 5459

CMD $HOME/tmp/startup.sh



# Build command -> docker build --rm -t workspace_iotcontrollercep .
# run command -> docker run -h iotcontrollerrouter -d workspace_iotcontrollercep
# run and connect -> docker run -P -i -t -h iotcontrollercep workspace_iotcontrollercep /bin/bash
