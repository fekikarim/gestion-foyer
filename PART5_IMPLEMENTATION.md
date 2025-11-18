# Part 5: Services Avancés - Implementation Guide

## Overview
This document outlines all the advanced services implemented in Part 5 of the Gestion Foyer project.

---

## 1. Affecter un Foyer à une Universite

### Service Method
```java
public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite)
```

### Implementation
- **Service**: `UniversiteServiceImpl`
- **Interface**: `IUniversiteService`
- **Repository Method**: `findByNomUniversite(String nomUniversite)` - Added to `UniversiteRepository`

### REST Endpoint
```
PUT /universite/affecter-foyer/{foyer-id}/{nom-universite}
```

### Description
Assigns a foyer to a university by its name. The foyer is linked to the university through a one-to-one relationship.

---

## 2. Affecter des Chambres à un Bloc

### Service Method
```java
public Bloc affecterChambresABloc(List<Long> numChambre, long idBloc)
```

### Implementation
- **Service**: `BlocServiceImpl`
- **Interface**: `IBlocService`

### REST Endpoint
```
PUT /bloc/affecter-chambres/{bloc-id}
Request Body: List of chamber IDs
```

### Description
Associates multiple chambers with a specific bloc. Updates each chamber's bloc reference and maintains the one-to-many relationship.

---

## 3. Ajouter Foyer et Affecter à Universite

### Service Method
```java
public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite)
```

### Implementation
- **Service**: `FoyerServiceImpl`
- **Interface**: `IFoyerService`
- **Features**:
  - Creates foyer with all associated blocs (cascade enabled)
  - Automatically assigns foyer to the specified university
  - Blocs are encapsulated and created together with the foyer

### REST Endpoint
```
POST /foyer/ajouter-foyer-et-affecter/{universite-id}
Request Body: Foyer object with nested blocs
```

### Description
Creates a new foyer with its associated blocs and immediately assigns it to a university in a single transaction.

---

## 4. Ajouter une Réservation

### Service Method
```java
public Reservation ajouterReservation(long idChambre, long cinEtudiant)
```

### Implementation
- **Service**: `ReservationServiceImpl`
- **Interface**: `IReservationService`
- **Features**:
  - Automatically generates reservation ID: `numeroChambre-nomBloc-anneeUniversitaire`
  - Sets `estValide = true` by default
  - Links chamber and student to the reservation
  - Creates bidirectional relationship

### REST Endpoint
```
POST /reservation/ajouter-reservation/{chambre-id}/{cin-etudiant}
```

### Description
Creates a reservation for a student in a specific chamber with automatic ID generation and validation.

---

## 5. Annuler une Réservation

### Service Method
```java
public Reservation annulerReservation(long cinEtudiant)
```

### Implementation
- **Service**: `ReservationServiceImpl`
- **Interface**: `IReservationService`
- **Features**:
  - Updates `estValide = false`
  - Removes association with student (disaffects student)
  - Removes association with chamber (disaffects chamber)
  - Maintains reservation record for audit purposes

### REST Endpoint
```
PUT /reservation/annuler-reservation/{cin-etudiant}
```

### Description
Cancels a student's reservation by marking it invalid and removing all associations while maintaining data integrity.

---

## 6. Get Chambres par Nom Universite

### Service Method
```java
public List<Chambre> getChambresParNomUniversite(String nomUniversite)
```

### Implementation
- **Service**: `ChambreServiceImpl`
- **Interface**: `IChambreService`
- **Repository Method**: Uses JPQL query in `ChambreRepository`
  ```java
  @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite")
  ```

### REST Endpoint
```
GET /chambre/get-chambres-par-universite/{nom-universite}
```

### Description
Retrieves all chambers belonging to a specific university using the university name as the search criterion.

---

## 7. Get Chambres par Bloc et Type (Two Solutions)

### Service Method
```java
public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC)
```

### Implementation
- **Service**: `ChambreServiceImpl`
- **Interface**: `IChambreService`
- **Repository Methods** (Two Solutions):

#### Solution 1: JPQL (Recommended)
```java
@Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
List<Chambre> findChambresByBlocAndTypeJPQL(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);
```

#### Solution 2: Spring Data Keywords
```java
List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);
```

### REST Endpoint
```
GET /chambre/get-chambres-par-bloc-et-type/{bloc-id}/{type}
```

### Description
Retrieves chambers of a specific type from a given bloc. Demonstrates two different query approaches (JPQL and Spring Data Keywords).

---

## 8. Get Reservations par Annee Universitaire et Nom Universite

### Service Method
```java
public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite)
```

### Implementation
- **Service**: `ReservationServiceImpl`
- **Interface**: `IReservationService`
- **Repository Method**: Uses JPQL query in `ReservationRepository`
  ```java
  @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :anneeUniversitaire " +
         "AND r.chambre.bloc.foyer.universite.nomUniversite = :nomUniversite")
  ```

### REST Endpoint
```
GET /reservation/get-reservation-par-annee-et-universite?anneeUniversitaire={timestamp}&nomUniversite={name}
```

