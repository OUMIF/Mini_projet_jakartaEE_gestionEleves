package com.tp2.tp2.dao;

import com.tp2.tp2.model.Filiere;
import com.tp2.tp2.utils.HibernateUtil;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FiliereDao implements ICRUD<Filiere, Integer> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public List<Filiere> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Filiere", Filiere.class).list();
        } catch (Exception ex) {
            System.out.println("Erreur getAll: " + ex.getMessage());
            return new ArrayList<>(); // retourne liste vide en cas d'erreur
        }
    }


    @Override
    public Filiere findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Filiere.class, id);
        } catch (Exception ex) {
            System.out.println("Erreur findById: " + ex.getMessage());
            return null;
        }
    }


    @Override
    public boolean create(Filiere object) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(object);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur create: " + ex.getMessage());
            return false;
        }
    }


    @Override
    public boolean update(Filiere object) {
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
            Filiere filiere = session.get(Filiere.class, id);
            if (filiere == null) return false;

            transaction = session.beginTransaction();
            session.remove(filiere);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur delete: " + ex.getMessage());
            return false;
        }
    }

    public Optional<Filiere> findByCode(@NotBlank(message = "il faut fournir un code") String code) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Filiere f where f.code = :code",Filiere.class)
                    .setParameter("code",code)
                    .uniqueResultOptional();
        } catch (Exception ex) {
            System.out.println("Erreur findById: " + ex.getMessage());
            return Optional.empty();
        }
    }
}
