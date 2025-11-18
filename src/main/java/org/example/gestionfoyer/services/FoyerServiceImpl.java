package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Foyer;
import org.example.gestionfoyer.entities.Universite;
import org.example.gestionfoyer.repositories.FoyerRepository;
import org.example.gestionfoyer.repositories.UniversiteRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FoyerServiceImpl implements IFoyerService {

    private final FoyerRepository foyerRepository;
    private final UniversiteRepository universiteRepository;

    @Override
    public List<Foyer> retrieveAllFoyers() {
        log.info("Récupération de tous les foyers");
        return foyerRepository.findAll();
    }

    @Override
    public Foyer addFoyer(Foyer f) {
        log.info("Ajout d'un nouveau foyer: {}", f.getNomFoyer());
        return foyerRepository.save(f);
    }

    @Override
    public Foyer updateFoyer(Foyer f) {
        log.info("Mise à jour du foyer avec ID: {}", f.getIdFoyer());
        return foyerRepository.save(f);
    }

    @Override
    public Foyer retrieveFoyer(long idFoyer) {
        log.info("Récupération du foyer avec ID: {}", idFoyer);
        return foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé avec l'ID: " + idFoyer));
    }

    @Override
    public void removeFoyer(long idFoyer) {
        log.info("Suppression du foyer avec ID: {}", idFoyer);
        foyerRepository.deleteById(idFoyer);
    }

    // Part 5: Add foyer with its blocs and assign to university
    @Override
    public Foyer ajouterFoyerEtAffecterAUniversite(Foyer foyer, long idUniversite) {
        log.info("Ajout d'un foyer et affectation à l'université avec ID: {}", idUniversite);

        Universite universite = universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec l'ID: " + idUniversite));

        Foyer savedFoyer = foyerRepository.save(foyer);

        universite.setFoyer(savedFoyer);
        universiteRepository.save(universite);

        return savedFoyer;
    }
}
