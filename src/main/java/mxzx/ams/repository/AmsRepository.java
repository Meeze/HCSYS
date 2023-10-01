package mxzx.ams.repository;

import mxzx.ams.model.Ams;
import mxzx.HibernateUtil;
import mxzx.bounty.model.Bounty;
import mxzx.database.Repository;
import org.bukkit.Location;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

public class AmsRepository implements Repository<Ams> {

    public Ams loadByLocation(Location loc) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List results = session.createCriteria(Ams.class)
                .add(Restrictions.eq("location", loc))
                .list();
        session.close();
        return (Ams) results.get(0);

    }


    @Override
    public void save(Ams type) {
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
    public void update(Ams type) {
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
    public Ams load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Ams loaded = session.byNaturalId(Ams.class).using("owner", id).load();
        session.close();
        return loaded;
    }

    @Override
    public List<Ams> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Ams> all = session.createQuery("SELECT a FROM Ams a", Ams.class).getResultList();
        session.close();
        if (all == null) {
            return new ArrayList<>();
        }
        return all;
    }

    @Override
    public void delete(Ams type) {
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
