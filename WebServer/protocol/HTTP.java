package WebServer.protocol;

public class HTTP {
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String PUT = "PUT";
    private static final String DELETE = "DELETE";

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

    // public static Response post(String url, Object data) throws Exception {
    //     try {
    //         URLComponent urlComponent = URLComponent.parseUrl(url);
    //         String domain = urlComponent.getDomain();
    //         Integer port = urlComponent.getPort();
    //         Request request = new Request(POST, urlComponent.getPath(), data);
    //         Response response = Network.call(domain, port, request);
    //         return response;
    //     } catch (Exception ex) {
    //         System.out.println("500 Error " + ex.getMessage());
    //         throw ex;
    //     }
    // }

    // public static Response put(String url, Object data) throws Exception {
    //     try {
    //         URLComponent urlComponent = URLComponent.parseUrl(url);
    //         String domain = urlComponent.getDomain();
    //         Integer port = urlComponent.getPort();
    //         Request request = new Request(PUT, urlComponent.getPath(), data);
    //         Response response = Network.call(domain, port, request);
    //         return response;
    //     } catch (Exception ex) {
    //         System.out.println("500 Error " + ex.getMessage());
    //         throw ex;
    //     }
    // }

    // public static Response delete(String url) throws Exception {
    //     try {
    //         URLComponent urlComponent = URLComponent.parseUrl(url);
    //         String domain = urlComponent.getDomain();
    //         Integer port = urlComponent.getPort();
    //         Request request = new Request(DELETE, urlComponent.getPath());
    //         Response response = Network.call(domain, port, request);
    //         return response;
    //     } catch (Exception ex) {
    //         System.out.println("500 Error " + ex.getMessage());
    //         throw ex;
    //     }
    // }
}
