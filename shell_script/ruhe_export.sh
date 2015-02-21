#!/bin/bash
NOW=$(date +"%m-%d-%Y")
REMOVE=$(date --date='6 day ago' +"%m-%d-%Y")
BACKUP_SERVER_IP=96.46.13.85
mysql_script_dir=/usr/local/mysql_script
backupFile=$mysql_script_dir/ruhe_all_$NOW.sql

# ========= Backup mysql with mysqldump ======
mysqldump -uroot -pZ93j31_h --add-drop-table --add-locks --quick ruhe > $backupFile
scp $backupFile root@$BACKUP_SERVER_IP:/usr/local/mysql_script

# ========= Read the mysqldump on backup server ======
ssh root@$BACKUP_SERVER_IP "mysql -uroot -pZ93j31_h ruhe < $backupFile"

# =========== Remove older backup files =======
removeFile=$mysql_script_dir/ruhe_all_$REMOVE.sql
rm -rf $removeFile
ssh root@$BACKUP_SERVER_IP "rm -rf $removeFile"

