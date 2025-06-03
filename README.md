## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 
- PostgreSQL

## 🧰 Built With

- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- AOP for Logging


About the repository:
 * Its a backend repo which have multiple endpoints to support various UI application 
 function.
 * This repo uses postgres DB and uses spring JPA.
 * This repo have AOP for logging

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