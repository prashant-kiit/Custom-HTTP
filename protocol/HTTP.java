package protocol;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import backend.src.application.database.User;

public class HTTP {
    private static final String GET = "GET";
    private static final String POST = "POST";

    public static Response get(String url) throws Exception {
        URLComponent urlComponent = URLComponent.parseUrl(url);
        String domain = urlComponent.getDomain();
        Integer port = urlComponent.getPort();
        String method = GET;
        String path = urlComponent.getPath();

        try (Socket socket = new Socket(domain, port)) {
            // send request to server
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            Request request = new Request(method, domain, port, path);
            out.writeObject(request);

            // receive response from server
            InputStream input = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(input);
            Response response = (Response) in.readObject();

            in.close();
            out.close();
            socket.close();

            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Response post(String url, Object data) throws Exception {
        URLComponent urlComponent = URLComponent.parseUrl(url);
        String domain = urlComponent.getDomain();
        Integer port = urlComponent.getPort();
        String method = POST;
        String path = urlComponent.getPath();

        try (Socket socket = new Socket(domain, port)) {
            // send request to server
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            Request request = new Request(method, domain, port, path, data);
            out.writeObject(request);

            // receive response from server
            InputStream input = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(input);
            Response response = (Response) in.readObject();

            // close the connection
            in.close();
            out.close();
            socket.close();

            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
