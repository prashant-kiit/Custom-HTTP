package protocol;

import java.util.Arrays;
import java.util.Map;

public class URLComponent {
    private String domain;
    private Integer port;
    private String path;

    public URLComponent(String domain, Integer port, String path) {
        this.domain = domain;
        this.port = port;
        this.path = path;
    }

    public String getDomain() {
        return domain;
    }

    public Integer getPort() {
        return port;
    }

    public String getPath() {
        return path;
    }

    public static URLComponent parseUrl(String url) {
        String uri = url.replaceFirst("^(http://|https://)", "");

        String[] originpathparts = uri.split("/", 2);
        String origin = originpathparts[0];
        String path = originpathparts[1];

        String[] domainportparts = origin.split(":", 2);
        String domain = domainportparts[0];
        Integer port = Integer.parseInt(domainportparts[1]);

        return new URLComponent(domain, port, path);
    }

    public static Boolean isSimilar(Map<String, String> key, Request request) {
        String keyMethod = key.get("method");
        String keyPath = key.get("path");
        String keyPathParts[] = keyPath.split("/");
        String requestMethod = request.getMethod();
        String requestPath = request.getPath();
        String slugPathParts[] = requestPath.split("/");

        if (!keyMethod.equals(requestMethod) || keyPathParts.length != slugPathParts.length) {
            return false;
        }

        for (Integer index = 0; index < keyPathParts.length; index++) {
            if (!keyPathParts[index].equals(slugPathParts[index])) {
                if (keyPathParts[index].charAt(0) == ':') {
                    request.setParams(keyPathParts[index].substring(1), slugPathParts[index]);
                    continue;
                }
                return false;
            }
        }

        return true;
    }
}
