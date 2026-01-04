package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.universite.UniversiteResponse;
import org.example.gestionfoyer.dto.universite.UniversiteUpsertRequest;
import org.example.gestionfoyer.entities.Universite;

public final class UniversiteMapper {
    private UniversiteMapper() {
    }

    public static Universite toEntity(UniversiteUpsertRequest request) {
        if (request == null) return null;
        Universite universite = new Universite();
        universite.setIdUniversite(request.idUniversite());
        universite.setNomUniversite(request.nomUniversite());
        universite.setAdresse(request.adresse());
        return universite;
    }

    public static UniversiteResponse toResponse(Universite universite) {
        if (universite == null) return null;
        Long foyerId = universite.getFoyer() != null ? universite.getFoyer().getIdFoyer() : null;
        return new UniversiteResponse(
                universite.getIdUniversite(),
                universite.getNomUniversite(),
                universite.getAdresse(),
                foyerId
        );
    }
}
