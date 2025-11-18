package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Etudiant;
import org.example.gestionfoyer.repositories.EtudiantRepository;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class EtudiantServiceImpl implements IEtudiantService {

    private final EtudiantRepository etudiantRepository;

    @Override
    public List<Etudiant> retrieveAllEtudiants() {
        log.info("Récupération de tous les étudiants");
        return etudiantRepository.findAll();
    }

    @Override
    public List<Etudiant> addEtudiants(List<Etudiant> etudiants) {
        log.info("Ajout de {} étudiants", etudiants.size());
        return etudiantRepository.saveAll(etudiants);
    }

    @Override
    public Etudiant updateEtudiant(Etudiant e) {
        log.info("Mise à jour de l'étudiant avec ID: {}", e.getIdEtudiant());
        return etudiantRepository.save(e);
    }

    @Override
    public Etudiant retrieveEtudiant(long idEtudiant) {
        log.info("Récupération de l'étudiant avec ID: {}", idEtudiant);
        return etudiantRepository.findById(idEtudiant)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé avec l'ID: " + idEtudiant));
    }

    @Override
    public void removeEtudiant(long idEtudiant) {
        log.info("Suppression de l'étudiant avec ID: {}", idEtudiant);
        etudiantRepository.deleteById(idEtudiant);
    }
}