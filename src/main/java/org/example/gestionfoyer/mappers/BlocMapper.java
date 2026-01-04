package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.bloc.BlocResponse;
import org.example.gestionfoyer.dto.bloc.BlocUpsertRequest;
import org.example.gestionfoyer.entities.Bloc;

public final class BlocMapper {
    private BlocMapper() {
    }

    public static Bloc toEntity(BlocUpsertRequest request) {
        if (request == null) return null;
        Bloc bloc = new Bloc();
        bloc.setIdBloc(request.idBloc());
        bloc.setNomBloc(request.nomBloc());
        bloc.setCapaciteBloc(request.capaciteBloc());
        return bloc;
    }

    public static BlocResponse toResponse(Bloc bloc) {
        if (bloc == null) return null;
        Long foyerId = bloc.getFoyer() != null ? bloc.getFoyer().getIdFoyer() : null;
        return new BlocResponse(bloc.getIdBloc(), bloc.getNomBloc(), bloc.getCapaciteBloc(), foyerId);
    }
}
