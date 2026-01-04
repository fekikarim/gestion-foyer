package org.example.gestionfoyer.dto.chambre;

import org.example.gestionfoyer.entities.TypeChambre;

public record ChambreResponse(
        Long idChambre,
        Long numeroChambre,
        TypeChambre typeC,
        Long blocId
) {
}
