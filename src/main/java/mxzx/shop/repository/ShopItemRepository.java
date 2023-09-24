package mxzx.shop.repository;

import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.shop.model.ShopItem;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class ShopItemRepository implements Repository<ShopItem> {

    @Override
    public void save(ShopItem entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public void update(ShopItem entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public ShopItem load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        ShopItem loaded = session.get(ShopItem.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<ShopItem> loadAll() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<ShopItem> all = session.createQuery("SELECT a FROM ShopItem a", ShopItem.class).getResultList();
        session.close();
        if (all == null) {
            return new ArrayList<>();
        }
        return all;
    }

    @Override
    public void delete(ShopItem entity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(entity);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}
