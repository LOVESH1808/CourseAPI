
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
{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "password123"
}

**Response**

{
  "id": 1,
  "name": "John Doe",
  "email": "john@example.com",
  "role": "USER"
}
