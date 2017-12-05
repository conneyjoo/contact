#!/bin/sh

SERVICE_NAME=contact

PATH_TO_JAR=$(pwd)/contact.jar

JVM_OPTS=""

JVM_DEBUG="-Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=9529"

EXEC_PARAMS="--spring.config.location=./application.yml --logging.config=./spring-logback.xml --logging.level.root=INFO --logging.file=/file-server/logs/bfs-$(date +%Y%m%d).log"

PID_PATH_NAME=/tmp/contact-pid

case $1 in
    start)
        echo "$SERVICE_NAME starting ..."
        if [ ! -f $PID_PATH_NAME ]; then
            if [ ! -f $2 ]; then
                nohup java $JVM_OPTS $JVM_DEBUG -jar $PATH_TO_JAR $EXEC_PARAMS >/dev/null 2>&1 &
                echo $! > $PID_PATH_NAME
                echo "$SERVICE_NAME started ..."
            else
                nohup java $JVM_OPTS -jar $PATH_TO_JAR $EXEC_PARAMS >/dev/null 2>&1 &
                echo $! > $PID_PATH_NAME
                echo "$SERVICE_NAME started ..."
            fi
        else
            echo "$SERVICE_NAME is already running ..."
        fi
    ;;
    stop)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stoping ..."
            kill -9 $PID;
            echo "$SERVICE_NAME stopped ..."
            rm $PID_PATH_NAME
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
    restart)
        if [ -f $PID_PATH_NAME ]; then
            PID=$(cat $PID_PATH_NAME);
            echo "$SERVICE_NAME stopping ...";
            kill -9 $PID;
            echo "$SERVICE_NAME stopped ...";
            rm $PID_PATH_NAME
            echo "$SERVICE_NAME starting ..."
            nohup java -jar $PATH_TO_JAR /tmp 2>> /dev/null >> /dev/null &
            echo $! > $PID_PATH_NAME
            echo "$SERVICE_NAME started ..."
        else
            echo "$SERVICE_NAME is not running ..."
        fi
    ;;
esac
