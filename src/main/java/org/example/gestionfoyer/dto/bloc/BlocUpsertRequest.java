package org.example.gestionfoyer.dto.bloc;

public record BlocUpsertRequest(
        Long idBloc,
        String nomBloc,
        Long capaciteBloc,
        Long foyerId
) {
}
