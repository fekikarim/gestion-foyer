package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Etudiant;
import org.example.gestionfoyer.services.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
@Tag(name = "Gestion Étudiants")
public class EtudiantRestController {

    private final IEtudiantService etudiantService;

    @GetMapping("/retrieve-all-etudiants")
    @Operation(description = "Récupérer tous les étudiants")
    public List<Etudiant> getEtudiants() {
        return etudiantService.retrieveAllEtudiants();
    }

    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    @Operation(description = "Récupérer un étudiant par son ID")
    public Etudiant retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        return etudiantService.retrieveEtudiant(etudiantId);
    }

    @PostMapping("/add-etudiants")
    @Operation(description = "Ajouter une liste d'étudiants")
    public List<Etudiant> addEtudiants(@RequestBody List<Etudiant> etudiants) {
        return etudiantService.addEtudiants(etudiants);
    }

    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    @Operation(description = "Supprimer un étudiant par son ID")
    public void removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        etudiantService.removeEtudiant(etudiantId);
    }

    @PutMapping("/update-etudiant")
    @Operation(description = "Modifier un étudiant existant")
    public Etudiant updateEtudiant(@RequestBody Etudiant e) {
        return etudiantService.updateEtudiant(e);
    }
}