package backend.src.application;

import java.util.ArrayList;
import java.util.function.Function;
import backend.src.application.database.User;
import library.Request;
import library.Response;

public class Controller {
    public static Function<Request, Response> getUsers = (request) -> {
        ArrayList<User> users = User.findUsers();
        return new Response().setCode(200).setMessage("Success").setData(users);
    };
}
