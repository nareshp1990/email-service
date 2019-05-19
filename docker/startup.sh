#!/bin/bash
set -e  
echo "1"
# if $proxy_domain is not set, then default to $HOSTNAME
#export proxy_domain=${proxy_domain:-$HOSTNAME}
#echo $HOSTNAME
# ensure the following environment variables are set. exit script and container if not set.
test $ENV
export service_name=email-service

export app_path=/etc/conf/$service_name

rm -rf $app_path/bootstrap.properties

echo "spring.application.name=$service_name" >> $app_path/bootstrap.properties
echo "spring.cloud.consul.host=$consul_url" >> $app_path/bootstrap.properties
echo "spring.cloud.consul.port=$consul_port" >> $app_path/bootstrap.properties

export jvm_options=" -Dfile.encoding=UTF-8 -Dspring.config.location=$app_path/  -Dlogging.config=$app_path/logback-spring.xml -Dlogfile.location=/var/log/$service_name/$service_name.log  -Dspring.profiles.active=$ENV";

echo "Staring $service_name"

exec java $jvm_options -jar $service_name.jar