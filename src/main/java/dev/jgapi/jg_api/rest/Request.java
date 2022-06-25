package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.entities.HttpResponseEntity;
import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
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

    public HttpResponseEntity execute(int timeout) throws IOException {
        String endpointReplace = this.getRoute().getRoute();
        for (String key : this.getRouteReplacements().keySet()) {
            String value = this.getRouteReplacements().get(key);
            endpointReplace = endpointReplace.replace(key, value);
        }
        URL url = new URL(this.getRoute().getUrl() + this.getRoute().getVersion() + endpointReplace);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(this.route.getMethod());
        con.setDoOutput(true);
        for (String headerKey : this.getHeaders().keySet()) {
            String headerVal = this.getHeaders().get(headerKey);
            con.setRequestProperty(headerKey, headerVal);
        }
        String json = this.body.toString();
        byte[] out = json.getBytes(StandardCharsets.UTF_8);
        try(OutputStream os = con.getOutputStream()) {
            os.write(out);
        }
        con.setConnectTimeout(timeout);
        con.connect();
        String responseMessage = con.getResponseMessage();
        int responseCode = con.getResponseCode();
        con.disconnect();
        return new HttpResponseEntity(responseMessage, responseCode);
    }
}
