package protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connector {
    private Socket socket;
    private OutputStream output;
    private InputStream input;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public Connector(String domain, Integer port) throws UnknownHostException, IOException {
        this.socket = new Socket(domain, port);
    }

    public void sendRequest(String method, String path) throws IOException {
        this.output = socket.getOutputStream();
        this.out = new ObjectOutputStream(output);
        Request request = new Request(method, path);
        out.writeObject(request);
    }

    public Response receiveResponse() throws IOException, ClassNotFoundException {
        this.input = socket.getInputStream();
        this.in = new ObjectInputStream(input);
        Response response = (Response) in.readObject();
        return response;
    }

    public void close() throws IOException {
        in.close();
        out.close();
        socket.close();
    }
}