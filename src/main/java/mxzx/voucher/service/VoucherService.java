package mxzx.voucher.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.Session;
import org.hibernate.Transaction;
import mxzx.HibernateUtil;
import mxzx.database.Repository;
import mxzx.voucher.model.Voucher;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class VoucherService  implements Repository<Voucher> {


    @Override
    public void save(Voucher type) {
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
        }}

    @Override
    public Voucher load(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Voucher loaded = session.get(Voucher.class, id);
        session.close();
        return loaded;
    }

    @Override
    public List<Voucher> loadAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        List<Voucher> vouchers = session.createQuery("SELECT a FROM Voucher a", Voucher.class).getResultList();
        session.close();
        return vouchers;
    }

    @Override
    public void delete(Voucher id) {
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

