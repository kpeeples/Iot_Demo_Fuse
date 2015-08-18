FROM    psteiner/fuse

MAINTAINER psteiner <psteiner@redhat.com>

USER root

ENV APPL iot_controller_cep-jar-with-dependencies.jar

# Transfer application
# ADD src $HOME/tmp/src/
# ADD pom.xml $HOME/tmp/
ADD target/$APPL $HOME/tmp/

# transfer script
ADD Docker_Files/scripts/run.sh $HOME/tmp/

# make 'psteiner' the owner
RUN chown -R psteiner:psteiner $HOME/tmp
RUN chmod u+x $HOME/tmp/run.sh

# switch to psteiner
USER psteiner

WORKDIR $HOME/tmp

EXPOSE 8080 9990 4447 5459

CMD $HOME/tmp/run.sh



# Build command -> docker build --rm -t workspacefuse_iotcontrollercep .
# run command -> docker run -h iotcontrollerrouter -d workspace_iotcontrollercep
# run and connect -> docker run -P -i -t -h iotcontrollercep workspace_iotcontrollercep /bin/bash
