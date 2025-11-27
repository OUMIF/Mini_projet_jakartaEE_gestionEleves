package com.tp2.tp2.model;

import jakarta.persistence.*;
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
public class Cours {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String intitule;

    // filiere -- cours
    @ManyToMany(mappedBy = "cours")
    private List<Filiere> filieres;

    // eleve -- cours
    @ManyToMany(mappedBy = "cours")
    private List<Eleve> eleves;


}
