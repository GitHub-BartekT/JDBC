package pl.iseebugs.JDBC;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements UserDaoImpl{

    public UserDao() {
    }

    @Override
    public List<User> read() {
        List<User> userList = new ArrayList<>();

        try {
            String read = "SELECT \"USER_ID\", \"USER_NAME\", \"USER_ROLE\" FROM public.users";
            ResultSet result = UserDal.executeSelect(read);
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
    public User readById(Integer id) {
        User user = new User();
        try{
            String readById = String.format("SELECT * FROM public.users WHERE \"USER_ID\" = '%s'", id);
            ResultSet result = UserDal.executeSelect(readById);
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

    @Override
    public User readByName(String name) {
        User user = new User();
        try{
            String readByName = String.format("SELECT * FROM public.users WHERE \"USER_NAME\" = '%s'", name);
            ResultSet result = UserDal.executeSelect(readByName);
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

    @Override
    public User create(User entity) {

        if (readByName(entity.getUserName()) == null){
            return null;
        }

        String createUser = String.format("INSERT INTO public.users (\"USER_NAME\", \"USER_ROLE\") VALUES ('%s', '%s')",
                entity.getUserName(),entity.getUserRole());
        UserDal.executeQuery(createUser);
        return entity;
    }

    @Override
    public User update(User entity) {
        String updateUser = String.format("UPDATE public.users SET \"USER_ROLE\"='%s' WHERE \"USER_NAME\"='%s'",
                entity.getUserRole(), entity.getUserName());
        UserDal.executeQuery(updateUser);
        return entity;
    }

    @Override
    public User delete(User entity) {
        String deleteUser = String.format("DELETE FROM public.users WHERE \"USER_NAME\"='%s'",
                entity.getUserName());
        UserDal.executeQuery(deleteUser);
        return null;
    }
}
