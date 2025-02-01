package WebServer.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.UnknownHostException;

public class ClientConnector {
    private Socket socket;
    private OutputStream output;
    private InputStream input;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClientConnector(String domain, Integer port) throws UnknownHostException, IOException {
        this.socket = new Socket(domain, port);
    }

    public void sendRequest(Request request) throws IOException {
        this.output = socket.getOutputStream();
        this.out = new ObjectOutputStream(output);
        out.writeObject(request);
    }

    public Object receiveResponse() throws IOException, ClassNotFoundException {
        this.input = socket.getInputStream();
        this.in = new ObjectInputStream(input);
        Object response = in.readObject();
        return response;
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}