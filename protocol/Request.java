package protocol;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Request implements Serializable {
    private String method;
    private String path;
    private Object data;
    private Map<String, String> params;

    public Request(String method, String path) {
        this.method = method;
        this.path = path;
        this.params = new HashMap<>();
    }

    public Request(String method, String path, Object data) {
        this.method = method;
        this.path = path;
        this.data = data;
        this.params = new HashMap<>();
    }

    public String getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    public Object getData() {
        return data;
    }

    public String getParams(String paremKey) {
        return params.get(paremKey);
    }

    public void setParams(String paramKey, String paramValue) {
        params.put(paramKey, paramValue);
    }

    @Override
    public String toString() {
        return "Request{" +
                "method='" + method + '\'' +
                ", path='" + path + '\'' +
                ", data=" + data +
                ", params=" + params +
                '}';
    }
}
