package org.example.gestionfoyer.dto.foyer;

public record FoyerResponse(
        Long idFoyer,
        String nomFoyer,
        Long capaciteFoyer,
        Long universiteId
) {
}
