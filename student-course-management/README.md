# Student Course Management

## How to run

1. Make sure you have MySQL running and accessible.
2. Default DB settings (in `application.properties`):
   - URL: jdbc:mysql://localhost:3306/student_course_db
   - Username: root
   - Password: root
3. Build and run:
   - mvn clean package
   - mvn spring-boot:run
4. Swagger UI: http://localhost:8080/swagger-ui/index.html

## Endpoints examples

- POST /courses/add
  {
    "courseName":"Java Fundamentals",
    "instructor":"Dr. Sharma"
  }

- POST /students/add
  {
    "name":"Sourabh",
    "email":"sourabh@example.com",
    "city":"Indore",
    "courseId":1
  }
