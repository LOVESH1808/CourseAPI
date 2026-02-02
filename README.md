
A production-ready REST API built using Spring Boot, PostgreSQL, and JWT Authentication that enables course discovery, enrollment, progress tracking, and secure user management.

This project is fully documented using OpenAPI / Swagger, deployed on Railway, and can be tested entirely through Swagger UI.

This project integrates authentication, authorization, environment-based configuration, and cloud deployment making it a complete real-world backend system.

## 🚀 Live Deployment

**Base URL**
https://courseapi-production-b1da.up.railway.app

---

**Swagger UI**
https://courseapi-production-b1da.up.railway.app/swagger-ui/index.html


## 🔐 Authentication Overview (JWT)

The API uses **stateless JWT authentication**.

### Authentication Flow
1. User registers or logs in
2. API returns a JWT token
3. Token is supplied via Swagger using **Authorize 🔒**
4. Protected endpoints become accessible

**Authorization Header**
Authorization: Bearer <JWT_TOKEN>


---

## 📌 API ENDPOINTS

---

## 🔑 Authentication APIs

### ▶ Register User
POST /api/auth/register


**Request Body**
```json
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}
Response

{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "USER"
}
▶ Login User
POST /api/auth/login
Request Body

{
  "email": "john@example.com",
  "password": "password123"
}
Response

{
  "accessToken": "eyJhbGciOiJIUzI1NiJ9...",
  "tokenType": "Bearer"
}
📚 Course APIs
▶ Get All Courses (Public)
GET /api/courses
Response

[
  {
    "id": 1,
    "title": "Spring Boot Mastery",
    "description": "Learn Spring Boot from scratch"
  }
]
▶ Create Course (Protected)
POST /api/courses
Request Body

{
  "title": "Java Fundamentals",
  "description": "Core Java concepts"
}
Response

{
  "id": 2,
  "title": "Java Fundamentals",
  "description": "Core Java concepts"
}
🧩 Subtopic APIs
▶ Add Subtopic (Protected)
POST /api/subtopics
Request Body

{
  "courseId": 1,
  "title": "Spring Security Basics"
}
📝 Enrollment APIs
▶ Enroll in Course (Protected)
POST /api/enrollments
Request Body

{
  "courseId": 1
}
Response

{
  "enrollmentId": 10,
  "status": "ENROLLED"
}
📈 Progress APIs
▶ Update Progress (Protected)
POST /api/progress
Request Body

{
  "enrollmentId": 10,
  "completedSubtopics": 3
}
🔍 Search APIs
▶ Search Courses
GET /api/search?query=spring
🧠 Data Schemas (Entities)
👤 User
id: Long
name: String
email: String
password: String
role: Role
📘 Course
id: Long
title: String
description: String
🧩 Subtopic
id: Long
title: String
course: Course
📝 Enrollment
id: Long
user: User
course: Course
status: EnrollmentStatus
📊 Progress
id: Long
enrollment: Enrollment
completedSubtopics: int
