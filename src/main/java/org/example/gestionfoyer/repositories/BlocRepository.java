package org.example.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.gestionfoyer.entities.Bloc;

@Repository
public interface BlocRepository extends JpaRepository<Bloc, Long> {
    // Les méthodes CRUD de base sont héritées de JpaRepository
}
