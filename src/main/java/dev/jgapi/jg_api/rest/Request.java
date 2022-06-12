package dev.jgapi.jg_api.rest;

import cn.hutool.json.JSONObject;

import java.util.HashMap;

public class Request {
    private Routing route;
    private HashMap<String, String> routeReplacements;
    private HashMap<String, String> headers;
    private JSONObject body;
    public Request(Routing route, HashMap<String, String> routeReplacements, HashMap<String, String> headers, JSONObject body) {
        this.route = route;
        this.routeReplacements = routeReplacements;
        this.headers = headers;
        this.body = body;
    }
    public Routing getRoute() {
        return this.route;
    }
    public HashMap<String, String> getRouteReplacements() {
        return this.routeReplacements;
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
