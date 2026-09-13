package com.example.websocket.handler;

import com.example.websocket.session.NicknameHandshakeInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    @Override
    public void afterConnectionEstablished(
            WebSocketSession session
    ) throws Exception {
        String nickname = (String) session.getAttributes()
                .get(NicknameHandshakeInterceptor.ATTR_NICKNAME);
        TextMessage response = new TextMessage(nickname);
        session.sendMessage(response);
    }
}
