package backend.src.application;

import backend.ENV;
import protocol.Container;

public class Application {
    public static void main(String[] args) {
        new Thread(new Container("Container 1", ENV.DOMAIN1, ENV.PORT1)).start();
        new Thread(new Container("Container 2", ENV.DOMAIN2, ENV.PORT2)).start();
        new Thread(new Container("Container 3", ENV.DOMAIN3, ENV.PORT3)).start();
    }
}
