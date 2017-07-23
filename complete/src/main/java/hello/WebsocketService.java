package hello;

import org.springframework.web.socket.WebSocketSession;

public interface WebsocketService {

    void addWebsocketSession(WebSocketSession webSocketSession);

    void removeWebsocketSession(WebSocketSession webSocketSession);

    void send(String message);

}