### Description
Retrieves all reservations for a specific academic year and university. Useful for generating academic reports.

---

## 9. Get Chambres Non Reservées par Nom Universite et Type

### Service Method
```java
public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type)
```

### Implementation
- **Service**: `ChambreServiceImpl`
- **Interface**: `IChambreService`
- **Repository Method**: Uses JPQL subquery in `ChambreRepository`
  ```java
  @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite " +
         "AND c.typeC = :typeC " +
         "AND c.idChambre NOT IN " +
         "(SELECT r.chambre.idChambre FROM Reservation r WHERE r.estValide = true)")
  ```

### REST Endpoint
```
GET /chambre/get-chambres-non-reserve/{nom-universite}/{type}
```

### Description
Retrieves available (unreserved) chambers of a specific type from a university during the current academic year. Useful for showing available accommodations to students.

---

## Repository Enhancements

All custom query methods have been added to the repositories:

### UniversiteRepository
- `Optional<Universite> findByNomUniversite(String nomUniversite)`

### FoyerRepository
- Basic CRUD operations inherited from JpaRepository

### BlocRepository
- Basic CRUD operations inherited from JpaRepository

### ChambreRepository
- `findChambresByBlocAndTypeJPQL(long, TypeChambre)` - JPQL solution
- `findByBlocIdBlocAndTypeC(long, TypeChambre)` - Spring Data Keywords solution
- `findChambresByNomUniversite(String)` - Find by university name
- `findChambresNonReserveParNomUniversiteEtType(String, TypeChambre)` - Find unreserved chambers

### ReservationRepository
- `findReservationsByAnneeAndUniversite(Date, String)` - Find by year and university
- `findReservationByCinEtudiant(Long)` - Find by student CIN

### EtudiantRepository
- `Optional<Etudiant> findByCin(Long cin)` - Find student by CIN

---

## REST Endpoints Summary

| HTTP Method | Endpoint | Description |
|---|---|---|
| POST | `/foyer/ajouter-foyer-et-affecter/{universite-id}` | Add foyer with blocs and assign to university |
| PUT | `/universite/affecter-foyer/{foyer-id}/{nom-universite}` | Assign foyer to university |
| PUT | `/bloc/affecter-chambres/{bloc-id}` | Assign chambers to bloc |
| POST | `/reservation/ajouter-reservation/{chambre-id}/{cin-etudiant}` | Add reservation |
| PUT | `/reservation/annuler-reservation/{cin-etudiant}` | Cancel reservation |
| GET | `/chambre/get-chambres-par-universite/{nom-universite}` | Get chambers by university |
| GET | `/chambre/get-chambres-par-bloc-et-type/{bloc-id}/{type}` | Get chambers by bloc and type |
| GET | `/chambre/get-chambres-non-reserve/{nom-universite}/{type}` | Get unreserved chambers |
| GET | `/reservation/get-reservation-par-annee-et-universite` | Get reservations by year and university |

---

## Build Status

✅ **BUILD SUCCESS** - All services compiled without errors
- Total build time: 59.908 seconds
- JAR created: `gestion-foyer-0.0.1-SNAPSHOT.jar`

---

## Testing

To test the Part 5 services:

1. **Start the application**:
   ```bash
   java -jar target/gestion-foyer-0.0.1-SNAPSHOT.jar
   ```

2. **Access Swagger UI** (if configured):
   ```
   http://localhost:8080/swagger-ui.html
   ```

3. **Test endpoints using cURL or Postman**

---

## Files Modified

### Service Interfaces
- ✅ `IUniversiteService.java` - Added `affecterFoyerAUniversite`
- ✅ `IFoyerService.java` - Added `ajouterFoyerEtAffecterAUniversite`
- ✅ `IBlocService.java` - Added `affecterChambresABloc`
- ✅ `IChambreService.java` - Added 3 query methods
- ✅ `IReservationService.java` - Added 3 methods

### Service Implementations
- ✅ `UniversiteServiceImpl.java`
- ✅ `FoyerServiceImpl.java`
- ✅ `BlocServiceImpl.java`
- ✅ `ChambreServiceImpl.java`
- ✅ `ReservationServiceImpl.java`

### Repositories
- ✅ `UniversiteRepository.java`
- ✅ `FoyerRepository.java`
- ✅ `BlocRepository.java`
- ✅ `ChambreRepository.java`
- ✅ `ReservationRepository.java`
- ✅ `EtudiantRepository.java`

### REST Controllers
- ✅ `UniversiteRestController.java`
- ✅ `FoyerRestController.java`
- ✅ `BlocRestController.java`
- ✅ `ChambreRestController.java`
- ✅ `ReservationRestController.java`

---

## Notes

1. **Cascade Operations**: Foyer creation automatically creates associated blocs
2. **Validation**: All services include proper exception handling and null checks
3. **Logging**: All services use SLF4J logging for debugging
4. **JPQL vs Keywords**: Both approaches are provided for chamber queries; JPQL is used in implementation
5. **Reservation ID Format**: `numeroChambre-nomBloc-anneeUniversitaire` (e.g., `101-A-2025`)


