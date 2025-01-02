package library;

public class Response {
    private Integer code;
    private String message;
    private String data;
    private String error;

    // Getters
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }

    public String getError() {
        return error;
    }

    // Setters
    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setError(String error) {
        this.error = error;
    }
}
