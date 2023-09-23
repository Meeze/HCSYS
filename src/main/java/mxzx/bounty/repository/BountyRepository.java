package mxzx.bounty.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import mxzx.bounty.model.Bounty;
import mxzx.HibernateUtil;
import mxzx.database.Repository;

import java.util.ArrayList;
import java.util.List;

public class BountyRepository implements Repository<Bounty> {

    @Override
    public void save(Bounty toSave) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(toSave);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Bounty load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Bounty loaded = session.get(Bounty.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Bounty> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Bounty> punishments = session.createQuery("SELECT a FROM Bounty a", Bounty.class).getResultList();
        session.close();
        if (punishments == null) {
            return new ArrayList<>();
        }
        return punishments;
    }


    public List<Bounty> loadByHunted(String target) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List results = session.createCriteria(Bounty.class)
                .add( Restrictions.eq("target", target) )
                .list();
        session.close();
        return results;

    }

    @Override
    public void delete(Bounty toDelete) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(toDelete);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}