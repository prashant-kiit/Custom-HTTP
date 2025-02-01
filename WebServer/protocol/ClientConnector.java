package WebServer.protocol;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
        // recieve stream of response data on client at applicaton layer under one connection instance
        Stream<Object> response = StreamSupport.stream(new Spliterators.AbstractSpliterator<Object>(
                Long.MAX_VALUE, 0) {
            @Override
            public boolean tryAdvance(java.util.function.Consumer<? super Object> action) {
                try {
                    Object obj = in.readObject();
                    if ("END".equals(obj))
                        return false; // Stop on "END"
                    action.accept(obj);
                    return true;
                } catch (Exception e) {
                    return false; // End of stream
                }
            }
        }, false);

        List<Object> responses = response.collect(Collectors.toList());

        // Object response = in.readObject();
        return new ArrayList<>(responses);
    }

    public void close() throws IOException {
        this.in.close();
        this.out.close();
        this.socket.close();
    }
}