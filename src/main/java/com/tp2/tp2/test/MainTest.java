package com.tp2.tp2.test;

import com.tp2.tp2.model.Eleve;
import com.tp2.tp2.model.Filiere;
import com.tp2.tp2.model.Cours;
import com.tp2.tp2.service.EleveService;

import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class MainTest {

    public static void main(String[] args) {

        // Création du validator
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        EleveService eleveService = new EleveService(validator);

        // Création de quelques filières
        Filiere filiere1 = new Filiere();
        filiere1.setId(1);
        filiere1.setNom("Informatique");

        Filiere filiere2 = new Filiere();
        filiere2.setId(2);
        filiere2.setNom("Mathématiques");

        // Création de quelques cours
        Cours cours1 = new Cours();
        cours1.setId(1);
        cours1.setCode("Java");

        Cours cours2 = new Cours();
        cours2.setId(2);
        cours2.setCode("Base de données");

        Cours cours3 = new Cours();
        cours3.setId(3);
        cours3.setCode("Algèbre");

        // Création des élèves
        Eleve eleve1 = new Eleve();
        eleve1.setNom("Mehdi");
        eleve1.setPrenom("El Adnani");
        eleve1.setFiliere(filiere1);
        List<Cours> coursEleve1 = new ArrayList<>();
        coursEleve1.add(cours1);
        coursEleve1.add(cours2);
        eleve1.setCours(coursEleve1);

        Eleve eleve2 = new Eleve();
        eleve2.setNom("Sara");
        eleve2.setPrenom("Benali");
        eleve2.setFiliere(filiere2);
        List<Cours> coursEleve2 = new ArrayList<>();
        coursEleve2.add(cours3);
        eleve2.setCours(coursEleve2);

        // Ajout dans la base via le service
        eleveService.createEleve(eleve1);
        eleveService.createEleve(eleve2);

        // Affichage de tous les élèves
        eleveService.getAllEleve().forEach(e ->
                System.out.println(e.getNom() + " " + e.getPrenom() + " -> Filiere: " + e.getFiliere().getNom())
        );
    }
}
