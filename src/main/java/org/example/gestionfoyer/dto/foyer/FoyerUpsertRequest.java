package org.example.gestionfoyer.dto.foyer;

public record FoyerUpsertRequest(
        Long idFoyer,
        String nomFoyer,
        Long capaciteFoyer
) {
}
