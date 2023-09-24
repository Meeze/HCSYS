package mxzx.clan.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import mxzx.clan.model.Clan;
import mxzx.HibernateUtil;
import mxzx.database.Repository;

import java.util.ArrayList;
import java.util.List;

public class ClanRepository implements Repository<Clan> {
    @Override
    public void save(Clan type) {
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
    public void update(Clan type) {
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
    public Clan load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Clan loaded = session.get(Clan.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Clan> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Clan> all = session.createQuery("SELECT a FROM Clan a", Clan.class).getResultList();
        session.close();
        if (all == null) {
            return new ArrayList<>();
        }
        return all;
    }

    @Override
    public void delete(Clan type) {
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
