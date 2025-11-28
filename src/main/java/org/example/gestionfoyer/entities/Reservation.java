package org.example.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Reservation implements Serializable {

    @Id
    private String idReservation;

    private LocalDate anneeUniversitaire;

    private Boolean estValide;

    // Relation ManyToMany avec Etudiant
    @ManyToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    @ToString.Exclude
    private Set<Etudiant> etudiants;

    // Relation ManyToOne avec Chambre
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Chambre chambre;
}
