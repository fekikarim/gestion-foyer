package org.example.gestionfoyer.dto.etudiant;

import java.time.LocalDate;

public record EtudiantResponse(
        Long idEtudiant,
        String nomEt,
        String prenomEt,
        Long cin,
        String ecole,
        LocalDate dateNaissance
) {
}
