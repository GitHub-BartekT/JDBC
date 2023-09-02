package pl.iseebugs;

import org.postgresql.core.QueryExecutor;
import pl.iseebugs.JDBC.DBConnector;
import pl.iseebugs.JDBC.User;
import pl.iseebugs.JDBC.UserDal;
import pl.iseebugs.JDBC.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao repository = new UserDao();

        List<User> users = repository.read();
        users.forEach(t -> System.out.println(t));
    }
}