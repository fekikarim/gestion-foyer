package org.example.gestionfoyer.dto.universite;

public record UniversiteResponse(
        Long idUniversite,
        String nomUniversite,
        String adresse,
        Long foyerId
) {
}
