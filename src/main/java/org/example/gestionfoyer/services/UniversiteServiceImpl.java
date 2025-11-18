package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Universite;
import org.example.gestionfoyer.entities.Foyer;
import org.example.gestionfoyer.repositories.UniversiteRepository;
import org.example.gestionfoyer.repositories.FoyerRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UniversiteServiceImpl implements IUniversiteService {

    private final UniversiteRepository universiteRepository;
    private final FoyerRepository foyerRepository;

    @Override
    public List<Universite> retrieveAllUniversities() {
        log.info("Récupération de toutes les universités");
        return universiteRepository.findAll();
    }

    @Override
    public Universite addUniversite(Universite u) {
        log.info("Ajout d'une nouvelle université: {}", u.getNomUniversite());
        return universiteRepository.save(u);
    }

    @Override
    public Universite updateUniversite(Universite u) {
        log.info("Mise à jour de l'université avec ID: {}", u.getIdUniversite());
        return universiteRepository.save(u);
    }

    @Override
    public Universite retrieveUniversite(long idUniversite) {
        log.info("Récupération de l'université avec ID: {}", idUniversite);
        return universiteRepository.findById(idUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec l'ID: " + idUniversite));
    }

    // Part 5: Assign foyer to university
    @Override
    public Universite affecterFoyerAUniversite(long idFoyer, String nomUniversite) {
        log.info("Affectation du foyer {} à l'université {}", idFoyer, nomUniversite);

        Foyer foyer = foyerRepository.findById(idFoyer)
                .orElseThrow(() -> new RuntimeException("Foyer non trouvé avec l'ID: " + idFoyer));

        Universite universite = universiteRepository.findByNomUniversite(nomUniversite)
                .orElseThrow(() -> new RuntimeException("Université non trouvée avec le nom: " + nomUniversite));

        universite.setFoyer(foyer);
        return universiteRepository.save(universite);
    }
}