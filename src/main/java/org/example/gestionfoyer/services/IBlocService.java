package org.example.gestionfoyer.services;

import org.example.gestionfoyer.entities.Bloc;
import java.util.List;

public interface IBlocService {
    List<Bloc> retrieveBlocs();
    Bloc updateBloc(Bloc bloc);
    Bloc addBloc(Bloc bloc);
    Bloc retrieveBloc(long idBloc);
    void removeBloc(long idBloc);
    // Part 5: Assign chambers to bloc
    Bloc affecterChambresABloc(List<Long> numChambre, long idBloc);
}
