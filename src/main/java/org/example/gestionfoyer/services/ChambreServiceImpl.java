package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.TypeChambre;
import org.example.gestionfoyer.repositories.ChambreRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ChambreServiceImpl implements IChambreService {

    private final ChambreRepository chambreRepository;

    @Override
    public List<Chambre> retrieveAllChambres() {
        log.info("Récupération de toutes les chambres");
        return chambreRepository.findAll();
    }

    @Override
    public Chambre addChambre(Chambre c) {
        log.info("Ajout d'une nouvelle chambre numéro: {}", c.getNumeroChambre());
        return chambreRepository.save(c);
    }

    @Override
    public Chambre updateChambre(Chambre c) {
        log.info("Mise à jour de la chambre avec ID: {}", c.getIdChambre());
        return chambreRepository.save(c);
    }

    @Override
    public Chambre retrieveChambre(long idChambre) {
        log.info("Récupération de la chambre avec ID: {}", idChambre);
        return chambreRepository.findById(idChambre)
                .orElseThrow(() -> new RuntimeException("Chambre non trouvée avec l'ID: " + idChambre));
    }

    // Part 5: Get chambers by university name
    @Override
    public List<Chambre> getChambresParNomUniversite(String nomUniversite) {
        log.info("Récupération des chambres de l'université: {}", nomUniversite);
        return chambreRepository.findChambresByNomUniversite(nomUniversite);
    }

    // Part 5: Get chambers by bloc and type (using JPQL)
    @Override
    public List<Chambre> getChambresParBlocEtType(long idBloc, TypeChambre typeC) {
        log.info("Récupération des chambres du bloc {} de type {}", idBloc, typeC);
        // Using JPQL solution
        return chambreRepository.findChambresByBlocAndTypeJPQL(idBloc, typeC);
        // Alternative: Using Keywords method
        // return chambreRepository.findByBlocIdBlocAndTypeC(idBloc, typeC);
    }

    // Part 5: Get unreserved chambers by university name and type
    @Override
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(String nomUniversite, TypeChambre type) {
        log.info("Récupération des chambres non réservées de l'université {} de type {}", nomUniversite, type);
        return chambreRepository.findChambresNonReserveParNomUniversiteEtType(nomUniversite, type);
    }
}