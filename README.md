
A production-ready REST API built using Spring Boot, PostgreSQL, and JWT Authentication that enables course discovery, enrollment, progress tracking, and secure user management.

This project is fully documented using OpenAPI / Swagger, deployed on Railway, and can be tested entirely through Swagger UI.

This project integrates authentication, authorization, environment-based configuration, and cloud deployment making it a complete real-world backend system.

## 🚀 Live Deployment

**Base URL**
https://courseapi-production-b1da.up.railway.app

---

**Swagger UI**
https://courseapi-production-b1da.up.railway.app/swagger-ui/index.html
## 🔑 Authentication APIs

### ➤ Register User
**POST** `/api/auth/register`

**Request Body**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}

```
**Request Response**
```json
{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "USER"
}
```
### ➤ Login User

**POST** `/api/auth/login`
```json
{
  "email": "john@example.com",
  "password": "password123"
}
```
**Request Response**
```json
{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
```
### 📚 Course APIs

➤ Get All Courses (Public)

**GET** `/api/courses`
**Request Response**
```json
[
  {
    "id": 1,
    "title": "Spring Boot Mastery",
    "description": "Learn Spring Boot from scratch"
  }
]
```
### ➤ Create Course (Protected)

**POST** `/api/courses`
**Request Body**
```json
{
  "title": "Java Fundamentals",
  "description": "Core Java concepts"
}
```
**Request Response**
```json
{
  "id": 2,
  "title": "Java Fundamentals",
  "description": "Core Java concepts"
}
```

### 🧩 Subtopic APIs
➤ Add Subtopic (Protected)
**POST** `/api/subtopics`

**Request Body**

```json
{
  "courseId": 1,
  "title": "Spring Security Basics"
}
```
### 📝 Enrollment APIs
➤ Enroll in Course (Protected)

**POST** `/api/enrollments`

**Request Body**
```json
{
  "courseId": 1
}
```
**Request Response**
```json
{
  "enrollmentId": 10,
  "status": "ENROLLED"
}
```

### 📈 Progress APIs
➤ Update Progress (Protected)

**POST** `/api/progress`

**Request Body**
```json
{
  "enrollmentId": 10,
  "completedSubtopics": 3
}
```
### 🔍 Search APIs
➤ Search Courses

**GET** `/api/search?query=spring`
The search uses case-insensitive matching and partial string comparison, allowing flexible queries without requiring full words or exact casing.


