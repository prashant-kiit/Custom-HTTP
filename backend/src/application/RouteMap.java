package backend.src.application;

import protocol.RouterHandler;

public class RouteMap {
    public RouteMap() {
        RouterHandler.insertRoute("GET=users", Controller.getUsers);
        RouterHandler.insertRoute("GET=users/:name", Controller.getUserById);
        RouterHandler.insertRoute("POST=user", Controller.postUser);
        RouterHandler.insertRoute("PUT=users/:name", Controller.putUser);
        RouterHandler.insertRoute("DELETE=users/:name", Controller.deleteUser);
    }
}
