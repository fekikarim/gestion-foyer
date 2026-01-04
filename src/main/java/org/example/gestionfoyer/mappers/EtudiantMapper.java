package org.example.gestionfoyer.mappers;

import org.example.gestionfoyer.dto.etudiant.EtudiantResponse;
import org.example.gestionfoyer.dto.etudiant.EtudiantUpsertRequest;
import org.example.gestionfoyer.entities.Etudiant;

public final class EtudiantMapper {
    private EtudiantMapper() {
    }

    public static Etudiant toEntity(EtudiantUpsertRequest request) {
        if (request == null) return null;
        Etudiant etudiant = new Etudiant();
        etudiant.setIdEtudiant(request.idEtudiant());
        etudiant.setNomEt(request.nomEt());
        etudiant.setPrenomEt(request.prenomEt());
        etudiant.setCin(request.cin());
        etudiant.setEcole(request.ecole());
        etudiant.setDateNaissance(request.dateNaissance());
        return etudiant;
    }

    public static EtudiantResponse toResponse(Etudiant etudiant) {
        if (etudiant == null) return null;
        return new EtudiantResponse(
                etudiant.getIdEtudiant(),
                etudiant.getNomEt(),
                etudiant.getPrenomEt(),
                etudiant.getCin(),
                etudiant.getEcole(),
                etudiant.getDateNaissance()
        );
    }
}
