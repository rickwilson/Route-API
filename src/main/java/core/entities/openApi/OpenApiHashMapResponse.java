package core.entities.openApi;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenApiHashMapResponse {
    public static enum ResultType {SUCCESS,ERROR};

    private ResultType result;
    private ArrayList<String> error_messages;
    private HashMap<String, String> data;

    public OpenApiHashMapResponse() {
    }

    public OpenApiHashMapResponse(ArrayList<String> error_messages) {
        this.error_messages = error_messages;
        result = ResultType.ERROR;
    }

    public OpenApiHashMapResponse(HashMap<String, String> data) {
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

    public HashMap<String, String> getData() {
        return data;
    }

    public void setData(HashMap<String, String> data) {
        this.data = data;
    }
}
