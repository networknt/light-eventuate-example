FROM openjdk:8-jre-alpine
EXPOSE 8083
ADD /target/eventuate-todo-command-service-0.1.0.jar server.jar
CMD ["/bin/sh","-c","java -Dlight-4j-config-dir=/config -jar /server.jar"]