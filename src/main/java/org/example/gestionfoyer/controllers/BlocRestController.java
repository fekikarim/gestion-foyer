package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Bloc;
import org.example.gestionfoyer.services.IBlocService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/bloc")
@Tag(name = "Gestion Blocs")
public class BlocRestController {

    private final IBlocService blocService;

    @GetMapping("/retrieve-all-blocs")
    @Operation(description = "Récupérer tous les blocs")
    public List<Bloc> getBlocs() {
        return blocService.retrieveBlocs();
    }

    @GetMapping("/retrieve-bloc/{bloc-id}")
    @Operation(description = "Récupérer un bloc par son ID")
    public Bloc retrieveBloc(@PathVariable("bloc-id") Long blocId) {
        return blocService.retrieveBloc(blocId);
    }

    @PostMapping("/add-bloc")
    @Operation(description = "Ajouter un nouveau bloc")
    public Bloc addBloc(@RequestBody Bloc bloc) {
        return blocService.addBloc(bloc);
    }

    @DeleteMapping("/remove-bloc/{bloc-id}")
    @Operation(description = "Supprimer un bloc par son ID")
    public void removeBloc(@PathVariable("bloc-id") Long blocId) {
        blocService.removeBloc(blocId);
    }

    @PutMapping("/update-bloc")
    @Operation(description = "Modifier un bloc existant")
    public Bloc updateBloc(@RequestBody Bloc bloc) {
        return blocService.updateBloc(bloc);
    }

    // Part 5: Assign chambers to bloc
    @PutMapping("/affecter-chambres/{bloc-id}")
    @Operation(description = "Affecter des chambres à un bloc")
    public Bloc affecterChambresABloc(@RequestBody List<Long> numChambre,
                                      @PathVariable("bloc-id") Long blocId) {
        return blocService.affecterChambresABloc(numChambre, blocId);
    }
}