package mxzx.kit.repository;

import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.kit.model.Kit;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class KitRepository implements Repository<Kit> {
    @Override
    public void save(Kit toSave) {
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
    public Kit load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Kit loaded = session.get(Kit.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Kit> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Kit> punishments = session.createQuery("SELECT a FROM Kit a", Kit.class).getResultList();
        session.close();
        if (punishments == null) {
            return new ArrayList<>();
        }
        return punishments;
    }


    @Override
    public void delete(Kit toDelete) {
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
