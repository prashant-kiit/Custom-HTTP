import backend.src.application.database.User;
import frontend.ENV;
import protocol.HTTP;
import protocol.Response;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client is running");

            Response response1 = HTTP.get(ENV.BASE_URL + "/users");
            System.out.println("Code = " + response1.getCode() +
                    ", Message = " + response1.getMessage() +
                    ", Data = " + response1.getData());

            Response response2 = HTTP.get(ENV.BASE_URL + "/users/John");
            System.out.println("Code = " + response2.getCode() +
                    ", Message = " + response2.getMessage() +
                    ", Data = " + response2.getData());

            Response response3 = HTTP.post(ENV.BASE_URL + "/user", new User("Prashant", "password", "j@j.com"));
            System.out.println("Code = " + response3.getCode() +
                    ", Message = " + response3.getMessage() +
                    ", Data = " + response3.getData());
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
