#!/bin/bash



set -e
cd /Users/cz_michael/Google\ Drive/Work/workspace/HealthfullU2
mvn clean 
mvn install

wait
echo "mvn clean install ......... Done!"

cd /Users/cz_michael/Google\ Drive/Work/workspace/HealthfullU2/src/main/webapp/static/less/healthfull_u
lessc healthfullU.less > ../../css/healthfull_u/healthfullU.css
wait
echo "less tranlate to css ......... Done!"

# shutdown tomcat
echo "" > /usr/local/apache-tomcat-7.0.59/logs/catalina.out
cd /usr/local/apache-tomcat-7.0.59/bin
./shutdown.sh

wait
echo "tomcat shutdown ......... Done!"


# remove older war
rm -rf /usr/local/apache-tomcat-7.0.59/webapps/ROOT/*
rm -rf /usr/local/apache-tomcat-7.0.59/webapps/HealthfullU2-0.0.2-SNAPSHOT

wait
echo "removing old tomcat webapp files ......... Done!"


rsync --progress /Users/cz_michael/Google\ Drive/Work/workspace/HealthfullU2/target/HealthfullU2-0.0.2-SNAPSHOT.war /usr/local/apache-tomcat-7.0.59/webapps/HealthfullU2-0.0.2-SNAPSHOT.war

wait
echo "rsync ......... Done!"


# extract war file into ROOT directory
cd /usr/local/apache-tomcat-7.0.59/webapps/ROOT
jar -xvf ../HealthfullU2-0.0.2-SNAPSHOT.war


wait
echo "explode war file ......... Done!"


# startup tomcat
cd /usr/local/apache-tomcat-7.0.59/bin
./startup.sh

wait
echo "tomcat startup ......... Done!"


wait
# start Gulp
cd /Users/cz_michael/Google\ Drive/Work/workspace/HealthfullU2
gulp

