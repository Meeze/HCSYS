package mxzx.database;

import java.util.List;

public interface Repository<T> {

    void save(T type);
    void update(T type);
    T load(String id);
    List<T> loadAll();
    void delete(T type);
}
