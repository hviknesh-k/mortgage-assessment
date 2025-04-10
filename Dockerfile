# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app
# Copy the project JAR file into the container
COPY target/assessment-0.0.1-SNAPSHOT.jar app.jar
# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]