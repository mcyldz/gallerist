# Car Sales Management
Gallerist is a car dealership management system built with Java and Spring Boot. It enables vehicle sales in both Turkish Lira (TL) and US Dollars (USD) by dynamically fetching real-time exchange rates from the Central Bank of Turkey (TCMB) API. The system ensures data integrity and security using PostgreSQL, Spring Security, and JWT Authentication. Exception management is implemented for error handling and a seamless user experience.

## 💻 Technologies

- **Backend:**
  - Java 17
  - Spring Boot 3.x
  - Spring Data JPA
  - Spring Web
  - Spring Security
  
- **Database:**
  - PostgreSQL

- **Security:**
  - JWT (JSON Web Token) Authentication
  
- **Integrations:**
  - TCMB API (Live currency exchange rates)
  
- **Testing:**
  - JUnit 5
  - Mockito
  
- **Documentation:**
  - Swagger/OpenAPI

## ⚡Features

- Vehicle sales management in both TL and USD  
- Real-time currency conversion using TCMB API  
- Secure authentication and authorization with JWT  
- Exception management for error handling  
- RESTful API endpoints for easy integration  
- Scalable and maintainable architecture

## 📌 Project Scope  
This project is designed purely as a backend system. It does not include a frontend interface but provides well-structured APIs that can be integrated with any frontend application.  

## 🔐 Security  
- User authentication and authorization are managed with **Spring Security** and **JWT Authentication** to ensure a secure application environment.  

## 🔧 Setup & Installation  
1. Clone the repository:  
   ```
   git clone https://github.com/your-username/gallerist.git
   ```

2. Navigate to the project directory:
    ```
    cd gallerist
    ```

3. Configure the database settings in application.properties

4. Run the application using:
    ```
    mvn spring-boot:run
    ```

📜 License
    ```
    This project is open-source and available under the MIT License.
    ```
