package WebServer.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Connector {
    private Socket socket;
    private InputStream input;
    private ObjectInputStream in;
    private Request request;
    private OutputStream output;
    private ObjectOutputStream out;
    private Response response;

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public void receiveRequest() throws IOException, ClassNotFoundException {
        this.input = this.socket.getInputStream();
        this.in = new ObjectInputStream(input);
        this.request = (Request) this.in.readObject();
        System.out.println("Connector receives a request : request = " + request);
    }

    public void sendResponse(Response response) throws IOException {
        this.response = response;
        this.output = this.socket.getOutputStream();
        this.out = new ObjectOutputStream(output);
        response.getData().forEach(t -> {
            try {
                out.writeObject(t);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        // out.writeObject(response);
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }

    public Request getRequest() {
        return this.request;
    }

    public Response getResponse() {
        return this.response;
    }

}
