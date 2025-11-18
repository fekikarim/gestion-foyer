# 🏢 Gestion Foyer Universitaire

<p align="center">
  <img src="https://img.shields.io/badge/Spring%20Boot-3.5.7-brightgreen.svg" alt="Spring Boot">
  <img src="https://img.shields.io/badge/Java-17-orange.svg" alt="Java">
  <img src="https://img.shields.io/badge/MySQL-8.0-blue.svg" alt="MySQL">
  <img src="https://img.shields.io/badge/Maven-3.6+-red.svg" alt="Maven">
  <img src="https://img.shields.io/badge/License-Educational-lightgrey.svg" alt="License">
</p>

<p align="center">
  <img src="https://spring.io/images/spring-boot-logo-9146a4d3298760c2e7e49595184e1972.svg" alt="Spring Boot Logo" width="200">
</p>

## 📋 Description

**Gestion Foyer Universitaire** is a comprehensive enterprise web application built with Spring Boot to streamline dormitory room reservation processes for university students. This application provides a robust REST API for managing universities, dormitories (foyers), blocks, rooms, students, and reservations with advanced features including Aspect-Oriented Programming (AOP) for performance monitoring and scheduled tasks for automated reporting.

## 🛠️ Technologies Utilisées

- **Java 17** - Programming language
- **Spring Boot 3.5.7** - Framework for building the application
- **Spring Data JPA** - Data access layer
- **Spring AOP** - Aspect-Oriented Programming for cross-cutting concerns
- **MySQL Database** - Relational database management system
- **Lombok** - Java library to reduce boilerplate code
- **SpringDoc OpenAPI 2.2.0** - API documentation and Swagger UI
- **Maven** - Build automation and dependency management
- **AspectJ** - Aspect-oriented programming support

## 📁 Project Structure

```
gestion-foyer/
├── src/
│   ├── main/
│   │   ├── java/org/example/gestionfoyer/
│   │   │   ├── GestionFoyerApplication.java
│   │   │   ├── aspect/
│   │   │   │   └── ExecutionTimeAspect.java
│   │   │   ├── config/
│   │   │   │   ├── SchedulerConfig.java
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── controllers/
│   │   │   │   ├── AOPRestController.java
│   │   │   │   ├── BlocRestController.java
│   │   │   │   ├── ChambreRestController.java
│   │   │   │   ├── EtudiantRestController.java
│   │   │   ├── FoyerRestController.java
│   │   │   ├── ReservationRestController.java
│   │   │   ├── SchedulerRestController.java
│   │   │   └── UniversiteRestController.java
│   │   │   ├── entities/
│   │   │   │   ├── Bloc.java
│   │   │   │   ├── Chambre.java
│   │   │   │   ├── Etudiant.java
│   │   │   │   ├── Foyer.java
│   │   │   │   ├── Reservation.java
│   │   │   │   ├── TypeChambre.java
│   │   │   │   └── Universite.java
│   │   │   ├── repositories/
│   │   │   │   ├── BlocRepository.java
│   │   │   │   ├── ChambreRepository.java
│   │   │   │   ├── EtudiantRepository.java
│   │   │   │   ├── FoyerRepository.java
│   │   │   │   ├── ReservationRepository.java
│   │   │   │   └── UniversiteRepository.java
│   │   │   ├── services/
│   │   │   │   ├── BlocServiceImpl.java
│   │   │   │   ├── ChambreServiceImpl.java
│   │   │   │   ├── EtudiantServiceImpl.java
│   │   │   │   ├── FoyerServiceImpl.java
│   │   │   │   ├── IBlocService.java
│   │   │   │   ├── IChambreService.java
│   │   │   │   ├── IEtudiantService.java
│   │   │   │   ├── IFoyerService.java
│   │   │   │   ├── IReservationService.java
│   │   │   ├── IUniversiteService.java
│   │   │   ├── ReservationServiceImpl.java
│   │   │   ├── SchedulerService.java
│   │   │   └── UniversiteServiceImpl.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/org/example/gestionfoyer/
│           └── GestionFoyerApplicationTests.java
├── pom.xml
├── README.md
└── database_script.sql
```

