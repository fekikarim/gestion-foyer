package org.example.gestionfoyer.dto.bloc;

public record BlocResponse(
        Long idBloc,
        String nomBloc,
        Long capaciteBloc,
        Long foyerId
) {
}
