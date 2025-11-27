package com.tp2.tp2.dao;

import com.tp2.tp2.model.Eleve;
import com.tp2.tp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

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
    public boolean create(Eleve object) {
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
