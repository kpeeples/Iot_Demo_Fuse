FROM    psteiner/fuse

MAINTAINER psteiner <psteiner@redhat.com>

USER root

ENV BUNDLE_NAME controller_temperature-1.0.0-SNAPSHOT.jar
ENV BROKER_NAME iotcontrollertemperature

# copy required ( or not ) configurations
ADD Docker_Files/config/activemq.xml $HOME/tmp/

# copy helper_install script
ADD Docker_Files/scripts/install.sh $HOME/tmp/

# copy deployable
# ADD src $HOME/tmp/src/
# ADD pom.xml $HOME/tmp/
ADD target/$BUNDLE_NAME $HOME/tmp/

# let the user 'psteiner' own the files
RUN chown -R psteiner:psteiner $HOME

# continue as 'psteiner'
USER psteiner

RUN chmod +x $HOME/tmp/install.sh
RUN chmod +x $HOME/run.sh

# run the helper_install script
WORKDIR $HOME/tmp
RUN $HOME/tmp/install.sh

# move configuration(s) to their right place
RUN mv $HOME/tmp/activemq.xml $HOME/$FUSE_LOCATION/etc

# open ports
EXPOSE 22 8101 8181 44444 1099 61616 80 8080 

# ENTRYPOINT $HOME/$FUSE_LOCATION/bin/fuse
CMD $HOME/run.sh

# Build command -> docker build --rm -t workspacefuse_iotcontrollertemperature .
# run command -> docker run -d -p 10000:8181 workspacefuse_iotcontrollertemperature
# run and connect -> docker run -p 10000:8181 -i -t workspacefuse_iotcontrollertemperature /bin/bash
