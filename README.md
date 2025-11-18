# 🏢 Gestion Foyer Universitaire - Application Spring Boot

## 📋 Description
Application web d'entreprise développée avec Spring Boot pour la gestion de foyers universitaires, permettant de simplifier le processus de réservation des chambres pour les étudiants.

## 🛠️ Technologies Utilisées
- **Java 17**
- **Spring Boot 3.1.5**
- **Spring Data JPA**
- **MySQL Database**
- **Lombok**
- **Swagger/OpenAPI 3**
- **Maven**

## 📁 Structure du Projet
```
gestion-foyer/
├── src/
│   ├── main/
│   │   ├── java/tn/esprit/gestionfoyer/
│   │   │   ├── GestionFoyerApplication.java
│   │   │   ├── config/
│   │   │   │   └── SwaggerConfig.java
│   │   │   ├── entities/
│   │   │   │   ├── Universite.java
│   │   │   │   ├── Foyer.java
│   │   │   │   ├── Bloc.java
│   │   │   │   ├── Chambre.java
│   │   │   │   ├── Etudiant.java
│   │   │   │   ├── Reservation.java
│   │   │   │   └── TypeChambre.java
│   │   │   ├── repositories/
│   │   │   │   ├── UniversiteRepository.java
│   │   │   │   ├── FoyerRepository.java
│   │   │   │   ├── BlocRepository.java
│   │   │   │   ├── ChambreRepository.java
│   │   │   │   ├── EtudiantRepository.java
│   │   │   │   └── ReservationRepository.java
│   │   │   ├── services/
│   │   │   │   ├── IUniversiteService.java
│   │   │   │   ├── UniversiteServiceImpl.java
│   │   │   │   ├── IFoyerService.java
│   │   │   │   ├── FoyerServiceImpl.java
│   │   │   │   ├── IBlocService.java
│   │   │   │   ├── BlocServiceImpl.java
│   │   │   │   ├── IChambreService.java
│   │   │   │   ├── ChambreServiceImpl.java
│   │   │   │   ├── IEtudiantService.java
│   │   │   │   ├── EtudiantServiceImpl.java
│   │   │   │   ├── IReservationService.java
│   │   │   │   └── ReservationServiceImpl.java
│   │   │   └── controllers/
│   │   │       ├── UniversiteRestController.java
│   │   │       ├── FoyerRestController.java
│   │   │       ├── BlocRestController.java
│   │   │       ├── ChambreRestController.java
│   │   │       ├── EtudiantRestController.java
│   │   │       └── ReservationRestController.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
├── pom.xml
└── README.md
```

## 🚀 Installation et Configuration

### Prérequis
- JDK 17 ou supérieur
- Maven 3.6+
- MySQL Server 8.0+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Étape 1: Cloner le projet
```bash
git clone <repository-url>
cd gestion-foyer
```

### Étape 2: Configuration de la base de données MySQL
1. Démarrer MySQL Server
2. Créer la base de données (optionnel, car createDatabaseIfNotExist=true):
```sql
CREATE DATABASE gestion_foyer;
```
3. Modifier `application.properties` si nécessaire:
```properties
spring.datasource.username=votre_username
spring.datasource.password=votre_password
```

### Étape 3: Installation des dépendances
```bash
mvn clean install
```

### Étape 4: Lancer l'application
```bash
mvn spring-boot:run
```

Ou depuis votre IDE:
- Exécuter la classe `GestionFoyerApplication.java`

## 🌐 Accès à l'application

### URLs importantes
- **Application**: http://localhost:8089/gestion-foyer
- **Swagger UI**: http://localhost:8089/gestion-foyer/swagger-ui.html
- **API Docs (JSON)**: http://localhost:8089/gestion-foyer/api-docs

## 📊 Modèle de Données

### Relations entre entités
1. **Universite ↔ Foyer**: OneToOne bidirectionnelle
2. **Foyer → Bloc**: OneToMany
3. **Bloc → Chambre**: OneToMany
4. **Chambre → Reservation**: OneToMany
5. **Etudiant ↔ Reservation**: ManyToMany bidirectionnelle

### Types de Chambre (Enum)
- SIMPLE
- DOUBLE
- TRIPLE

## 🔧 API Endpoints

