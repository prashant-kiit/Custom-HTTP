package StreamServer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 5000);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            Stream<String> stream = reader.lines();
            List<String> response = stream.collect(Collectors.toList());

            System.out.println("Received: " + response);
        }

        socket.close();
    }
}
