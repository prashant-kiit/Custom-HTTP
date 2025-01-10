package protocol;

public class Network {
    public static Response call(String domain, Integer port, Request request) throws Exception {
        try {
            Connector connector = new Connector(domain, port);
            connector.sendRequest(request);
            Response response = connector.receiveResponse();
            connector.close();
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
