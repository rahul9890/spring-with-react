## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 
- PostgreSQL
- Apache Kafka 4.x (KRaft mode — no Zookeeper required)

## 🧰 Built With

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Apache Kafka (Spring Kafka)
- Lombok
- AOP for Logging
- Filters with logback-springg.xml logging patterns

## 🟢 Start the Kafka broker (do this BEFORE `./gradlew bootRun`)

The app connects to a broker at `localhost:9092` (see `application.properties`). If the broker is not running, you will see endless `Bootstrap broker localhost:9092 ... could not be established` warnings and `/kafka/publish` will silently buffer in the producer with nothing ever reaching `/kafka/messages`.

Kafka is extracted locally at `F:\Softwares\kafka_2.13-4.2.0`. Kafka 4.x runs in KRaft mode (no Zookeeper).

### One-time setup — format the storage directory

Expected output: `Formatting metadata directory ... with metadata.version ...`.

### Every time you want to start the broker just click on that run button below command

```shell
& "F:\Softwares\kafka_2.13-4.2.0\bin\windows\kafka-server-start.bat" "F:\Softwares\kafka_2.13-4.2.0\config\server.properties"
```

Leave that terminal open — Kafka runs in the foreground. When you see `Kafka Server started`, the broker is ready. Then in a **separate terminal**, start the Spring app:

```powershell
./gradlew bootRun
```

In the Spring app logs you should see `Cluster ID:` shortly after startup, and the `Bootstrap broker ... disconnected` warnings should stop.

### Stop the broker

`Ctrl+C` in the Kafka window, or run:

```powershell
& "F:\Softwares\kafka_2.13-4.2.0\bin\windows\kafka-server-stop.bat"
```

### Try the Kafka endpoints

```powershell
# Publish a message
curl -X POST http://localhost:8080/kafka/publish -H "Content-Type: application/json" -d '{\"message\":\"hello\"}'

# Read the consumer buffer
curl http://localhost:8080/kafka/messages
```

In the Spring logs you'll see the controller entry, the producer publish + broker ack (with the assigned `partition`/`offset`), and the consumer pickup.

### Windows gotchas

- Default `log.dirs` is `C:\tmp\kraft-combined-logs`. To keep data on F:, edit `F:\Softwares\kafka_2.13-4.2.0\config\server.properties` and change `log.dirs=/tmp/kraft-combined-logs` to `log.dirs=F:/kafka-data` (forward slashes) **before** running the format command.
- Kafka on Windows occasionally fails to delete old log segments (JVM file-lock quirk). If the broker wedges on startup, stop it and delete the `log.dirs` folder to reset.

About the repository:
 * Its a backend repo which have multiple endpoints to support various UI application 
 function.
 * This repo uses postgres DB and uses spring JPA.
 * This repo have AOP for logging
   * How to add fields in logs:
   * - If you want to add a field to appear in logs, just add it to MDC like this:
  *     MDC.put("YourKey", "yourValue");
  * - If you want it to appear in a fixed order (before others), also add it to DEFAULT_ORDERED_FIELDS.
  * - No need to modify logback-spring.xml for individual fields. All MDC fields (ordered and unordered) will be printed automatically.
  *
  * Example output:
  *     ip=192.168.1.2, Correlation-ID=abc123, CustomerId=CUST001, SessionId=xyz789, User-Agent=Postman


Endpoints:

* UserController:
        🔹 POST /users
        Description: Creates a new user.
        🔹 GET /users
        Description: Fetches a list of all registered users.
        Response: List<UserResponse>
        🔹 PUT /users
        Description: Updates an existing user's details.
        🔹 POST /users/authentication
        Description: Authenticates a user using email and password.
        Response: UserResponse (authenticated user details if successful)
        🔹 DELETE /users
        Description: Deletes a user by email.
        Response: HTTP 200 OK on successful deletion

* UserDocumentController
        🔹 POST /userdocument
          Description: Uploads a document for a user using the provided email, file name, file type, and file data.
          Parameters (form-data): 
            userEmail: Email of the user
            fileName: Name of the file
            fileType: MIME type of the file
            fileData: File to upload (as MultipartFile)
            Response: UploadUserDocumentResp (details of the uploaded document)
        🔹 GET /userdocument
            Description: Retrieves a list of all uploaded user documents.
            Response: List<UploadUserDocumentResp>


* UserGoalsController:
      🔹 PUT /user/goals
          Description: Creates or updates goals for a user.
          Response: 201 Created on success, 400 Bad Request on failure with appropriate message.
      🔹 GET /user/goals?userId=<uuid>
      Description: Retrieves all goals associated with a specific user based on their userId.
      Query Parameter:
      userId: UUID of the user
      Response: List<UserGoalsResp>