# Use OpenJDK 21 JDK slim image as the base image
FROM openjdk:21-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/my-config-server-app.jar my-config-server-app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8888

# Run the JAR file
ENTRYPOINT ["java", "-jar", "my-config-server-app.jar"]
