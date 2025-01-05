package backend.src.application.database;

import java.util.ArrayList;

public class User {
    @SuppressWarnings("unused")
    private String name;
    @SuppressWarnings("unused")
    private String password;
    @SuppressWarnings("unused")
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
}
