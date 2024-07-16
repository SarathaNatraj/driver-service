# Stage 1: Build the application
FROM maven:3.8.1-jdk-11 AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code and build the application
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the built jar file from the build stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8090

# Define the entrypoint to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
