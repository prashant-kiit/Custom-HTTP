package library;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTTP {
    public static Response get(String url) {
        String method = "GET";
        URLComponent urlComponent = URLComponent.parseUrl(url);
        String domain = urlComponent.getDomain();
        Integer port = urlComponent.getPort();
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
            Response response = new Response();
            response.setCode(500);
            response.setMessage("Internal Server Error");
            response.setError(ex.getMessage());

            return response;
        }
    }
}
