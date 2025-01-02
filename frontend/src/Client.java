import library.HTTP;

public class Client {
    public static void main(String[] args) {
        String response = HTTP.get("http://localhost:8080/users");
        System.out.println(response);
        // Request request2 = HTTP.createPostRequest("http://localhost:8080/user",
        // "{\"name\":\"John\"}");
        // Response response = request2.send();
        // Request request3 = HTTP.createPutRequest("http://localhost:8080/user/1",
        // "{\"name\":\"John\"}");
        // Response response = request3.send();
        // Request request4 = HTTP.createDeleteRequest("http://localhost:8080");
        // Response response = request4.send();
    }
}
