package org.example.gestionfoyer.dto.reservation;

import java.time.LocalDate;
import java.util.Set;

public record ReservationResponse(
        String idReservation,
        LocalDate anneeUniversitaire,
        Boolean estValide,
        Long chambreId,
        Set<Long> cinEtudiants
) {
}
