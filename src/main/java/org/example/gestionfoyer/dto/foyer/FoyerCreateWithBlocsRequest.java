package org.example.gestionfoyer.dto.foyer;

import org.example.gestionfoyer.dto.bloc.BlocUpsertRequest;

import java.util.List;

public record FoyerCreateWithBlocsRequest(
        String nomFoyer,
        Long capaciteFoyer,
        List<BlocUpsertRequest> blocs
) {
}
