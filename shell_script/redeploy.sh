#!/bin/bash

# shutdown tomcat
cd /usr/local/tomcat-7.0.12/bin
./shutdown.sh

sleep 5

# remove older war and cache in work/Catalina folder
rm -rf /usr/local/tomcat-7.0.12/webapps/ROOT
rm -rf /usr/local/tomcat-7.0.12/webapps/Ruhe_Wiki
rm -rf /usr/local/tomcat-7.0.12/work/Catalina


# pause 5 seconds until tomcat shutdown finish.
sleep 5

# startup tomcat
cd /usr/local/tomcat-7.0.12/bin
./startup.sh
echo "restart tomcat done."