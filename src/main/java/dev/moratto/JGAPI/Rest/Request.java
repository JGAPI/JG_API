package dev.moratto.JGAPI.Rest;

import cn.hutool.json.JSONObject;

import java.util.HashMap;

public class Request {
    private HashMap<String, String> headers;
    private JSONObject body;
    public Request(HashMap<String, String> headers, JSONObject body) {
        this.headers = headers;
        this.body = body;
    }
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }
    public JSONObject getBody() {
        return this.body;
    }
}
