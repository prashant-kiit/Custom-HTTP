package WebServer.backend.src.application;

import WebServer.protocol.RouterHandler;

public class RouteBuilder {
    private RouterHandler routerHandler;

    public RouteBuilder(RouterHandler routerHandler) {
        this.routerHandler = routerHandler;
    }

    public void listen() {
        routerHandler.insertRoute("GET=users", Controller.getUsers);
        // routerHandler.insertRoute("GET=users/:name", Controller.getUserById);
        // routerHandler.insertRoute("POST=user", Controller.postUser);
        // routerHandler.insertRoute("PUT=users/:name", Controller.putUser);
        // routerHandler.insertRoute("DELETE=users/:name", Controller.deleteUser);
    }
}
