#!/bin/bash

REPOSITORY=/home/ec2-user/Source
PROJECT_NAME=open-badge-springboot2-webservice


echo "> build 파일 복사"
cp $REPOSITORY/zip/*.jar $REPOSITORY/


echo "> 현재 구동중 pid"

CURRENT_PID=$(pgrep -fl openBadge | grep jar | awk '{print $1}')

echo "> 현재 구동중 PID : $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
        echo "> 현재 구동중인 애플리케이션은 없습니다"
else
        echo "> kill -15 $CURRENT_PID"
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "> 새 애플리케이션 배포"
JAR_NAME=$(ls -tr $REPOSITORY/ | grep *.jar | tail -n 1)


echo "> JAR NAME: $JAR_NAME"

echo "> $JAR_NAME 에 실행권한 추가"
chmod +x $JAR_NAME

echo "> JAR 실행"
nohup java -jar \
-Dspring.config.location=classpath:/application.properties,/home/ec2-user/Source/application-real-db.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
