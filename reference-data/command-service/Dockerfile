
FROM openjdk:8-jre-alpine
EXPOSE 8080
ADD /target/eventuate-reference-command-service-0.1.0.jar server.jar
CMD ["/bin/sh","-c","java -Dlight-4j-config-dir=/config -jar /server.jar"]