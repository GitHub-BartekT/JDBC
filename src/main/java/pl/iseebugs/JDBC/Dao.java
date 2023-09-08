package pl.iseebugs.JDBC;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    List<T> readAll();
    Optional<T> read(int id);
    T create(T t);
    T update(T t);
    T delete(T t);
}
