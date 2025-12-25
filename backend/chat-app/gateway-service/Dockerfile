# Stage 1: Build all modules
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Stage 2: Run gateway-service
FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY --from=builder /app/gateway-service/target/gateway-service-*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