## 🚀 Installation and Setup

### Prerequisites
- **JDK 17** or higher
- **Maven 3.6+**
- **MySQL Server 8.0+**
- **IDE** (IntelliJ IDEA, Eclipse, VS Code) recommended

### Step 1: Clone the Repository
```bash
git clone https://github.com/fekikarim/gestion-foyer.git
cd gestion-foyer
```

### Step 2: Database Configuration
1. Start MySQL Server
2. Create the database (optional, as `createDatabaseIfNotExist=true`):
```sql
CREATE DATABASE gestion_foyer;
```
3. Update database credentials in `src/main/resources/application.properties` if needed:
```properties
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Step 3: Install Dependencies
```bash
mvn clean install
```

### Step 4: Run the Application
```bash
mvn spring-boot:run
```

Alternatively, run from your IDE:
- Execute the `GestionFoyerApplication.java` class

## 🌐 Application Access

### Important URLs
- **Application Base**: http://localhost:8080/gestion-foyer
- **Swagger UI**: http://localhost:8080/gestion-foyer/swagger-ui.html
- **API Documentation (JSON)**: http://localhost:8080/gestion-foyer/api-docs

## 📊 Data Model

### Entity Relationships
1. **Universite ↔ Foyer**: Bidirectional OneToOne relationship
2. **Foyer → Bloc**: OneToMany relationship
3. **Bloc → Chambre**: OneToMany relationship
4. **Chambre → Reservation**: OneToMany relationship
5. **Etudiant ↔ Reservation**: Bidirectional ManyToMany relationship

### Room Types (Enum)
- `SIMPLE` - Single occupancy
- `DOUBLE` - Double occupancy
- `TRIPLE` - Triple occupancy

## 🔧 API Endpoints

### University Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/universite/retrieve-all-universites` | Retrieve all universities |
| GET | `/universite/retrieve-universite/{universite-id}` | Retrieve a university by ID |
| POST | `/universite/add-universite` | Add a new university |
| PUT | `/universite/update-universite` | Update an existing university |
| PUT | `/universite/affecter-foyer/{foyer-id}/{nom-universite}` | Assign a foyer to a university |

### Foyer Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/foyer/retrieve-all-foyers` | Retrieve all foyers |
| GET | `/foyer/retrieve-foyer/{id}` | Retrieve a foyer by ID |
| POST | `/foyer/add-foyer` | Add a new foyer |
| PUT | `/foyer/update-foyer` | Update an existing foyer |
| DELETE | `/foyer/remove-foyer/{id}` | Delete a foyer |

### Block Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/bloc/retrieve-all-blocs` | Retrieve all blocks |
| GET | `/bloc/retrieve-bloc/{id}` | Retrieve a block by ID |
| POST | `/bloc/add-bloc` | Add a new block |
| PUT | `/bloc/update-bloc` | Update an existing block |
| DELETE | `/bloc/remove-bloc/{id}` | Delete a block |

### Room Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/chambre/retrieve-all-chambres` | Retrieve all rooms |
| GET | `/chambre/retrieve-chambre/{id}` | Retrieve a room by ID |
| POST | `/chambre/add-chambre` | Add a new room |
| PUT | `/chambre/update-chambre` | Update an existing room |

### Student Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/etudiant/retrieve-all-etudiants` | Retrieve all students |
| GET | `/etudiant/retrieve-etudiant/{id}` | Retrieve a student by ID |
| POST | `/etudiant/add-etudiants` | Add new students |
| PUT | `/etudiant/update-etudiant` | Update an existing student |
| DELETE | `/etudiant/remove-etudiant/{id}` | Delete a student |

### Reservation Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/reservation/retrieve-all-reservations` | Retrieve all reservations |
| GET | `/reservation/retrieve-reservation/{id}` | Retrieve a reservation by ID |
| PUT | `/reservation/update-reservation` | Update an existing reservation |

### AOP Testing
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/aop/test-execution-time/{chambre-id}/{cin-etudiant}` | Test AOP aspect by adding a reservation |

### Scheduler Management
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/scheduler/display-chambres-non-reservees` | Manually trigger scheduler to display unreserved rooms |

## 📝 API Request Examples

