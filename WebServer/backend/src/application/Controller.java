package WebServer.backend.src.application;

import java.util.function.Function;
import java.util.stream.Stream;

import WebServer.backend.src.application.database.User;
import WebServer.protocol.Request;
import WebServer.protocol.Response;

public class Controller {
    public static Function<Request, Response> getUsers = (request) -> {
        try {
            Stream<User> users = User.findUsers();
            return new Response().setCode(200).setMessage("Success").setData(users);
        } catch (Exception e) {
            return new Response().setCode(500).setMessage("Error").setError(e.getMessage());
        }
    };
    // try {
    // User user = User.deleteUserByName(request.getParams("name"));
    // if (user == null) {
    // return new Response().setCode(404).setMessage("Failure").setError("No users
    // found");
    // }
    // return new Response().setCode(200).setMessage("Success").setData(user);
    // } catch (Exception e) {
    // return new
    // Response().setCode(500).setMessage("Error").setError(e.getMessage());
    // }
    // };
}
