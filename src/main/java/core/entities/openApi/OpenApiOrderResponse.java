package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiOrderResponse {
    public static enum ResultType {SUCCESS,ERROR};

    private ResultType result;
    private ArrayList<String> error_messages;
    private Order data;

    public OpenApiOrderResponse() {
    }

    public OpenApiOrderResponse(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
        result = ResultType.ERROR;
    }

    public OpenApiOrderResponse(Order data) {
        this.data = data;
        result = ResultType.SUCCESS;
    }

    public ResultType getResult() {
        return result;
    }

    public void setResult(ResultType result) {
        this.result = result;
    }

    public ArrayList<String> getError_messages() {
        return error_messages;
    }

    public void setError_messages(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
    }

    public Order getData() {
        return data;
    }

    public void setData(Order data) {
        this.data = data;
    }
}
