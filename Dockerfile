# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged Spring Boot application to the container
COPY target/dh-webflux-0.0.1-SNAPSHOT.jar dh-webflux.jar

# Expose the port that the application will run on
EXPOSE 8080

# Set the entry point for the container
ENTRYPOINT ["java", "-jar", "dh-webflux.jar"]