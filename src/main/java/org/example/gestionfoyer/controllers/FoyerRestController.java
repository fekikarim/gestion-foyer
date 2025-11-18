package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Foyer;
import org.example.gestionfoyer.services.IFoyerService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/foyer")
@Tag(name = "Gestion Foyer")
public class FoyerRestController {

    private final IFoyerService foyerService;

    @GetMapping("/retrieve-all-foyers")
    @Operation(description = "Récupérer tous les foyers")
    public List<Foyer> getFoyers() {
        return foyerService.retrieveAllFoyers();
    }

    @GetMapping("/retrieve-foyer/{foyer-id}")
    @Operation(description = "Récupérer un foyer par son ID")
    public Foyer retrieveFoyer(@PathVariable("foyer-id") Long foyerId) {
        return foyerService.retrieveFoyer(foyerId);
    }

    @PostMapping("/add-foyer")
    @Operation(description = "Ajouter un nouveau foyer")
    public Foyer addFoyer(@RequestBody Foyer f) {
        return foyerService.addFoyer(f);
    }

    @DeleteMapping("/remove-foyer/{foyer-id}")
    @Operation(description = "Supprimer un foyer par son ID")
    public void removeFoyer(@PathVariable("foyer-id") Long foyerId) {
        foyerService.removeFoyer(foyerId);
    }

    @PutMapping("/update-foyer")
    @Operation(description = "Modifier un foyer existant")
    public Foyer updateFoyer(@RequestBody Foyer f) {
        return foyerService.updateFoyer(f);
    }

    // Part 5: Add foyer with its blocs and assign to university
    @PostMapping("/ajouter-foyer-et-affecter/{universite-id}")
    @Operation(description = "Ajouter un foyer avec ses blocs et l'affecter à une université")
    public Foyer ajouterFoyerEtAffecterAUniversite(@RequestBody Foyer foyer,
                                                    @PathVariable("universite-id") Long universiteId) {
        return foyerService.ajouterFoyerEtAffecterAUniversite(foyer, universiteId);
    }
}
