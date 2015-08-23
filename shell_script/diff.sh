#!/bin/bash

src='/Users/cz_michael/Google Drive/Work/workspace/HealthfullU2/src/main/webapp/static'
local_server="/usr/local/apache-tomcat-7.0.59/webapps/ROOT/static"
diff -rq "$src" "$local_server" 