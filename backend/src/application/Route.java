package backend.src.application;

import protocol.RouterHandler;

public class Route extends RouterHandler {
    public Route() {
        this.insertRoute("GET=users", Controller.getUsers);
        this.insertRoute("GET=users/:name", Controller.getUserById);
        this.insertRoute("POST=user", Controller.postUser);
        this.insertRoute("PUT=users/:name", Controller.putUser);
        this.insertRoute("DELETE=users/:name", Controller.deleteUser);
    }
}
