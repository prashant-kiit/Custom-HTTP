package WebServer.backend.src.application;

import java.util.ArrayList;
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

    // public static Function<Request, Response> getUserById = (request) -> {
    // try {
    // User user = User.findUserByName(request.getParams("name"));
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

    // public static Function<Request, Response> postUser = (request) -> {
    // try {
    // ArrayList<User> users = User.insertUsers((User) request.getData());
    // if (users.size() == 0) {
    // return new Response().setCode(404).setMessage("Failure").setError("No users
    // found");
    // }
    // return new Response().setCode(200).setMessage("Success").setData(users);
    // } catch (Exception e) {
    // return new
    // Response().setCode(500).setMessage("Error").setError(e.getMessage());
    // }
    // };

    // public static Function<Request, Response> putUser = (request) -> {
    // try {
    // User user = User.updateUserByName(request.getParams("name"), (User)
    // request.getData());
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

    // public static Function<Request, Response> deleteUser = (request) -> {
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
