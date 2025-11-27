package com.tp2.tp2.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DossierAdministratif {

    @Id
    private Long id;

    private String numero;
    private Date dateCreation;

    @OneToOne(mappedBy = "dossierAdministratif", cascade = CascadeType.ALL)
    private Eleve eleve;


}