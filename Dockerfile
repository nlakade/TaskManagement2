# # Start with a base image containing Java runtime
# FROM openjdk:8-jdk-alpine

# # Add Maintainer Info
# LABEL maintainer="nlakade@cisco.com"

# # Add a volume pointing to /tmp
# VOLUME /tmp

# # Make port 8080 available to the world outside this container
# EXPOSE 8080

# # The application's war file
# ARG WAR_FILE=target/task-management-0.0.1-SNAPSHOT.war

# # Add the application's war to the container
# ADD ${WAR_FILE} task-management-0.0.1-SNAPSHOT.war

# # Run the war file
# ENTRYPOINT ["java","-jar","/task-management-0.0.1-SNAPSHOT.war"]


# Start with a base image containing Java runtime
# FROM eclipse-temurin:11-jdk-alpine
FROM eclipse-temurin:21-alpine

# Add Maintainer Info
LABEL maintainer="nlakade@cisco.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8080 available to the world outside this container

EXPOSE 9090

# # The application's war file
# ARG WAR_FILE=target/task-management-0.0.1-SNAPSHOT.war

# # Add the application's war to the container
# ADD ${WAR_FILE} task-management-0.0.1-SNAPSHOT.war

ADD target/task-management-0.0.1-SNAPSHOT.war task-management-0.0.1-SNAPSHOT.war

# Run the war file
ENTRYPOINT ["java","-jar","/task-management-0.0.1-SNAPSHOT.war"]
