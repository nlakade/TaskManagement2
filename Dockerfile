# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="nlakade@cisco.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container
EXPOSE 8080

# The application's war file
ARG WAR_FILE=target/task-management.war

# Add the application's war to the container
ADD ${WAR_FILE} task-management.war

# Run the war file
ENTRYPOINT ["java","-jar","/task-management.war"]