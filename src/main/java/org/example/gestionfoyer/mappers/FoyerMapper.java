package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.foyer.FoyerResponse;
import org.example.gestionfoyer.dto.foyer.FoyerUpsertRequest;
import org.example.gestionfoyer.entities.Foyer;

public final class FoyerMapper {
    private FoyerMapper() {
    }

    public static Foyer toEntity(FoyerUpsertRequest request) {
        if (request == null) return null;
        Foyer foyer = new Foyer();
        foyer.setIdFoyer(request.idFoyer());
        foyer.setNomFoyer(request.nomFoyer());
        foyer.setCapaciteFoyer(request.capaciteFoyer());
        return foyer;
    }

    public static FoyerResponse toResponse(Foyer foyer) {
        if (foyer == null) return null;
        Long universiteId = foyer.getUniversite() != null ? foyer.getUniversite().getIdUniversite() : null;
        return new FoyerResponse(foyer.getIdFoyer(), foyer.getNomFoyer(), foyer.getCapaciteFoyer(), universiteId);
    }
}
