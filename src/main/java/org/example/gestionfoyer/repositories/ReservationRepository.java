package org.example.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.gestionfoyer.entities.Reservation;
import java.util.List;
import java.util.Date;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, String> {
    // Les méthodes CRUD de base sont héritées de JpaRepository
    // Note: L'ID est de type String pour Reservation

    // Find reservations by academic year and university name
    @Query("SELECT r FROM Reservation r WHERE r.anneeUniversitaire = :anneeUniversitaire " +
           "AND r.chambre.bloc.foyer.universite.nomUniversite = :nomUniversite")
    List<Reservation> findReservationsByAnneeAndUniversite(@Param("anneeUniversitaire") Date anneeUniversitaire,
                                                           @Param("nomUniversite") String nomUniversite);

    // Find reservation by student cin
    @Query("SELECT r FROM Reservation r JOIN r.etudiants e WHERE e.cin = :cinEtudiant")
    Reservation findReservationByCinEtudiant(@Param("cinEtudiant") Long cinEtudiant);
}
