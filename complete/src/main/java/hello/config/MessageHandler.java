package hello.config;

import hello.service.WebsocketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class MessageHandler extends TextWebSocketHandler {

    @Autowired
    WebsocketService websocketService;

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage textMessage) throws IOException {

//        HttpServletRequest
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal);
//
//        if (principal instanceof UserDetails) {
//            String username = ((UserDetails) principal).getUsername();
//            System.out.println(username);
//        } else {
//            String username = principal.toString();
//            System.out.println(username);
//        }
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        }

        if ("ping".equalsIgnoreCase(textMessage.getPayload())) {
            session.sendMessage(new TextMessage("pong"));
        } else {
            CharSequence sequence = "server responded with echo \'" + textMessage.getPayload() + "\'";
            session.sendMessage(new TextMessage(sequence));
        }
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes instanceof ServletRequestAttributes) {
            HttpServletRequest request = ((ServletRequestAttributes)requestAttributes).getRequest();
        }
        websocketService.addWebsocketSession(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        websocketService.removeWebsocketSession(session);
    }

}
