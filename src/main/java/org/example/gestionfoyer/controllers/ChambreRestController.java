package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.TypeChambre;
import org.example.gestionfoyer.services.IChambreService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chambre")
@Tag(name = "Gestion Chambres")
public class ChambreRestController {

    private final IChambreService chambreService;

    @GetMapping("/retrieve-all-chambres")
    @Operation(description = "Récupérer toutes les chambres")
    public List<Chambre> getChambres() {
        return chambreService.retrieveAllChambres();
    }

    @GetMapping("/retrieve-chambre/{chambre-id}")
    @Operation(description = "Récupérer une chambre par son ID")
    public Chambre retrieveChambre(@PathVariable("chambre-id") Long chambreId) {
        return chambreService.retrieveChambre(chambreId);
    }

    @PostMapping("/add-chambre")
    @Operation(description = "Ajouter une nouvelle chambre")
    public Chambre addChambre(@RequestBody Chambre c) {
        return chambreService.addChambre(c);
    }

    @PutMapping("/update-chambre")
    @Operation(description = "Modifier une chambre existante")
    public Chambre updateChambre(@RequestBody Chambre c) {
        return chambreService.updateChambre(c);
    }

    // Part 5: Get chambers by university name
    @GetMapping("/get-chambres-par-universite/{nom-universite}")
    @Operation(description = "Récupérer les chambres d'une université par son nom")
    public List<Chambre> getChambresParNomUniversite(@PathVariable("nom-universite") String nomUniversite) {
        return chambreService.getChambresParNomUniversite(nomUniversite);
    }

    // Part 5: Get chambers by bloc and type (JPQL and Keywords solutions)
    @GetMapping("/get-chambres-par-bloc-et-type/{bloc-id}/{type}")
    @Operation(description = "Récupérer les chambres d'un bloc selon leur type")
    public List<Chambre> getChambresParBlocEtType(@PathVariable("bloc-id") Long blocId,
                                                   @PathVariable("type") TypeChambre typeC) {
        return chambreService.getChambresParBlocEtType(blocId, typeC);
    }

    // Part 5: Get unreserved chambers by university name and type
    @GetMapping("/get-chambres-non-reserve/{nom-universite}/{type}")
    @Operation(description = "Récupérer les chambres non réservées d'une université selon leur type")
    public List<Chambre> getChambresNonReserveParNomUniversiteEtTypeChambre(@PathVariable("nom-universite") String nomUniversite,
                                                                             @PathVariable("type") TypeChambre type) {
        return chambreService.getChambresNonReserveParNomUniversiteEtTypeChambre(nomUniversite, type);
    }
}