import WebServer.backend.src.application.database.User;
import WebServer.frontend.ENV;
import WebServer.protocol.HTTP;
import WebServer.protocol.Response;

public class Client {
        public static void main(String[] args) {
                try {
                        System.out.println("Client is running");

                        Response response1 = HTTP.get(ENV.BASE_URL + "/users");
                        System.out.println("Code = " + response1.getCode() +
                                        ", Message = " + response1.getMessage() +
                                        ", Data = " + response1.getData() +
                                        ", Error = " + response1.getError());

                        Response response2 = HTTP.get(ENV.BASE_URL + "/users/John");
                        System.out.println("Code = " + response2.getCode() +
                                        ", Message = " + response2.getMessage() +
                                        ", Data = " + response2.getData() +
                                        ", Error = " + response2.getError());

                        Response response3 = HTTP.post(ENV.BASE_URL + "/user", new User("Prashant",
                                        "password", "j@j.com"));
                        System.out.println("Code = " + response3.getCode() +
                                        ", Message = " + response3.getMessage() +
                                        ", Data = " + response3.getData() +
                                        ", Error = " + response3.getError());

                        Response response4 = HTTP.put(ENV.BASE_URL + "/users/John", new User("John",
                                        "password", "john@john.com"));
                        System.out.println("Code = " + response4.getCode() +
                                        ", Message = " + response4.getMessage() +
                                        ", Data = " + response4.getData() +
                                        ", Error = " + response4.getError());

                        Response response5 = HTTP.delete(ENV.BASE_URL + "/users/Joh");
                        System.out.println("Code = " + response5.getCode() +
                                        ", Message = " + response5.getMessage() +
                                        ", Data = " + response5.getData() +
                                        ", Error = " + response5.getError());
                } catch (Exception ex) {
                        System.out.println("Exception: " + ex.getMessage());
                        ex.printStackTrace();
                }
        }
}
