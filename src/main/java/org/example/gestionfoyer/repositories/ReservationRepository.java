package org.example.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.gestionfoyer.entities.Reservation;
import java.util.List;
import java.time.LocalDate;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    // Les méthodes CRUD de base sont héritées de JpaRepository
    // Note: L'ID est de type String pour Reservation

    // Find reservations by academic year and university name (with JOIN FETCH to avoid N+1 queries)
    @Query("SELECT DISTINCT r FROM Reservation r " +
           "JOIN FETCH r.chambre c " +
           "JOIN FETCH c.bloc b " +
           "JOIN FETCH b.foyer f " +
           "JOIN FETCH f.universite u " +
           "WHERE r.anneeUniversitaire = :anneeUniversitaire " +
           "AND u.nomUniversite = :nomUniversite")
    List<Reservation> findReservationsByAnneeAndUniversite(@Param("anneeUniversitaire") LocalDate anneeUniversitaire,
                                                           @Param("nomUniversite") String nomUniversite);

    // Find reservation by student cin
    @Query("SELECT r FROM Reservation r JOIN r.etudiants e WHERE e.cin = :cinEtudiant")
    List<Reservation> findReservationsByCinEtudiant(@Param("cinEtudiant") Long cinEtudiant);
}
