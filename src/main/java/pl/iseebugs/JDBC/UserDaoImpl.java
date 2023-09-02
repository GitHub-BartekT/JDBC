package pl.iseebugs.JDBC;

import java.util.List;

public interface UserDaoImpl extends Dao<User, Integer> {

    List<User> read();
    User readById(Integer id);
    User readByName(String id);
    User create(User entity);
    User update(User entity);
    User delete(User entity);
}