### Add University (POST)
```json
{
  "nomUniversite": "ESPRIT",
  "adresse": "Tunis, Tunisia"
}
```

### Add Foyer (POST)
```json
{
  "nomFoyer": "Foyer El Manar",
  "capaciteFoyer": 500
}
```

### Add Block (POST)
```json
{
  "nomBloc": "Block A",
  "capaciteBloc": 100
}
```

### Add Room (POST)
```json
{
  "numeroChambre": 101,
  "typeC": "DOUBLE"
}
```

### Add Students (POST)
```json
[
  {
    "nomEt": "Ben Ali",
    "prenomEt": "Ahmed",
    "cin": 12345678,
    "ecole": "ESPRIT",
    "dateNaissance": "2000-01-15"
  },
  {
    "nomEt": "Trabelsi",
    "prenomEt": "Fatma",
    "cin": 87654321,
    "ecole": "ESPRIT",
    "dateNaissance": "2001-05-20"
  }
]
```

### Add Reservation (POST)
```json
{
  "idReservation": "RES-2024-001",
  "anneeUniversitaire": "2024-09-01",
  "estValide": true
}
```

## ⚡ Advanced Features

### Aspect-Oriented Programming (AOP)
- **Execution Time Monitoring**: Automatically logs execution time for reservation operations
- **Method Tracing**: Tracks method calls with parameters and results
- **Performance Insights**: Helps identify bottlenecks in the application

### Scheduled Tasks
- **Daily Reports**: Automatically generates reports of unreserved rooms at 2:00 AM daily
- **University-wise Analysis**: Provides detailed breakdown by university and room type
- **Logging Integration**: Comprehensive logging for monitoring and debugging

## 🧪 Testing with Postman

### Postman Collection
Import the provided `Gestion-Foyer-Postman-Collection.json` into Postman to test all endpoints.

1. Open Postman
2. Import the collection file
3. Set base URL: `http://localhost:8080/gestion-foyer`
4. Test the endpoints in the recommended order

### Recommended Testing Order
1. Add a University
2. Add a Foyer
3. Assign Foyer to University (using `/universite/affecter-foyer`)
4. Add Blocks to the Foyer
5. Add Rooms to Blocks
6. Add Students
7. Create Reservations
8. Test AOP functionality with `/aop/test-execution-time`
9. Test scheduler with `/scheduler/display-chambres-non-reservees`
10. Perform GET, PUT, DELETE operations

## 🐛 Troubleshooting

### Application Won't Start
- Verify MySQL server is running
- Check database credentials in `application.properties`
- Ensure port 8080 is not in use

### Database Connection Issues
- Confirm MySQL server is accessible
- Validate connection URL, username, and password
- Check if database exists (or set `createDatabaseIfNotExist=true`)

### Lombok Not Working
1. Install Lombok plugin in your IDE
2. Enable Annotation Processing:
   - **IntelliJ IDEA**: Settings → Build → Compiler → Annotation Processors → Enable annotation processing
3. Rebuild the project

### AOP/Scheduler Not Working
- Ensure `@EnableAspectJAutoProxy` is configured (Spring Boot auto-configures)
- Verify `@EnableScheduling` is active in `SchedulerConfig`
- Check logs for any configuration errors

## 📚 Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA Reference](https://spring.io/projects/spring-data-jpa)
- [Spring AOP Documentation](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#aop)
- [SpringDoc OpenAPI](https://springdoc.org/)
- [Lombok Features](https://projectlombok.org/features/)
- [MySQL Documentation](https://dev.mysql.com/doc/)

## 👤 Contact

For questions, suggestions, or collaboration opportunities, feel free to reach out:

- **Email**: [feki.karim28@gmail.com](mailto:feki.karim28@gmail.com)
- **LinkedIn**: [Karim Feki](https://www.linkedin.com/in/karimfeki)
- **GitHub**: [Karim Feki](https://github.com/fekikarim)

## 📄 License

This project is developed for educational purposes as part of the "Architecture des SI II" course at ESPRIT.

---

**Note**: This application demonstrates advanced Spring Boot concepts including REST APIs, JPA relationships, AOP, and scheduled tasks for comprehensive dormitory management.