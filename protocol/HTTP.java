package protocol;

public class HTTP {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

    private static Response call(String method, String url) throws Exception {
        try {
            // parse url
            URLComponent urlComponent = URLComponent.parseUrl(url);
            // connect to server
            Connector connector = new Connector(urlComponent.getDomain(), urlComponent.getPort());
            // send request to server
            connector.sendRequest(GET, urlComponent.getPath());
            // receive response from server
            Response response = connector.receiveResponse();
            // close the connection
            connector.close();

            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Response get(String url) throws Exception {
        try {
            Response response = call(GET, url);
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Response post(String url, Object data) throws Exception {
        try {
            Response response = call(POST, url);
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Response put(String url, Object data) throws Exception {
        try {
            Response response = call(PUT, url);
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }

    public static Response delete(String url) throws Exception {
        try {
            Response response = call(DELETE, url);
            return response;
        } catch (Exception ex) {
            throw ex;
        }
    }
}
