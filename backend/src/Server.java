package backend.src;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import backend.ENV;
import backend.src.application.Application;
import library.Request;
import library.Response;

public class Server {
    private static TableOfQueues tableOfQueues = new TableOfQueues();

    static {
        // read from routes an fill table of queues
        new Thread(new Application(tableOfQueues)).start();
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(ENV.PORT)) {
            System.out.println("Server is listening on port " + ENV.PORT + " at domain " + ENV.DOMAIN);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Read the client's message
                InputStream input = socket.getInputStream();
                ObjectInputStream in = new ObjectInputStream(input);
                Request request = (Request) in.readObject();
                tableOfQueues.insert(request);
                System.out.println("Client says: mehtod = " + request.getMethod() + ", path = " + request.getPath());

                // Respond to the client
                OutputStream output = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(output);
                Response response = new Response();
                // if message is success
                response.setCode(200);
                response.setMessage("Success");
                response.setData("Hello world!");
                // if message is failure
                // response.setCode(400);
                // response.setMessage("Failure");
                // response.setError("Client Error");
                out.writeObject(response);

                in.close();
                out.close();
                socket.close();
            }
        } catch (Exception ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

}
