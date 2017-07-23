package hello.config;

import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;

@Component
public class MessageHandler extends TextWebSocketHandler {

    @Autowired
    WebsocketService websocketService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {
        if ("ping".equalsIgnoreCase(textMessage.getPayload())) {
            session.sendMessage(new TextMessage("pong"));
        } else {
            CharSequence sequence = "server responded with echo \'" + textMessage.getPayload() + "\'";
            session.sendMessage(new TextMessage(sequence));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        websocketService.addWebsocketSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        websocketService.removeWebsocketSession(session);
    }

}
