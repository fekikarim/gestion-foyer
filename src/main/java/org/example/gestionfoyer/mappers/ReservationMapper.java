package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.reservation.ReservationResponse;
import org.example.gestionfoyer.entities.Etudiant;
import org.example.gestionfoyer.entities.Reservation;

import java.util.Set;
import java.util.stream.Collectors;

public final class ReservationMapper {
    private ReservationMapper() {
    }

    public static ReservationResponse toResponse(Reservation reservation) {
        if (reservation == null) return null;

        Long chambreId = reservation.getChambre() != null ? reservation.getChambre().getIdChambre() : null;
        Set<Long> cinEtudiants = reservation.getEtudiants() == null
                ? null
                : reservation.getEtudiants().stream().map(Etudiant::getCin).collect(Collectors.toSet());

        return new ReservationResponse(
                reservation.getIdReservation(),
                reservation.getAnneeUniversitaire(),
                reservation.getEstValide(),
                chambreId,
                cinEtudiants
        );
    }
}
