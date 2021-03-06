# Start with a base image containing Java runtime
FROM openjdk:11-jdk-alpine

# Add Maintainer Info
MAINTAINER Alessandro Piccolo <alessandro.piccolo@hotmail.se>

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 since it is a web-service
EXPOSE 8080

# The application's jar file
ARG JAR_FILE=target/foxlight-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} foxlight.jar

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/foxlight.jar"]