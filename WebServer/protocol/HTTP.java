package WebServer.protocol;

public class HTTP {
    private static final String GET = "GET";

    public static Object get(String url) throws Exception {
        try {
            URLComponent urlComponent = URLComponent.parseUrl(url);
            String domain = urlComponent.getDomain();
            Integer port = urlComponent.getPort();
            Request request = new Request(GET, urlComponent.getPath());
            Object response = Network.call(domain, port, request);
            return response;
        } catch (Exception ex) {
            System.out.println("500 Error " + ex.getMessage());
            throw ex;
        }
    }
}
