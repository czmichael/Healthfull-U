#!/bin/bash

rsync --progress /Users/cz_michael/Desktop/workspace/Message_Resource_Struts2_Ant/dist/Ruhe_Wiki.war root@116.255.139.100:/usr/local/tomcat-7.0.12/webapps/Ruhe_Wiki.war
echo "Done."

sleep 3

echo "running redeploy.sh on staging server..."
ssh root@116.255.139.100 'source /etc/profile; /usr/local/shell_script/redeploy.sh'
echo "Done."