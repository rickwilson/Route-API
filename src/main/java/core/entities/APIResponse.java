package core.entities;

public class APIResponse {
    public static enum ResultType {SUCCESS,ERROR};

    private ResultType result;
    private String message;

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
