import WebServer.frontend.ENV;
import WebServer.protocol.HTTP;

public class Client {
        public static void main(String[] args) {
                try {
                        System.out.println("Client is running");

                        Object response1 = HTTP.get(ENV.BASE_URL + "/users");
                        System.out.println(response1);
                } catch (Exception ex) {
                        System.out.println("Exception: " + ex.getMessage());
                        ex.printStackTrace();
                }
        }
}
