package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiStringResponse {
    public static enum ResultType {SUCCESS,ERROR};

    private ResultType result;
    private ArrayList<String> error_messages;
    private String data;

    public OpenApiStringResponse() {
    }

    public OpenApiStringResponse(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
        result = ResultType.ERROR;
    }

    public OpenApiStringResponse(String data) {
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
