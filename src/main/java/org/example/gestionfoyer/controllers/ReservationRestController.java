package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Reservation;
import org.example.gestionfoyer.services.IReservationService;

import java.util.List;
import java.time.LocalDate;

@RestController
@AllArgsConstructor
@RequestMapping("/reservation")
@Tag(name = "Gestion Réservations")
public class ReservationRestController {

    private final IReservationService reservationService;

    @GetMapping("/retrieve-all-reservations")
    @Operation(description = "Récupérer toutes les réservations")
    public List<Reservation> getReservations() {
        return reservationService.retrieveAllReservation();
    }

    @GetMapping("/retrieve-reservation/{reservation-id}")
    @Operation(description = "Récupérer une réservation par son ID")
    public Reservation retrieveReservation(@PathVariable("reservation-id") String reservationId) {
        return reservationService.retrieveReservation(reservationId);
    }

    @PutMapping("/update-reservation")
    @Operation(description = "Modifier une réservation existante")
    public Reservation updateReservation(@RequestBody Reservation res) {
        return reservationService.updateReservation(res);
    }

    // Part 5: Add reservation assigned to chamber and student
    @PostMapping("/ajouter-reservation/{chambre-id}/{cin-etudiant}")
    @Operation(description = "Ajouter une réservation pour une chambre et un étudiant")
    public Reservation ajouterReservation(@PathVariable("chambre-id") Long chambreId,
                                          @PathVariable("cin-etudiant") Long cinEtudiant) {
        return reservationService.ajouterReservation(chambreId, cinEtudiant);
    }

    // Part 5: Cancel reservation by student cin
    @PutMapping("/annuler-reservation/{cin-etudiant}")
    @Operation(description = "Annuler une réservation d'un étudiant")
    public Reservation annulerReservation(@PathVariable("cin-etudiant") Long cinEtudiant) {
        return reservationService.annulerReservation(cinEtudiant);
    }

    // Part 5: Get reservations by academic year and university name
    @GetMapping("/get-reservation-par-annee-et-universite")
    @Operation(description = "Récupérer les réservations d'une année universitaire et d'une université")
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(
            @RequestParam("anneeUniversitaire") String anneeUniversitaire,
            @RequestParam("nomUniversite") String nomUniversite) {
        LocalDate date = LocalDate.parse(anneeUniversitaire);
        return reservationService.getReservationParAnneeUniversitaireEtNomUniversite(date, nomUniversite);
    }
}