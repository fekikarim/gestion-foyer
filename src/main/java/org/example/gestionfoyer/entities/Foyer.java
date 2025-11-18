package org.example.gestionfoyer.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Foyer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFoyer;

    private String nomFoyer;

    private Long capaciteFoyer;

    // Relation bidirectionnelle OneToOne avec Universite (child side)
    @OneToOne(mappedBy = "foyer")
    @JsonIgnore
    @ToString.Exclude
    private Universite universite;

    // Relation OneToMany avec Bloc
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "foyer")
    @ToString.Exclude
    private Set<Bloc> blocs;
}
