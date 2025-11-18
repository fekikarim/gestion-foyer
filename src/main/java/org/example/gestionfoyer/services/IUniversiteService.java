package org.example.gestionfoyer.services;

import org.example.gestionfoyer.entities.Universite;
import java.util.List;

public interface IUniversiteService {
    List<Universite> retrieveAllUniversities();
    Universite addUniversite(Universite u);
    Universite updateUniversite(Universite u);
    Universite retrieveUniversite(long idUniversite);
    // Part 5: Assign foyer to university
    Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite);
}
