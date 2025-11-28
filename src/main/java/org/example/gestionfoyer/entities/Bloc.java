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
public class Bloc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBloc;

    private String nomBloc;

    private Long capaciteBloc;

    // Relation ManyToOne avec Foyer
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Foyer foyer;

    // Relation OneToMany avec Chambre
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "bloc")
    @JsonIgnore
    @ToString.Exclude
    private Set<Chambre> chambres;
}
