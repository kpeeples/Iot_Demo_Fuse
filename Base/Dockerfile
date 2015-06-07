FROM    centos:centos6

MAINTAINER psteiner <psteiner@redhat.com>

# Update the system and Install necessary RPMs
RUN yum -y update && \ 
    yum -y install java-1.7.0-openjdk java-1.7.0-openjdk-devel unzip wget telnet && \
    yum clean all

# Make sure required environments are set
ENV JAVA_HOME /usr/lib/jvm/jre
ENV HOME /home/psteiner
ENV MAVEN_HOME /opt/maven

# Set root password
RUN echo "root:redhat" | /usr/sbin/chpasswd

# download and install maven
RUN wget http://ftp.fau.de/apache/maven/maven-3/3.2.5/binaries/apache-maven-3.2.5-bin.zip
RUN unzip apache-maven-3.2.5-bin.zip
RUN mv apache-maven-3.2.5/ /opt/maven
RUN ln -s /opt/maven/bin/mvn /usr/bin/mvn
RUN rm apache-maven-3.2.5-bin.zip


# Create user to run processed
RUN useradd -m -d /home/psteiner -p psteiner psteiner 

# Switch to new user
USER psteiner

# create a few needed directories
RUN mkdir $HOME/tmp

# make $HOME/tmp the active directory
WORKDIR $HOME/tmp

# Build command -> docker build --rm -t psteiner/base .
# run command -> docker run -d psteiner/base
