# Student Management System API

##Description
This is a RESTful API built using Spring Boot to manage student data. It supports CRUD operations, validation, pagination, sorting, and search functionality. The project uses MySQL for persistent storage.



##Features
- Create, Read, Update, Delete (CRUD) operations
- Input validation using annotations
- Global exception handling
- Pagination and sorting
- Search students by name
- MySQL database integration



##Tech Stack
- Java
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

---

##API Endpoints

### Create Student
POST /students

### Get All Students
GET /students?page=0&size=5

### Get Student by ID
GET /students/{id}

### Update Student
PUT /students/{id}

### Delete Student
DELETE /students/{id}

### Search Student
GET /students/search?name=piyush

---

##How to Run

1. Clone the repository
2. Create MySQL database: student_db
3. Rename application-example.properties to application.properties
4. Update database username and password
5. Run the application

---

## 👨‍💻 Author
Vaibhav Kumar
