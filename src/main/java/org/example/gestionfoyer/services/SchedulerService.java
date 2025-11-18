package org.example.gestionfoyer.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.TypeChambre;
import org.example.gestionfoyer.entities.Universite;
import org.example.gestionfoyer.repositories.ChambreRepository;
import org.example.gestionfoyer.repositories.UniversiteRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class SchedulerService {

    private final ChambreRepository chambreRepository;
    private final UniversiteRepository universiteRepository;

    /**
     * Part 6: Scheduler Service
     * Displays all unreserved chambers for the current academic year for all universities
     * Scheduled to run daily at 2:00 AM (02:00:00)
     */
    @Scheduled(cron = "0 0 2 * * *")
    public void displayChambresNonReserveesPourToutesUniversites() {
        log.info("========== PART 6: SCHEDULER - START ==========");
        log.info("Fetching all universities...");

        List<Universite> universites = universiteRepository.findAll();

        if (universites.isEmpty()) {
            log.warn("No universities found in the database");
            log.info("========== PART 6: SCHEDULER - END ==========");
            return;
        }

        log.info("Found {} universities", universites.size());

        // For each university, get unreserved chambers for each type
        for (Universite universite : universites) {
            log.info("========================================");
            log.info("University: {} (ID: {})", universite.getNomUniversite(), universite.getIdUniversite());
            log.info("Address: {}", universite.getAdresse());
            log.info("========================================");

            // Get unreserved chambers for each type in this university
            for (TypeChambre type : TypeChambre.values()) {
                List<Chambre> chambresNonReservees = chambreRepository
                        .findChambresNonReserveParNomUniversiteEtType(universite.getNomUniversite(), type);

                if (chambresNonReservees.isEmpty()) {
                    log.info("  Type [{}]: No unreserved chambers", type);
                } else {
                    log.info("  Type [{}]: {} unreserved chamber(s)", type, chambresNonReservees.size());

                    // Display details of each unreserved chamber
                    for (Chambre chambre : chambresNonReservees) {
                        log.info("    - Chamber #{} (ID: {}) in Bloc: {}",
                                chambre.getNumeroChambre(),
                                chambre.getIdChambre(),
                                chambre.getBloc() != null ? chambre.getBloc().getNomBloc() : "N/A");
                    }
                }
            }

            log.info("");
        }

        // Summary statistics
        long totalChambres = chambreRepository.findAll().size();
        long totalReserved = chambreRepository.findAll().stream()
                .filter(c -> c.getReservations() != null && c.getReservations().stream()
                        .anyMatch(r -> r.getEstValide() != null && r.getEstValide()))
                .count();
        long totalUnreserved = totalChambres - totalReserved;

        log.info("========================================");
        log.info("SUMMARY STATISTICS");
        log.info("========================================");
        log.info("Total Chambers: {}", totalChambres);
        log.info("Reserved Chambers: {}", totalReserved);
        log.info("Unreserved Chambers: {}", totalUnreserved);
        log.info("========== PART 6: SCHEDULER - END ==========");
    }

    /**
     * Alternative scheduler method to run at fixed intervals
     * Can be enabled for testing purposes
     * Runs every hour
     */
    // Uncomment to enable: @Scheduled(fixedRate = 3600000) // 1 hour = 3600000 ms
    public void displayChambresNonReserveesFixedRate() {
        log.debug("Fixed rate scheduler: Checking unreserved chambers...");
    }

    /**
     * Alternative scheduler method with initial delay
     * Can be enabled for testing purposes
     * Runs every 30 minutes after a 5-minute initial delay
     */
    // Uncomment to enable: @Scheduled(initialDelay = 300000, fixedDelay = 1800000)
    public void displayChambresNonReserveesWithDelay() {
        log.debug("Delayed scheduler: Checking unreserved chambers...");
    }
}
