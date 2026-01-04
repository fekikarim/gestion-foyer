package org.example.gestionfoyer.dto.universite;

public record UniversiteUpsertRequest(
        Long idUniversite,
        String nomUniversite,
        String adresse,
        Long foyerId
) {
}
