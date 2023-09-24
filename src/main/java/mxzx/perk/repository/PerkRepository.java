package mxzx.perk.repository;

import lombok.Data;
import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.perk.model.Perk;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

@Data
public class PerkRepository implements Repository<Perk> {

    @Override
    public void save(Perk type) {
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
    public void update(Perk type) {
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
    public Perk load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Perk loaded = session.get(Perk.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Perk> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Perk> vouchers = session.createQuery("SELECT a FROM Perk a", Perk.class).getResultList();
        session.close();
        return vouchers;
    }

    @Override
    public void delete(Perk id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(id);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }


}
