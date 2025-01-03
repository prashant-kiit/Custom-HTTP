package library;

import java.io.Serializable;

public class Response implements Serializable {
    private Integer code;
    private String message;
    private Object data;
    private Object error;

    // Getters
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public Object getError() {
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

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Response setError(Object error) {
        this.error = error;
        return this;
    }
}
