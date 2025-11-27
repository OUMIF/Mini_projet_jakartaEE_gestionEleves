package com.tp2.tp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Eleve {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nom;
    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "dossierAdministratif_id")
    private DossierAdministratif dossierAdministratif;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "filier_id")
    private Filiere filiere;

    @ManyToMany
    @JoinTable(
            name = "suit",
            joinColumns = @JoinColumn(name = "eleve_id"),
            inverseJoinColumns = @JoinColumn(name = "cours_id")
    )
    private List<Cours> cours;

}
