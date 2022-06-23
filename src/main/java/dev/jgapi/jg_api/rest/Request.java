package dev.jgapi.jg_api.rest;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
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

    public HttpResponse execute(int timeout) {
        String endpointReplace = this.getRoute().getRoute();
        for (String key : this.getRouteReplacements().keySet()) {
            String value = this.getRouteReplacements().get(key);
            endpointReplace = endpointReplace.replace(key, value);
        }
        HttpRequest httpRequest = new HttpRequest(this.getRoute().getUrl() + this.getRoute().getVersion() + endpointReplace);
        httpRequest.method(this.getRoute().getMethod());
        for (String headerKey : this.getHeaders().keySet()) {
            String headerVal = this.getHeaders().get(headerKey);
            httpRequest.header(headerKey, headerVal);
        }
        httpRequest.timeout(timeout);
        String body = "";
        httpRequest.body(body);
        return httpRequest.execute();
    }
}
