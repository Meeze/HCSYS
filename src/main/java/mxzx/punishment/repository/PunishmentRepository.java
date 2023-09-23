package mxzx.punishment.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.punishment.model.Punishment;

import java.util.ArrayList;
import java.util.List;

public class PunishmentRepository implements Repository<Punishment> {

    @Override
    public void save(Punishment punishment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(punishment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public Punishment load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Punishment loaded = session.get(Punishment.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Punishment> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<Punishment> punishments = session.createQuery("SELECT a FROM Punishment a", Punishment.class).getResultList();
        session.close();
        if (punishments == null) {
            return new ArrayList<>();
        }
        return punishments;
    }

    @Override
    public void delete(Punishment punishment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(punishment);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

}
