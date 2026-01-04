package org.example.gestionfoyer.dto.etudiant;

import java.time.LocalDate;

public record EtudiantUpsertRequest(
        Long idEtudiant,
        String nomEt,
        String prenomEt,
        Long cin,
        String ecole,
        LocalDate dateNaissance
) {
}
