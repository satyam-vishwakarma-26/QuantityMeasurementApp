# 🚀 Quantity Measurement App 

- Conversion between different units (e.g., cm ↔ m, kg ↔ g)  
- Comparison of quantities across units  
- Arithmetic operations like addition and subtraction of quantities  
- Handling of multiple measurement domains such as:
  - Length  
  - Weight  
  - Volume  
  - Temperature  

This ensures **accuracy, consistency, and flexibility** in handling real-world measurement problems.
---

This repository contains the Spring Boot backend for the Quantity Measurement Application.
The application is designed to perform **accurate unit measurement conversions and arithmetic operations on quantities** such as length, weight, volume, and temperature.  
It also integrates a **secure authentication system using Spring Security, JWT, and Google OAuth2** to ensure safe and scalable user access.

---

## 📏 About Quantity Measurement  

The Quantity Measurement system enables:

## ⚙️ Core Functionalities  

- 📏 Unit measurement & conversion  
- ➕ Arithmetic operations on quantities  
- 🔐 Secure authentication with JWT & OAuth2  
- 👤 User management system  
- 🚫 Unauthorized access handling (401 / 403)  

---

## ✨ Features Implemented

### 🔐 Authentication & Security
- Spring Security integration  
- JWT (JSON Web Token) based authentication  
- Secure REST APIs  
- Role-based access (if implemented)  

### 🌐 OAuth2 Login
- Google Authentication using OAuth2  
- Seamless login/signup with Google account  
- Automatic user registration on first login  

### 👤 User Management
- User entity and database integration  
- Store authenticated user details  
- Manage user sessions securely  

### ⚙️ Backend Functionalities
- RESTful API design  
- Exception handling  
- Secure endpoints  
- Token validation and filtering  

---

## 🛠️ Tech Stack

| Technology       | Description                              |
|------------------|-------------------------------------------|
| Java             | Programming Language                      |
| Spring Boot      | Backend Framework                         |
| Spring Security  | Authentication & Authorization            |
| JWT              | Token-based authentication                |
| OAuth2           | Google Login Integration                  |
| Hibernate / JPA  | ORM for database interaction              |
| MySQL / H2       | Database (configurable)                   |
| Maven            | Build Tool                                |

---

## 📁 Project Structure

    src/
     ├── controller        # REST Controllers
     ├── service           # Business Logic
     ├── repository        # Database Layer
     ├── model             # Entity Classes
     ├── security          # JWT & OAuth2 Configurations
     ├── config            # App Configurations
     └── exception         # Global Exception Handling

---

## 📂 API Endpoints (Sample)  

| Endpoint                      | Description                          |
|------------------------------|--------------------------------------|
| `/auth/login`                | JWT-based login                     |
| `/oauth2/authorize/google`   | Google OAuth2 login                 |
| `/auth/user`                 | Get authenticated user details      |
| `/api/**`                    | Protected APIs                      |

---

## 🔄 Authentication Flow

### 🔑 JWT Authentication Flow
- User sends login request  
- Backend validates credentials  
- JWT token is generated  
- Token is sent to client  
- Client includes token in headers for API requests  
- Backend validates token for each request  

### 🌍 Google OAuth2 Flow
- User clicks "Login with Google"  
- Redirect to Google Authentication page  
- Google verifies user credentials  
- Backend receives user details  
- User is registered (if new)  
- JWT token is generated and returned  

---

## 📊 Advantages  

- 🔐 High security (OAuth2 + JWT)  
- ⚡ Scalable (stateless backend)  
- 🧩 Clean and maintainable architecture  
- 🔄 Easy integration with frontend applications  
- 👨‍💻 Reduced backend complexity  

---

## 📌 Conclusion  

This project implements a **modern backend system** with:

- **Robust quantity measurement and conversion logic**
- **Secure authentication using OAuth2 and JWT**
- **Scalable and maintainable architecture using Spring Boot**

It is designed to be **production-ready, secure, and easily extendable**.

---

## 🔗 Repository Link  
👉 [View Code on GitHub](https://github.com/satyam-vishwakarma-26/QuantityMeasurementApp/tree/main)
