# Use a lightweight JDK 21 image (appropriate for Spring Boot 4.0/Spring 7)
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the built JAR file from your target folder into the container
# Replace 'daily-grind-0.0.1-SNAPSHOT.jar' with your actual JAR name if different
COPY target/*.jar app.jar

# Expose the port your app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]