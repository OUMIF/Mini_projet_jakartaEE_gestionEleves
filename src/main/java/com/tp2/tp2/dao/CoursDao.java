package com.tp2.tp2.dao;

import com.tp2.tp2.model.Cours;
import com.tp2.tp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CoursDao implements ICRUD<Cours, Integer> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<Cours> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Cours", Cours.class).list();
        } catch (Exception ex) {
            System.out.println("Erreur getAll: " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public Cours findById(Integer id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(Cours.class, id);
        } catch (Exception ex) {
            System.out.println("Erreur findById: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Cours object) {
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
    public boolean update(Cours object) {
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
            Cours cours = session.get(Cours.class, id);
            if (cours == null) return false;

            transaction = session.beginTransaction();
            session.remove(cours);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur delete: " + ex.getMessage());
            return false;
        }
    }
}
