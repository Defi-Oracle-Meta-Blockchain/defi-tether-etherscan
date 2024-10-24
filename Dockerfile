# Multi-stage Dockerfile for optimized build

# Stage 1: Build the application
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app

# Copy only the necessary files to leverage Docker cache
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Create a lightweight image for running the application
FROM openjdk:11-jre-slim
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/defi-oracle-meta-mainnet-1.0.0.jar /app/defi-oracle-meta-mainnet-1.0.0.jar

# Expose the application port
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "/app/defi-oracle-meta-mainnet-1.0.0.jar"]
