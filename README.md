# Anixe-assignment
assignment for anixe interview
This is a Java 8+ backend application, Spring Boot 2 & Hibernate and the in-memory relational
database H2. In order to run the application you will need JDK 8, Lombok Support for your IDE.

### 1. Clone the git repository:
git clone https://github.com/marianna-tavlopoulou/anixe-assignment.git
### 2. Run Gradle build the app:
gradlew clean build
### 3. Run the application with Gradle SpringBoot task:
gradlew bootRun
### 4. Check the health status of your application:
curl -X GET http://localhost:9000/assignment-app/monitoring/health
### 5. The application is running with H2 persistent database. Connect with h2 db with the following link:
http://localhost:8080/assignment-app/h2-console
### 6. API Documentation is available through the link below:
http://localhost:8080/assignment-app/swagger-ui.html
### 8. Having run the app with local profile, you can find the logs in location:
C:\var\log\tavlopoulou-assignment
