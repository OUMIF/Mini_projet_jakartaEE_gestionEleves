package com.tp2.tp2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class Filiere {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    @NotBlank(message = "il faut fournir un code")
    private String code;

    @NotBlank(message = "il faut fournir un nom")
    private String nom;

    @OneToMany(cascade = CascadeType.ALL , mappedBy = "filiere")
    private List<Eleve> eleves;

    @ManyToMany
    @JoinTable(
            name = "propose",
            joinColumns = @JoinColumn(name="filiere_id"),
            inverseJoinColumns =  @JoinColumn(name = "cours_id")
    )
    private List<Cours> cours;


}
