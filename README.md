# External API Sync Application

A Spring Boot application that consumes user data from an external API and stores it into a local database. The project also provides REST endpoints to fetch users in full or restricted format.

---

## 🚀 Features

- Fetch user data from [JSONPlaceholder API](https://jsonplaceholder.typicode.com/users)
- Persist users, addresses, geo, and company information in local database
- Expose REST endpoints to:
  - Retrieve all users
  - Retrieve a user by ID
  - Retrieve a user by company name
  - Retrieve restricted user data (ID, name, email, phone, company name)
- Manual and optional automatic synchronization of external API data to database using scheduler

---

## 🔗 REST API Endpoints

| Method | Endpoint | Description | Example Response |
|--------|---------|-------------|-----------------|
| **GET** | `/api/user` | Get all users from external API | Full user JSON objects |
| **GET** | `/api/user/{id}` | Get user by ID from external API | Full user JSON object |
| **GET** | `/api/company/{name}` | Get the first user by company name from external API | Full user JSON object |
| **GET** | `/api/restict` | Get restricted user data (ID, name, email, phone, company name) | `[{"id":1,"name":"Leanne Graham","email":"Sincere@april.biz","phone":"1-770-736-8031 x56442","companyName":"Romaguera-Crona"}, ...]` |
| **GET** | `/api/sync-users` | Manually sync users from external API into the database | `"Synced successfully"` |
| **GET** | `/api/users-db` *(optional)* | Get all users stored in the local database | List of User JSON objects |

---

## 🏗️ Tech Stack

- Java 17+
- Spring Boot
- Spring Data JPA
- H2 / MySQL (any relational DB)
- RestTemplate for external API consumption
- Lombok for boilerplate reduction
