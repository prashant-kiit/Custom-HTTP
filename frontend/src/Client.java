import frontend.ENV;
import frontend.library.HTTP;
import protocol.Response;

public class Client {
    public static void main(String[] args) {
        try {
            System.out.println("Client is running");
            Response response = HTTP.get(ENV.BASE_URL + "/users");
            System.out.println("Code = " + response.getCode() + ", Message = " + response.getMessage() + ", Data = "
                    + response.getData());
        } catch (Exception ex) {
            System.out.println("Client exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}
