package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Reservation;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.Etudiant;
import org.example.gestionfoyer.repositories.ReservationRepository;
import org.example.gestionfoyer.repositories.ChambreRepository;
import org.example.gestionfoyer.repositories.EtudiantRepository;

import java.util.List;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class ReservationServiceImpl implements IReservationService {

    private final ReservationRepository reservationRepository;
    private final ChambreRepository chambreRepository;
    private final EtudiantRepository etudiantRepository;

    @Override
    public List<Reservation> retrieveAllReservation() {
        log.info("Récupération de toutes les réservations");
        return reservationRepository.findAll();
    }

    @Override
    public Reservation updateReservation(Reservation res) {
        log.info("Mise à jour de la réservation avec ID: {}", res.getIdReservation());
        return reservationRepository.save(res);
    }

    @Override
    public Reservation retrieveReservation(String idReservation) {
        log.info("Récupération de la réservation avec ID: {}", idReservation);
        return reservationRepository.findById(idReservation)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée avec l'ID: " + idReservation));
    }

    // Part 5: Add reservation assigned to chamber and student
    @Override
    public Reservation ajouterReservation(long idChambre, long cinEtudiant) {
        log.info("Ajout d'une réservation pour la chambre {} et l'étudiant avec CIN {}", idChambre, cinEtudiant);

        Chambre chambre = chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée avec l'ID: " + idChambre));

        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec le CIN: " + cinEtudiant));

        // Generate reservation ID: numChambre-nomBloc-anneeUniversitaire
        String nomBloc = chambre.getBloc().getNomBloc();
        Long numeroChambre = chambre.getNumeroChambre();
        String year = String.valueOf(java.time.Year.now().getValue());
        String idReservation = numeroChambre + "-" + nomBloc + "-" + year;

        Reservation reservation = new Reservation();
        reservation.setIdReservation(idReservation);
        reservation.setEstValide(true);
        reservation.setAnneeUniversitaire(new Date());
        reservation.setChambre(chambre);

        Set<Etudiant> etudiants = new HashSet<>();
        etudiants.add(etudiant);
        reservation.setEtudiants(etudiants);

        return reservationRepository.save(reservation);
    }

    // Part 5: Cancel reservation by student cin
    @Override
    public Reservation annulerReservation(long cinEtudiant) {
        log.info("Annulation de la réservation de l'étudiant avec CIN: {}", cinEtudiant);
        // Fetch all reservations for this student
        List<Reservation> reservations = reservationRepository.findReservationsByCinEtudiant(cinEtudiant);
        if (reservations == null || reservations.isEmpty()) {
            throw new RuntimeException("Aucune réservation trouvée pour l'étudiant avec le CIN: " + cinEtudiant);
        }

        // Find the Etudiant entity once
        Etudiant etudiant = etudiantRepository.findByCin(cinEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec le CIN: " + cinEtudiant));

        Reservation lastSaved = null;
        for (Reservation reservation : reservations) {
            // Mark invalid
            reservation.setEstValide(false);

            // Remove student from reservation set
            if (reservation.getEtudiants() != null) {
                reservation.getEtudiants().remove(etudiant);
            }

            // Disassociate chamber
            reservation.setChambre(null);

            lastSaved = reservationRepository.save(reservation);
        }

        // Return the last processed reservation (controller expects a single Reservation)
        return lastSaved;
    }

    // Part 5: Get reservations by academic year and university name
    @Override
    public List<Reservation> getReservationParAnneeUniversitaireEtNomUniversite(Date anneeUniversitaire, String nomUniversite) {
        log.info("Récupération des réservations pour l'année universitaire {} et l'université {}", anneeUniversitaire, nomUniversite);
        return reservationRepository.findReservationsByAnneeAndUniversite(anneeUniversitaire, nomUniversite);
    }
}
