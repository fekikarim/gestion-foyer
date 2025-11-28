package org.example.gestionfoyer.services;

import jakarta.transaction.Transactional;
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

@Service
@AllArgsConstructor
@Slf4j
public class SchedulerService {

    private final ChambreRepository chambreRepository;
    private final UniversiteRepository universiteRepository;

    /**
     * Part 6: Scheduler Service
     * Displays all unreserved chambers for the current academic year for all universities
     * Scheduled to run every 60 seconds (60000 ms)
     * 
     * Uses JPQL queries for efficient database operations following the class diagram:
     * Universite (1) <-> (1) Foyer (1) -> (*) Bloc (1) -> (*) Chambre (1) -> (*) Reservation
     */
    @Scheduled(fixedRate = 60000)
    @Transactional
    public void displayChambresNonReserveesPourToutesUniversites() {

        List<Universite> universites = universiteRepository.findAll();

        if (universites.isEmpty()) {
            log.warn("No universities found in the database");
            return;
        }

        // For each university, get unreserved chambers for each type using JPQL
        for (Universite universite : universites) {
            log.info("Université: {}", universite.getNomUniversite());

            // Get unreserved chambers for each type in this university using JPQL with JOIN FETCH
            for (TypeChambre type : TypeChambre.values()) {
                List<Chambre> chambresNonReservees = chambreRepository
                        .findChambresNonReserveParNomUniversiteEtTypeFetch(universite.getNomUniversite(), type);

                log.info("  Type {} : {} chambres non réservées", type, chambresNonReservees.size());
            }
        }
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
