package library;

import java.io.Serializable;

public class Request implements Serializable {
    private String method;
    private String domain;
    private Integer port;
    private String path;

    public Request(String method, String domain, Integer port, String path) {
        this.method = method;
        this.domain = domain;
        this.port = port;
        this.path = path;
    }

    public String getMethod() {
        return method;
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
}
