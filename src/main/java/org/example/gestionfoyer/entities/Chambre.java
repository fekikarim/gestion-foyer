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
public class Chambre implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;

    private Long numeroChambre;

    @Enumerated(EnumType.STRING)
    private TypeChambre typeC;

    // Relation ManyToOne avec Bloc
    @ManyToOne
    @JsonIgnore
    @ToString.Exclude
    private Bloc bloc;

    // Relation OneToMany avec Reservation
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chambre")
    @JsonIgnore
    @ToString.Exclude
    private Set<Reservation> reservations;
}
