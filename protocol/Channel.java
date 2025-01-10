package protocol;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

import backend.src.application.Route;

public class Channel implements Runnable {
    private Socket socket;
    private Route route;

    public Channel(Socket socket, Route route) {
        this.socket = socket;
        this.route = route;
    }

    @Override
    public void run() {
        try {
            // Read the client's message
            InputStream input = socket.getInputStream();
            ObjectInputStream in = new ObjectInputStream(input);
            Request request = (Request) in.readObject();
            System.out.println("Channel receives a request : request = " + request);

            // route the request to the correct controller
            Response response = route.getRoutes().entrySet().stream()
                    .filter(entry -> URLComponent.isSimilar(entry.getKey(), request)).findFirst()
                    .map(entry -> entry.getValue().apply(request))
                    .orElse(new Response().setCode(404).setMessage("Failure").setError("Route not found"));

            // Respond to the client
            OutputStream output = socket.getOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(output);
            out.writeObject(response);
            System.out.println("Response: " + response.getData());

            // Close the connection
            in.close();
            out.close();
            socket.close();
        } catch (Exception ex) {
            System.out.println("Channel exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
