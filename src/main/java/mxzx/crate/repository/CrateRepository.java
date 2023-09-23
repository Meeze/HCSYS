package mxzx.crate.repository;

import mxzx.HibernateUtil;
import mxzx.crate.model.Crate;
import mxzx.crate.model.CrateItem;
import mxzx.database.Repository;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class CrateRepository implements Repository<Crate> {

    public void saveItem(CrateItem type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(type);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void save(Crate type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(type);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    public void update(Crate type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(type);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Crate load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Crate loaded = session.get(Crate.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Crate> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Crate> all = session.createQuery("SELECT a FROM Crate a", Crate.class).getResultList();
        session.close();
        if (all == null) {
            return new ArrayList<>();
        }
        return all;
    }

    @Override
    public void delete(Crate type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(type);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
