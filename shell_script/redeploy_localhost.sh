#!/bin/bash

# shutdown tomcat
cd /usr/local/tomcat-7.0.12/bin
./shutdown.sh

sleep 5

# remove older war
rm -rf /usr/local/tomcat-7.0.12/webapps/ROOT
rm -rf /usr/local/tomcat-7.0.12/webapps/Ruhe_Wiki
rm -rf /usr/local/tomcat-7.0.12/work/Catalina

# move the new app (.war) into webapps folder
# cp /Users/cz_michael/Desktop/workspace/Message_Resource_Struts2_Ant/dist/Ruhe_Wiki.war /usr/local/tomcat-7.0.12/webapps/


rsync --progress /Users/cz_michael/Desktop/workspace/Message_Resource_Struts2_Ant/dist/Ruhe_Wiki.war /usr/local/tomcat-7.0.12/webapps/Ruhe_Wiki.war


echo "rsync RuheWiki.war done."


# pause 5 seconds until tomcat shutdown finish.
sleep 5

# startup tomcat
cd /usr/local/tomcat-7.0.12/bin
./startup-jrebel.sh
echo "restart tomcat with jrebel done."