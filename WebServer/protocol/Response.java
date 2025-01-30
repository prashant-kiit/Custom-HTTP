package WebServer.protocol;

import java.io.Serializable;
import java.util.stream.Stream;

public class Response implements Serializable {
    private Integer code;
    private String message;
    private Stream<?> data;
    private String error;

    // Getters
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Stream<?> getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    // Setters
    public Response setCode(Integer code) {
        this.code = code;
        return this;
    }

    public Response setMessage(String message) {
        this.message = message;
        return this;
    }

    public Response setData(Stream<?> data) {
        this.data = data;
        return this;
    }

    public Response setError(String error) {
        this.error = error;
        return this;
    }
}
