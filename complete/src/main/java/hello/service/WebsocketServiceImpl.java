package hello.service;

import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
public class WebsocketServiceImpl implements WebsocketService {

    private final Set<WebSocketSession> sessions = new HashSet<>();

    @PostConstruct
    public void init() {

    }

    @Override
    public void send(String message) {
        for (WebSocketSession webSocketSession : sessions) {
            try {
                webSocketSession.sendMessage(new TextMessage("server sent: " + message));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void addWebsocketSession(WebSocketSession webSocketSession) {
        sessions.add(webSocketSession);
    }

    @Override
    public void removeWebsocketSession(WebSocketSession webSocketSession) {
        sessions.remove(webSocketSession);
    }

}