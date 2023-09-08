package pl.iseebugs.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<User> {

    public UserDao() {
    }

    @Override
    public List<User> readAll() {
        List<User> userList = new ArrayList<>();

        try {
            String read = "SELECT \"USER_ID\", \"USER_NAME\", \"USER_ROLE\" FROM public.users";
            ResultSet result = DataAccessLayer.executeSelect(read);
            while (result.next()) {
                User user = new User();
                user.setUserId(result.getInt("USER_ID"));
                user.setUserName(result.getString("USER_NAME"));
                user.setUserRole(result.getString("USER_ROLE"));
                userList.add(user);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public Optional<User> read(int id) {
        User user = new User();
        try{
            String readById = String.format("SELECT * FROM public.users WHERE \"USER_ID\" = '%s'", id);
            ResultSet result = DataAccessLayer.executeSelect(readById);
            while (result.next()) {
                user.setUserId(result.getInt("USER_ID"));
                user.setUserName(result.getString("USER_NAME"));
                user.setUserRole(result.getString("USER_ROLE"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return Optional.ofNullable(user);
    }

    @Override
    public User create(User entity) {

        if (readByName(entity.getUserName()) == null){
            return null;
        }

        String createUser = String.format("INSERT INTO public.users (\"USER_NAME\", \"USER_ROLE\") VALUES ('%s', '%s')",
                entity.getUserName(),entity.getUserRole());
        DataAccessLayer.executeQuery(createUser);
        return entity;
    }

    @Override
    public User update(User entity) {
        String updateUser = String.format("UPDATE public.users SET \"USER_ROLE\"='%s' WHERE \"USER_NAME\"='%s'",
                entity.getUserRole(), entity.getUserName());
        DataAccessLayer.executeQuery(updateUser);
        return entity;
    }

    @Override
    public User delete(User entity) {
        String deleteUser = String.format("DELETE FROM public.users WHERE \"USER_NAME\"='%s'",
                entity.getUserName());
        DataAccessLayer.executeQuery(deleteUser);
        return null;
    }

    public User readByName(String name) {
        User user = new User();
        try{
            String readByName = String.format("SELECT * FROM public.users WHERE \"USER_NAME\" = '%s'", name);
            ResultSet result = DataAccessLayer.executeSelect(readByName);
            while (result.next()) {
                user.setUserId(result.getInt("USER_ID"));
                user.setUserName(result.getString("USER_NAME"));
                user.setUserRole(result.getString("USER_ROLE"));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }
}
