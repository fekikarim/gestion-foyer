package org.example.gestionfoyer.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Universite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUniversite;

    private String nomUniversite;

    private String adresse;

    // Relation bidirectionnelle OneToOne avec Foyer
    @OneToOne(cascade = CascadeType.ALL)
    @ToString.Exclude
    private Foyer foyer;
}
