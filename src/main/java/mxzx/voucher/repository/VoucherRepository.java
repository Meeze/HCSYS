package mxzx.voucher.repository;

import mxzx.database.Repository;
import mxzx.voucher.model.Voucher;

import java.util.List;

public class VoucherRepository implements Repository<Voucher> {
    @Override
    public void save(Voucher type) {

    }

    @Override
    public Voucher load(String id) {
        return null;
    }

    @Override
    public List<Voucher> loadAll() {
        return null;
    }

    @Override
    public void delete(Voucher type) {

    }
}
