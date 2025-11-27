package com.tp2.tp2.service;

import com.tp2.tp2.dao.DossierAdministratifDao;
import com.tp2.tp2.dao.EleveDao;
import com.tp2.tp2.dao.FiliereDao;
import com.tp2.tp2.model.Eleve;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class EleveService {

    private final EleveDao eleveDao = new EleveDao();
    private final DossierAdministratifDao dossierAdministratifDao = new DossierAdministratifDao();
    private final Validator validator;

    public  EleveService(Validator validator){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        this.validator = factory.getValidator();
    }


    public boolean createEleve(Eleve eleve){
        Set<ConstraintViolation<Eleve>> violations = validator.validate(eleve);
        if(!violations.isEmpty()){
            violations.forEach(violation -> System.out.println(violation.getMessage()));
        }
        if(eleve.getFiliere() == null){
            System.out.println("Eleve filiere is null");
        }
        if (eleve.getCours() == null){
            System.out.println("Eleve cours is null");
        }
        return eleveDao.create(eleve);
    }

    public List<Eleve> getAllEleve(){
        return eleveDao.getAll();
    }

    public Optional<Eleve> getEleveById(int id){
        Eleve eleve = eleveDao.findById(id);
        return Optional.ofNullable(eleve);
    }

    public boolean updateEleve(Eleve eleve){

        if(eleveDao.findById(eleve.getId()) == null){
            System.out.println("Eleve id not found");
            return false;
        }
        else {
            Set<ConstraintViolation<Eleve>> violations = validator.validate(eleve);
            if (!violations.isEmpty()) {
                violations.forEach(violation -> System.out.println(violation.getMessage()));
            }
            if (eleve.getFiliere() == null) {
                System.out.println("Eleve filiere is null");
            }
            if (eleve.getCours() == null) {
                System.out.println("Eleve cours is null");
            }
        }
        return eleveDao.update(eleve);
    }

    public boolean deleteEleve(int id){
        /* On verifie deja si l'etudiant existe vraiment */
        if(eleveDao.findById(id) == null){
            System.out.println("Eleve id not found");
            return false;
        }
        else {
            Eleve eleve = eleveDao.findById(id);
            dossierAdministratifDao.delete(eleve.getDossierAdministratif().getId());
            eleve.getCours().clear();
            eleveDao.update(eleve);
            eleveDao.delete(id);
            return true;
        }
    }



}
