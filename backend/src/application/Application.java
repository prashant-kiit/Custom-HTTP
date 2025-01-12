package backend.src.application;

import protocol.Dispatcher;
import protocol.Server;
import java.io.IOException;
import backend.ENV;

public class Application {
    public static void main(String[] args) throws IOException {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.listen();
        Server server = new Server(ENV.DOMAIN, ENV.PORT);
        server.listen();
    }
}
