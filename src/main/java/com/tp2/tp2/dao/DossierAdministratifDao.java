package com.tp2.tp2.dao;

import com.tp2.tp2.model.DossierAdministratif;
import com.tp2.tp2.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class DossierAdministratifDao implements ICRUD<DossierAdministratif, Long> {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @Override
    public List<DossierAdministratif> getAll() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from DossierAdministratif", DossierAdministratif.class).list();
        } catch (Exception ex) {
            System.out.println("Erreur getAll: " + ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public DossierAdministratif findById(Long id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(DossierAdministratif.class, id);
        } catch (Exception ex) {
            System.out.println("Erreur findById: " + ex.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(DossierAdministratif object) {
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
    public boolean update(DossierAdministratif object) {
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
    public  boolean delete(Long id) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            DossierAdministratif dossier = session.get(DossierAdministratif.class, id);
            if (dossier == null) return false;

            transaction = session.beginTransaction();
            session.remove(dossier);
            transaction.commit();
            return true;
        } catch (Exception ex) {
            if (transaction != null) transaction.rollback();
            System.out.println("Erreur delete: " + ex.getMessage());
            return false;
        }
    }
}
