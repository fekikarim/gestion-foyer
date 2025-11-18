package org.example.gestionfoyer.services;

import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.TypeChambre;
import java.util.List;

public interface IChambreService {
    List<Chambre> retrieveAllChambres();
    Chambre addChambre(Chambre c);
    Chambre updateChambre(Chambre c);
    Chambre retrieveChambre(long idChambre);
    // Part 5: Get chambers by university name
    List<Chambre> getChambresParNomUniversite(String nomUniversite);
    // Part 5: Get chambers by bloc and type (two solutions: JPQL and Keywords)
    List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC);
    // Part 5: Get unreserved chambers by university name and type
    List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type);
}
