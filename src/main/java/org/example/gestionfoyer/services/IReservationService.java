package org.example.gestionfoyer.services;

import org.example.gestionfoyer.entities.Reservation;
import java.util.List;
import java.util.Date;

public interface IReservationService {
    List<Reservation> retrieveAllReservation();
    Reservation updateReservation(Reservation res);
    Reservation retrieveReservation(String idReservation);
    // Part 5: Add reservation assigned to chamber and student
    Reservation ajouterReservation(long idChambre, long cinEtudiant);
    // Part 5: Cancel reservation by student cin
    Reservation annulerReservation(long cinEtudiant);
    // Part 5: Get reservations by academic year and university name
    List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite);
}
