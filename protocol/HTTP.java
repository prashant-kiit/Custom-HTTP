package protocol;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HTTP {
    private static final String GET = "GET";
    private static final String POST = "POST";

    public static Response get(String url) throws Exception {
        URLComponent urlComponent = URLComponent.parseUrl(url);

        try (Socket socket = new Socket(urlComponent.getDomain(), urlComponent.getPort())) {
            // send request to server
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            Request request = new Request(GET, urlComponent.getPath());
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

        try (Socket socket = new Socket(urlComponent.getDomain(), urlComponent.getPort())) {
            // send request to server
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            Request request = new Request(POST, urlComponent.getPath(), data);
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
