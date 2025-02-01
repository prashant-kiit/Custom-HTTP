package WebServer.protocol;

public class Network {
    public static Object call(String domain, Integer port, Request request) throws Exception {
        try {
            ClientConnector clientConnector = new ClientConnector(domain, port);
            clientConnector.sendRequest(request);
            Object response = clientConnector.receiveResponse();
            clientConnector.close();
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
