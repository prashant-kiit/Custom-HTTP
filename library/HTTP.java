package library;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class HTTP {
    public static String get(String url) {
        URLComponent urlComponent = URLComponent.parseUrl(url);
        String domain = urlComponent.getDomain();
        Integer port = urlComponent.getPort();

        try (Socket socket = new Socket(domain, port)) {
            // send request
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("Hello, Server! This is the client.");

            // receive response
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String serverMessage = reader.readLine();
            return serverMessage;
        } catch (Exception ex) {
            System.out.println("I/O error: " + ex.getMessage());
            return ex.getMessage();
        }
    }
}
