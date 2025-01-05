package protocol;

import java.io.Serializable;

public class Request implements Serializable {
    private String domain;
    private Integer port;
    private String method;
    private String path;
    private Object data;

    public Request(String method, String domain, Integer port, String path) {
        this.method = method;
        this.domain = domain;
        this.port = port;
        this.path = path;
    }

    public Request(String method, String domain, Integer port, String path, Object data) {
        this.method = method;
        this.domain = domain;
        this.port = port;
        this.path = path;
        this.data = data;
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

    public Object getData() {
        return data;
    }
}
