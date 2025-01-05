package backend.src.application.database;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String name;
    private String password;
    private String email;

    public User(String name, String password, String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public static ArrayList<User> findUsers() {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));
        return users;
    }

    public static ArrayList<User> insertUsers(User data) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));
        users.add(data);
        return users;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
