package org.example.gestionfoyer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.example.gestionfoyer.entities.Chambre;
import org.example.gestionfoyer.entities.TypeChambre;
import java.util.List;

@Repository
public interface ChambreRepository extends JpaRepository<Chambre, Long> {
    // Les méthodes CRUD de base sont héritées de JpaRepository

    // Find chambers by bloc and type using JPQL
    @Query("SELECT c FROM Chambre c WHERE c.bloc.idBloc = :idBloc AND c.typeC = :typeC")
    List<Chambre> findChambresByBlocAndTypeJPQL(@Param("idBloc") long idBloc, @Param("typeC") TypeChambre typeC);

    // Find chambers by bloc and type using keywords
    List<Chambre> findByBlocIdBlocAndTypeC(long idBloc, TypeChambre typeC);

    // Find chambers by university name
    @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite")
    List<Chambre> findChambresByNomUniversite(@Param("nomUniversite") String nomUniversite);

    // Find unreserved chambers by university name and type for current academic year
    @Query("SELECT c FROM Chambre c WHERE c.bloc.foyer.universite.nomUniversite = :nomUniversite " +
           "AND c.typeC = :typeC " +
           "AND c.idChambre NOT IN " +
           "(SELECT r.chambre.idChambre FROM Reservation r WHERE r.estValide = true)")
    List<Chambre> findChambresNonReserveParNomUniversiteEtType(@Param("nomUniversite") String nomUniversite,
                                                               @Param("typeC") TypeChambre typeC);
}
