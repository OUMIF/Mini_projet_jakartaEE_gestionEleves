package com.tp2.tp2.test;

import com.tp2.tp2.model.Filiere;
import com.tp2.tp2.service.FiliereService;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

public class FiliereTest {

    public static void main(String[] args) {

        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        FiliereService service = new FiliereService(validator);

        System.out.println("===== TEST CREATE FILIERE =====");

        Filiere f1 = new Filiere();
        f1.setCode("INFO");
        f1.setNom("info");

        service.createFiliere(f1).ifPresentOrElse(
                f -> System.out.println("Créée : " + f),
                () -> System.out.println("Erreur lors de la création !")
        );



        System.out.println("===== TEST DOUBLON CODE =====");

        Filiere f2 = new Filiere();
        f2.setCode("INFO"); // même code → devrait échouer
        f2.setNom("info2");


        service.createFiliere(f2).ifPresentOrElse(
                f -> System.out.println("Créée : " + f),
                () -> System.out.println("Erreur : code existe déjà !")
        );



        System.out.println("===== TEST FIND BY ID =====");

        service.findFiliereById(3).ifPresentOrElse(
                f -> System.out.println("Filière = " + f),
                () -> System.out.println("Aucune filière trouvée")
        );




        System.out.println("===== TEST UPDATE =====");

        if (f1 != null) {

            service.updateFiliere(f1);
            System.out.println("Après update : " + service.findFiliereById(f1.getId()));
        }



        System.out.println("===== TEST DELETE =====");

        service.deleteFiliere(f2);
    }
}
