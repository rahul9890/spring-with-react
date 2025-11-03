# ====== Stage 1: Build ======
FROM eclipse-temurin:17-jdk AS build

WORKDIR /app
COPY . .

# If Maven
# RUN mvn -ntp clean package -DskipTests

# If Gradle
RUN ./gradlew clean build -x test

# ====== Stage 2: Run ======
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy only the jar file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app/app.jar"]