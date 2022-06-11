package dev.JGAPI.JG_API.Rest;

import cn.hutool.json.JSONObject;

import java.util.HashMap;

public class Request {
    private Routing route;
    private HashMap<String, String> headers;
    private JSONObject body;
    public Request(Routing route, HashMap<String, String> headers, JSONObject body) {
        this.headers = headers;
        this.body = body;
    }
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }
    public JSONObject getBody() {
        return this.body;
    }

    public String execute() {
        // TODO RETURN SOMETHING BETTER THAN NULL;
        return null;
    }
}
