package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.entities.Reservation;
import org.example.gestionfoyer.services.IReservationService;

@RestController
@AllArgsConstructor
@RequestMapping("/aop")
@Tag(name = "Part 6 - AOP Aspect")
public class AOPRestController {

    private final IReservationService reservationService;

    @PostMapping("/test-execution-time/{chambre-id}/{cin-etudiant}")
    @Operation(description = "Test AOP aspect by adding a reservation and measuring execution time")
    public Reservation testExecutionTimeAspect(@PathVariable("chambre-id") Long chambreId,
                                               @PathVariable("cin-etudiant") Long cinEtudiant) {
        return reservationService.ajouterReservation(chambreId, cinEtudiant);
    }
}

