# Multi-stage Dockerfile for optimized build
FROM maven:3.8.1-openjdk-11 AS build
WORKDIR /app
COPY . .
RUN mvn clean package

FROM openjdk:11-jre-slim
WORKDIR /app
COPY --from=build /app/target/defi-oracle-meta-mainnet-1.0.0.jar /app/
CMD ["java", "-jar", "defi-oracle-meta-mainnet-1.0.0.jar"]
