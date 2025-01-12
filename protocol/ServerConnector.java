package protocol;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector {
    private ServerSocket serverSocket;

    public ServerConnector(Integer port) throws Exception {
        this.serverSocket = new ServerSocket(port);
    }

    public Connector accept() throws IOException {
        Socket socket = this.serverSocket.accept();
        Connector connector = new Connector();
        connector.setSocket(socket);
        return connector;
    }
}
