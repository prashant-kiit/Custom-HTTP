package protocol;

import backend.src.application.Controller;

public class RouteBuilder {
    public void listen() {
        RouterHandler.getInstance().insertRoute("GET=users", Controller.getUsers);
        RouterHandler.getInstance().insertRoute("GET=users/:name", Controller.getUserById);
        RouterHandler.getInstance().insertRoute("POST=user", Controller.postUser);
        RouterHandler.getInstance().insertRoute("PUT=users/:name", Controller.putUser);
        RouterHandler.getInstance().insertRoute("DELETE=users/:name", Controller.deleteUser);
    }
}
