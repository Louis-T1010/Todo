FROM tomcat:latest

RUN rm -rf /usr/local/tomcat/webapps/*
COPY ./home/kingsley/personal/Todo /usr/local/tomcat/webapps/ROOT
