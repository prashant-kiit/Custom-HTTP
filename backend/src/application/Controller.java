package backend.src.application;

import java.util.ArrayList;
import java.util.function.Function;
import backend.src.application.database.User;
import protocol.Request;
import protocol.Response;

public class Controller {
    public static Function<Request, Response> getUsers = (request) -> {
        try {
            ArrayList<User> users = User.findUsers();
            return new Response().setCode(200).setMessage("Success").setData(users);
        } catch (Exception e) {
            return new Response().setCode(400).setMessage("Error").setError(e.getMessage());
        }

    };
}
