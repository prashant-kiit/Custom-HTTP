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

    public static User findUserByName(String name) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));

        for (User user : users) {
            if (user.name.equals(name)) {
                return user;
            }
        }
        return null;
    }

    public static ArrayList<User> insertUsers(User data) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));
        users.add(data);
        return users;
    }

    public static User updateUserByName(String name, User data) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));

        for (User user : users) {
            if (user.name.equals(name)) {
                user.name = data.name;
                user.password = data.password;
                user.email = data.email;
                return user;
            }
        }
        return null;
    }

    public static User deleteUserByName(String name) {
        ArrayList<User> users = new ArrayList<>();
        users.add(new User("John", "password", "j@j.com"));
        users.add(new User("Jane", "password", "j@j.com"));
        users.add(new User("Bob", "password", "j@j.com"));

        for (User user : users) {
            if (user.name.equals(name)) {
                users.remove(user);
                return user;
            }
        }
        return null;
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
