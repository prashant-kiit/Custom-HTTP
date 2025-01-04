package backend.library;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import backend.src.application.Route;
import protocol.Request;
import protocol.Response;

public class Channel implements Runnable {
    private Socket socket;
    private Route route;

    public Channel(Socket socket) {
        this.socket = socket;
        this.route = new Route();
    }

    @Override
    public void run() {
        try {
            // Read the client's message
            InputStream input = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(input);
            Request request = (Request) in.readObject();
            System.out.println(
                    "Channel receives a request : method = " + request.getMethod() + ", path = " + request.getPath());
            in.close();

            // route the request to the correct controller
            String methodAndPath = request.getMethod() + ":" + request.getPath();
            Response response = route.getRoutes().entrySet().stream()
                    .filter(entry -> entry.getKey().equals(methodAndPath))
                    .findFirst()
                    .map(entry -> entry.getValue().apply(request))
                    .orElse(new Response().setCode(404).setMessage("Failure").setError("Route not found"));

            // Respond to the client
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            out.writeObject(response);
            out.close();
        } catch (Exception ex) {
            try {
                OutputStream output = socket.getOutputStream();
                ObjectOutputStream out = new ObjectOutputStream(output);
                Response response = new Response();
                response.setCode(500).setMessage("Error").setError(ex.getMessage());
                out.writeObject(response);
                out.close();
            } catch (Exception e) {
                System.out.println("Server exception: " + e.getMessage());
                e.printStackTrace();
            }
        } finally {
            try {
                socket.close();
            } catch (Exception e) {
                System.out.println("Server exception: " + e.getMessage());
                e.printStackTrace();
            }

        }
    }

}
