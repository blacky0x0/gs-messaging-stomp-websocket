package hello;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * https://github.com/despird-zh/gp.web/blob/10e8fc98fe7c5637cf57e60ef3b49797ff4dd36c/src/main/java/com/gp/config/WebSocketConfigurer.java
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(messageWebSocketHandler(), "/ws").setAllowedOrigins("*").withSockJS();
    }

    @Bean
    public WebSocketHandler messageWebSocketHandler() {
        return new MessageHandler();
    }

}