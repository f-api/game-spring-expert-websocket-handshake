package com.example.websocket.config;

import com.example.websocket.handler.WebSocketHandler;
import com.example.websocket.session.NicknameHandshakeInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
@RequiredArgsConstructor
public class WebSocketConfig implements WebSocketConfigurer {
    private final WebSocketHandler webSocketHandler;
    private final NicknameHandshakeInterceptor nicknameHandshakeInterceptor;

    @Override
    public void registerWebSocketHandlers(
            WebSocketHandlerRegistry registry
    ) {
        registry.addHandler(webSocketHandler, "/ws")
                .addInterceptors(nicknameHandshakeInterceptor);
    }
}
