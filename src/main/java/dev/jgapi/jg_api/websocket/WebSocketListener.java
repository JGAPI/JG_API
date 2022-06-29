package dev.jgapi.jg_api.websocket;


import dev.jgapi.jg_api.exceptions.InvalidOperationException;
import org.json.JSONObject;

import java.net.http.WebSocket;
import java.nio.ByteBuffer;
import java.util.concurrent.CompletionStage;

public class WebSocketListener implements WebSocket.Listener {
    private WebSocketManager webSocketManager;

    public WebSocketListener(WebSocketManager webSocketManager) {
        this.webSocketManager = webSocketManager;
    }

    @Override
    public void onOpen(WebSocket webSocket) { WebSocket.Listener.super.onOpen(webSocket); }

    @Override
    public CompletionStage<?> onText(WebSocket webSocket, CharSequence data, boolean last) {
        JSONObject json = new JSONObject(data.toString());
        int opcode = json.getInt("op");
        switch (opcode) {
            case 0:
                this.webSocketManager.parseWebsocketMessage(json);
                break;
            case 1:
                try {
                    this.webSocketManager.parseWebsocketWelcome(json);
                } catch (InvalidOperationException e) {
                    throw new RuntimeException(e);
                }
                break;
            default:
                System.out.println("Unhandled OpCode: " + opcode);
        }

        return WebSocket.Listener.super.onText(webSocket, data, last);
    }

    @Override
    public CompletionStage<?> onBinary(WebSocket webSocket, ByteBuffer data, boolean last) {
        return WebSocket.Listener.super.onBinary(webSocket, data, last);
    }

    @Override
    public CompletionStage<?> onPing(WebSocket webSocket, ByteBuffer message) {
        return WebSocket.Listener.super.onPing(webSocket, message);
    }

    @Override
    public CompletionStage<?> onPong(WebSocket webSocket, ByteBuffer message) {
        return WebSocket.Listener.super.onPong(webSocket, message);
    }

    @Override
    public CompletionStage<?> onClose(WebSocket webSocket, int statusCode, String reason) {
        return WebSocket.Listener.super.onClose(webSocket, statusCode, reason);
    }

    @Override
    public void onError(WebSocket webSocket, Throwable error) {
        error.printStackTrace();
        WebSocket.Listener.super.onError(webSocket, error);
    }
}
