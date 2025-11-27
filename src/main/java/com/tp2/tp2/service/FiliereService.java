package com.tp2.tp2.service;

import com.tp2.tp2.dao.FiliereDao;
import com.tp2.tp2.model.Filiere;
import jakarta.validation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public class FiliereService {
    private final FiliereDao filiereDao = new FiliereDao();
    private final Validator validator;

    public  FiliereService(Validator validator) {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }



    public Optional<Filiere> createFiliere(Filiere filiere){

        Set<ConstraintViolation<Filiere>> violations = validator.validate(filiere);

        if(!violations.isEmpty()){
            violations.forEach(violation -> System.out.println(violation.getMessage()));
            return  Optional.empty();
        }


        Optional<Filiere> exist = filiereDao.findByCode(filiere.getCode());
        if (exist.isPresent()) {
            System.out.println("Le code de la filière existe déjà !");
            return Optional.empty();
        }
            filiereDao.create(filiere);
            return Optional.of(filiere);
    }


    public boolean updateFiliere(Filiere filiere){

        Set<ConstraintViolation<Filiere>> violations = validator.validate(filiere);

        if(!violations.isEmpty()){
             for(ConstraintViolation<Filiere> violation : violations){
                 System.out.println(violation.getMessage());
             }
            return false;
         }
         else{
             if(!filiereDao.findByCode(filiere.getCode()).isEmpty()){
                 System.out.println("Code deja existant");
                 return false;
             }
             filiereDao.update(filiere);
             return true;
        }
    }

    public void deleteFiliere(Filiere filiere){
        if(filiere.getEleves().isEmpty()){
            filiereDao.delete(filiere.getId());
            System.out.println("Filière supprimée avec succès");
        }
        else
        System.out.println("La filiere contient des eleves {"+filiere.getEleves()+"}");
    }

    public List<Filiere> findAllFilieres(){
        return filiereDao.getAll();
    }

    public Optional<Filiere> findFiliereById(Integer id){
            Filiere filiere = filiereDao.findById(id);
            if(filiere == null){
                return Optional.empty();
            }
            return Optional.of(filiere);
    }
}
