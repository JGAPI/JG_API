package dev.jgapi.jg_api.entities.http;

public class HttpResponseEntity {
    private String response;
    private int responseCode;
    public HttpResponseEntity(String response, int responseCode) {
        this.response = response;
        this.responseCode = responseCode;
    }

    public String getResponse() {
        return this.response;
    }

    public int getResponseCode() {
        return this.responseCode;
    }
}
