package core.entities.konnektive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class KonnektiveRegisterResponse {
    public static enum ResultType {SUCCESS,ERROR};

    private ResultType result;
    private ArrayList<String> error_messages;
    private Account data;

    public KonnektiveRegisterResponse() {
    }

    public KonnektiveRegisterResponse(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
        result = ResultType.ERROR;
    }

    public KonnektiveRegisterResponse(Account data) {
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

    public Account getData() {
        return data;
    }

    public void setData(Account data) {
        this.data = data;
    }
}
