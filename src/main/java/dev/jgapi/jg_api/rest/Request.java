package dev.jgapi.jg_api.rest;

import dev.jgapi.jg_api.entities.http.HttpResponseEntity;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
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
        endpointReplace += "?";
        if (this.getRoute().getMethod().equals("GET")) {
            for (String key : this.getBody().keySet()) {
                endpointReplace += key + "=" + this.getBody().get(key).toString() + "&";
            }
        }
        if (this.getBody().length() > 0)
            endpointReplace = endpointReplace.substring(0, (endpointReplace.length() - 1));
        URL url = new URL(this.getRoute().getUrl() + this.getRoute().getVersion() + endpointReplace);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod(this.route.getMethod());
        con.setDoOutput(true);
        for (String headerKey : this.getHeaders().keySet()) {
            String headerVal = this.getHeaders().get(headerKey);
            con.setRequestProperty(headerKey, headerVal);
        }
        byte[] out = null;
        if (this.getBody().toString().length() > 0 && !this.getRoute().getMethod().equals("GET")) {
            out = this.getBody().toString().getBytes(StandardCharsets.UTF_8);
            con.setFixedLengthStreamingMode(out.length);
        }
        con.setConnectTimeout(timeout);
        con.connect();
        if (this.getBody().toString().length() > 0 && !this.getRoute().getMethod().equals("GET")) {
            try (OutputStream os = con.getOutputStream()) {
                os.write(out);
            }
        }
        String responseMessage = readFullyAsString(con.getInputStream(), "UTF-8");
        int responseCode = con.getResponseCode();
        con.disconnect();
        return new HttpResponseEntity(responseMessage, responseCode);
    }
    private String readFullyAsString(InputStream inputStream, String encoding) throws IOException {
        return readFully(inputStream).toString(encoding);
    }

    private ByteArrayOutputStream readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, length);
        }
        return baos;
    }
}
