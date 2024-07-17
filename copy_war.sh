#!/bin/bash


source_file="/home/owner/Workspace/website/Todo/build/libs/Todo.war"
destination="/home/owner/apache-tomcat-10.1.19/webapps/Todo.war"

cp "$source_file" "$destination"
