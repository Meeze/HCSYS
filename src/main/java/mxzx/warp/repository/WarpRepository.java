package mxzx.warp.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.warp.model.Warp;

import java.util.ArrayList;
import java.util.List;

public class WarpRepository implements Repository<Warp> {

    @Override
    public void save(Warp warp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(warp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Warp warp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(warp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Warp load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Warp loaded = session.get(Warp.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Warp> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Warp> all = session.createQuery("SELECT a FROM Warp a", Warp.class).getResultList();
        session.close();
        if (all == null) {
            return new ArrayList<>();
        }
        return all;
    }

    @Override
    public void delete(Warp warp) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(warp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
