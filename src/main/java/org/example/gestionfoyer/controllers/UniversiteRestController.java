package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Universite;
import org.example.gestionfoyer.services.IUniversiteService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
@Tag(name = "Gestion Universités")
public class UniversiteRestController {

    private final IUniversiteService universiteService;

    @GetMapping("/retrieve-all-universites")
    @Operation(description = "Récupérer toutes les universités")
    public List<Universite> getUniversites() {
        return universiteService.retrieveAllUniversities();
    }

    @GetMapping("/retrieve-universite/{universite-id}")
    @Operation(description = "Récupérer une université par son ID")
    public Universite retrieveUniversite(@PathVariable("universite-id") Long universiteId) {
        return universiteService.retrieveUniversite(universiteId);
    }

    @PostMapping("/add-universite")
    @Operation(description = "Ajouter une nouvelle université")
    public Universite addUniversite(@RequestBody Universite u) {
        return universiteService.addUniversite(u);
    }

    @PutMapping("/update-universite")
    @Operation(description = "Modifier une université existante")
    public Universite updateUniversite(@RequestBody Universite u) {
        return universiteService.updateUniversite(u);
    }

    // Part 5: Assign foyer to university
    @PutMapping("/affecter-foyer/{foyer-id}/{nom-universite}")
    @Operation(description = "Affecter un foyer à une université")
    public Universite affecterFoyerAUniversite(@PathVariable("foyer-id") Long foyerId,
                                                @PathVariable("nom-universite") String nomUniversite) {
        return universiteService.affecterFoyerAUniversite(foyerId, nomUniversite);
    }
}