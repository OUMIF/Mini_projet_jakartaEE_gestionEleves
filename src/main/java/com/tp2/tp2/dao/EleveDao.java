package com.tp2.tp2.dao;

import com.tp2.tp2.model.Eleve;
import com.tp2.tp2.model.Filiere;
import com.tp2.tp2.model.Cours;
import com.tp2.tp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EleveDao implements ICRUD<Eleve, Integer> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Eleve> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Eleve", Eleve.class).list();
        } catch (Exception ex) {
            System.out.println("Erreur getAll: " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Eleve findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Eleve.class, id);
        } catch (Exception ex) {
            System.out.println("Erreur findById: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Eleve eleve) {
        Transaction transaction = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();

            // Attacher la filiere
            Filiere filiere = null;
            if (eleve.getFiliere() != null && eleve.getFiliere().getId() != null) {
                filiere = session.get(Filiere.class, eleve.getFiliere().getId());
            }
            eleve.setFiliere(filiere);
            // Attacher les cours
            if (eleve.getCours() != null) {
                Set<Cours> courses = new HashSet<>();
                for (Cours c : eleve.getCours()) {
                    Cours coursDB = session.get(Cours.class, c.getId());
                    if (coursDB != null) {
                        courses.add(coursDB);
                    } else {
                        System.out.println("Cours inexistant: " + c.getId());
                    }
                }
                eleve.setCours(courses.stream().toList());
            }

            session.persist(eleve);
            transaction.commit();
            return true;

        } catch (Exception ex) {
            if (transaction != null && transaction.getStatus().canRollback()) {
                transaction.rollback();
            }
            System.out.println("Erreur create: " + ex.getMessage());
            return false;
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }


    @Override
    public boolean update(Eleve object) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur update: " + ex.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Integer id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            Eleve eleve = session.get(Eleve.class, id);
            if (eleve == null) return false;

            transaction = session.beginTransaction();
            session.remove(eleve);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur delete: " + ex.getMessage());
            return false;
        }
    }
}
