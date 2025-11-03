# ===== Stage 1: Build =====
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app

COPY . .

# Give permission to gradlew
RUN chmod +x ./gradlew

# Build the project
RUN ./gradlew clean build -x test

# ===== Stage 2: Run =====
FROM eclipse-temurin:17-jre

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]