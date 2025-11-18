package org.example.gestionfoyer.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.example.gestionfoyer.services.SchedulerService;

@RestController
@AllArgsConstructor
@RequestMapping("/scheduler")
@Tag(name = "Part 6 - Scheduler")
public class SchedulerRestController {

    private final SchedulerService schedulerService;

    @GetMapping("/display-chambres-non-reservees")
    @Operation(description = "Manually trigger the scheduler to display unreserved chambers for all universities")
    public String triggerDisplayChambresNonReservees() {
        schedulerService.displayChambresNonReserveesPourToutesUniversites();
        return "Scheduler triggered successfully. Check logs for details.";
    }
}

