package dev.moratto.JGAPI.Websocket;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletionStage;

public class WebSocketListener implements WebSocket.Listener {
    private WebSocketManager webSocketManager;

    public WebSocketListener(WebSocketManager webSocketManager) {
        this.webSocketManager = webSocketManager;
    }

    @Override
    public void onOpen(WebSocket webSocket) {
        WebSocket.Listener.super.onOpen(webSocket);
    }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        System.out.println("Text: " + data);
        this.webSocketManager.parseWebsocketMessage(data);
        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    @Override
    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        System.out.println("Binary: " + data);
        return WebSocket.Listener.super.onBinary(webSocket, data, last);
    }

    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        System.out.println("Ping: " + message);
        return WebSocket.Listener.super.onPing(webSocket, message);
    }

    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        System.out.println("Pong: " + message);
        return WebSocket.Listener.super.onPong(webSocket, message);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        System.out.println(statusCode + " " + reason);
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        error.printStackTrace();
        WebSocket.Listener.super.onError(webSocket, error);
    }
}
