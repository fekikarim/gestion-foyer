package org.example.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.gestionfoyer.entities.Foyer;

@Repository
public interface FoyerRepository extends JpaRepository<Foyer, Long> {
    // Les méthodes CRUD de base sont héritées de JpaRepository
    // findAll(), findById(), save(), deleteById(), etc.
}
