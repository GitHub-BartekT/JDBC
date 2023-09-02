package pl.iseebugs.JDBC;

import java.util.List;

public interface Dao<T,K> {
    List<T> read();
    T readById(K id);
    T create(T entity);
    T update(T entity);
    T delete(T entity);
}
