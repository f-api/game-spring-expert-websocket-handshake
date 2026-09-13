package com.example.websocket.session;

import java.util.Map;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

@Component
public class NicknameHandshakeInterceptor implements HandshakeInterceptor {
    public static final String ATTR_NICKNAME = "nickname";
    private static final Pattern NICKNAME = Pattern.compile("^[A-Za-z]{2,12}$");

    @Override
    public boolean beforeHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler handler,
            Map<String, Object> attributes
    ) {
        String query = request.getURI().getQuery();
        String nickname = (query != null && query.startsWith("nickname="))
                ? query.substring("nickname=".length()) : null;

        if (nickname == null || !NICKNAME.matcher(nickname).matches()) {
            response.setStatusCode(HttpStatus.BAD_REQUEST);
            return false;
        }

        attributes.put(ATTR_NICKNAME, nickname);
        return true;
    }

    @Override
    public void afterHandshake(
            ServerHttpRequest request,
            ServerHttpResponse response,
            WebSocketHandler handler,
            Exception exception
    ) {
    }
}
