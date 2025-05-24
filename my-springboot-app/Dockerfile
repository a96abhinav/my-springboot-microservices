# Use OpenJDK 21 JDK slim image as the base image
FROM openjdk:21-jdk-slim

# Install curl
RUN apt-get update && apt-get install -y curl

# Set the working directory in the container
WORKDIR /app

# ADD the application JAR file into the container
ADD target/my-spring-boot-app.jar my-springboot-app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8085

# Run the JAR file
ENTRYPOINT ["java", "-jar", "my-springboot-app.jar"]
