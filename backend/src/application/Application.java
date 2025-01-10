package backend.src.application;

import protocol.Server;
import java.io.IOException;
import backend.ENV;

public class Application {
    public static void main(String[] args) throws IOException {
        Server server = new Server(ENV.DOMAIN, ENV.PORT);
        Route route = new Route();
        server.setRoute(route);
        server.listen();
    }
}
