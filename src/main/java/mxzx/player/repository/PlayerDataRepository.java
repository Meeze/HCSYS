package mxzx.player.repository;

import org.hibernate.Session;
import org.hibernate.Transaction;
import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.player.model.PlayerData;

import java.util.ArrayList;
import java.util.List;

public class PlayerDataRepository  implements Repository<PlayerData> {

    @Override
    public void save(PlayerData toSave) {
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
    public PlayerData load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        PlayerData loaded = session.get(PlayerData.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<PlayerData> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<PlayerData> punishments = session.createQuery("SELECT a FROM PlayerData a", PlayerData.class).getResultList();
        session.close();
        if (punishments == null) {
            return new ArrayList<>();
        }
        return punishments;
    }

    @Override
    public void delete(PlayerData toDelete) {
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