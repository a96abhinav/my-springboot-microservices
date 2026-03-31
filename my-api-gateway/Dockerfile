# Use OpenJDK 21 JDK slim image as the base image
FROM eclipse-temurin:21-jdk-jammy

# Install curl
RUN apt-get update && apt-get install -y curl

# Set the working directory in the container
WORKDIR /app

# Copy the application JAR file into the container
COPY target/my-api-gateway-app.jar my-api-gateway-app.jar

# Expose the port your Spring Boot app runs on
EXPOSE 8081

# Run the JAR file
ENTRYPOINT ["java", "-jar", "my-api-gateway-app.jar"]
