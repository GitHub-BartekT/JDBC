package pl.iseebugs;

import pl.iseebugs.JDBC.User;
import pl.iseebugs.JDBC.UserDao;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserDao repository = new UserDao();

        List<User> users = repository.readAll();
        users.forEach(t -> System.out.println(t));
    }
}