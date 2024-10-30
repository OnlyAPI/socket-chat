package com.yf.chat.socket.config;

import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Component;

@Component
public class StompChannelInterceptor implements ChannelInterceptor {

    private final WebSocketSessionManager sessionManager;

    public StompChannelInterceptor(WebSocketSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);

        if (StompCommand.CONNECT.equals(accessor.getCommand())) {
            // 连接建立
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            sessionManager.addSession(sessionId);
        } else if (StompCommand.DISCONNECT.equals(accessor.getCommand())) {
            // 连接关闭
            String sessionId = (String) message.getHeaders().get("simpSessionId");
            sessionManager.removeSession(sessionId);
        }
        return message;
    }
}
