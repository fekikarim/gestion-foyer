package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.chambre.ChambreResponse;
import org.example.gestionfoyer.dto.chambre.ChambreUpsertRequest;
import org.example.gestionfoyer.entities.Chambre;

public final class ChambreMapper {
    private ChambreMapper() {
    }

    public static Chambre toEntity(ChambreUpsertRequest request) {
        if (request == null) return null;
        Chambre chambre = new Chambre();
        chambre.setIdChambre(request.idChambre());
        chambre.setNumeroChambre(request.numeroChambre());
        chambre.setTypeC(request.typeC());
        return chambre;
    }

    public static ChambreResponse toResponse(Chambre chambre) {
        if (chambre == null) return null;
        Long blocId = chambre.getBloc() != null ? chambre.getBloc().getIdBloc() : null;
        return new ChambreResponse(chambre.getIdChambre(), chambre.getNumeroChambre(), chambre.getTypeC(), blocId);
    }
}
