package mxzx._core;

import java.util.List;

public interface BaseService<T> extends Base {

    void save(T type);
    void update(T type);
    T load(String id);
    List<T> loadAll();
    void delete(T type);
}