### Gestion Université
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/universite/retrieve-all-universites` | Récupérer toutes les universités |
| GET | `/universite/retrieve-universite/{id}` | Récupérer une université |
| POST | `/universite/add-universite` | Ajouter une université |
| PUT | `/universite/update-universite` | Modifier une université |

### Gestion Foyer
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/foyer/retrieve-all-foyers` | Récupérer tous les foyers |
| GET | `/foyer/retrieve-foyer/{id}` | Récupérer un foyer |
| POST | `/foyer/add-foyer` | Ajouter un foyer |
| PUT | `/foyer/update-foyer` | Modifier un foyer |
| DELETE | `/foyer/remove-foyer/{id}` | Supprimer un foyer |

### Gestion Bloc
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/bloc/retrieve-all-blocs` | Récupérer tous les blocs |
| GET | `/bloc/retrieve-bloc/{id}` | Récupérer un bloc |
| POST | `/bloc/add-bloc` | Ajouter un bloc |
| PUT | `/bloc/update-bloc` | Modifier un bloc |
| DELETE | `/bloc/remove-bloc/{id}` | Supprimer un bloc |

### Gestion Chambre
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/chambre/retrieve-all-chambres` | Récupérer toutes les chambres |
| GET | `/chambre/retrieve-chambre/{id}` | Récupérer une chambre |
| POST | `/chambre/add-chambre` | Ajouter une chambre |
| PUT | `/chambre/update-chambre` | Modifier une chambre |

### Gestion Étudiant
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/etudiant/retrieve-all-etudiants` | Récupérer tous les étudiants |
| GET | `/etudiant/retrieve-etudiant/{id}` | Récupérer un étudiant |
| POST | `/etudiant/add-etudiants` | Ajouter des étudiants |
| PUT | `/etudiant/update-etudiant` | Modifier un étudiant |
| DELETE | `/etudiant/remove-etudiant/{id}` | Supprimer un étudiant |

### Gestion Réservation
| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/reservation/retrieve-all-reservations` | Récupérer toutes les réservations |
| GET | `/reservation/retrieve-reservation/{id}` | Récupérer une réservation |
| PUT | `/reservation/update-reservation` | Modifier une réservation |

## 📝 Exemples de requêtes

### Ajouter une Université (POST)
```json
{
  "nomUniversite": "ESPRIT",
  "adresse": "Tunis, Tunisie"
}
```

### Ajouter un Foyer (POST)
```json
{
  "nomFoyer": "Foyer El Manar",
  "capaciteFoyer": 500
}
```

### Ajouter un Bloc (POST)
```json
{
  "nomBloc": "Bloc A",
  "capaciteBloc": 100
}
```

### Ajouter une Chambre (POST)
```json
{
  "numeroChambre": 101,
  "typeC": "DOUBLE"
}
```

### Ajouter des Étudiants (POST)
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

### Ajouter une Réservation (POST)
```json
{
  "idReservation": "RES-2024-001",
  "anneeUniversitaire": "2024-09-01",
  "estValide": true
}
```

## 🧪 Tests avec Postman

### Collection Postman
Importez la collection suivante dans Postman pour tester tous les endpoints:

1. Ouvrir Postman
2. Créer une nouvelle collection "Gestion Foyer"
3. Ajouter les requêtes selon les endpoints ci-dessus
4. Base URL: `http://localhost:8089/gestion-foyer`

### Ordre recommandé pour les tests
1. Ajouter une Université
2. Ajouter un Foyer
3. Associer Foyer à Université (via update)
4. Ajouter un Bloc au Foyer
5. Ajouter des Chambres au Bloc
6. Ajouter des Étudiants
7. Créer des Réservations
8. Tester les opérations GET, PUT, DELETE

## 🐛 Dépannage

### Problème: Application ne démarre pas
- Vérifier que MySQL est démarré
- Vérifier les credentials dans `application.properties`
- Vérifier que le port 8089 n'est pas utilisé

### Problème: Erreur de connexion à la base de données
```
Solution: Vérifier les paramètres de connexion MySQL
- URL correcte
- Username/password valides
- Base de données créée (ou createDatabaseIfNotExist=true)
```

### Problème: Lombok ne fonctionne pas
```
Solution: 
1. Installer le plugin Lombok dans votre IDE
2. Enable Annotation Processing (IntelliJ: Settings → Build → Compiler → Annotation Processors)
3. Rebuild le projet
```

## 📚 Ressources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Swagger/OpenAPI](https://swagger.io/specification/)
- [Lombok](https://projectlombok.org/)

## 👥 Auteurs
- Projet d'étude - Architecture des SI II
- Framework: Spring Boot
- Objectif: Gestion de Foyer Universitaire

## 📄 Licence
Projet éducatif - ESPRIT

---
**Note**: Cette application a été développée dans le cadre du cours "Architecture des SI II (Spring Framework)"