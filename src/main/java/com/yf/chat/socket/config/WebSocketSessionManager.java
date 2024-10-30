package com.yf.chat.socket.config;

import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketSessionManager {

    private final Set<String> sessions = ConcurrentHashMap.newKeySet();

    public void addSession(String sessionId) {
        sessions.add(sessionId);
    }

    public void removeSession(String sessionId) {
        sessions.remove(sessionId);
    }

    public int getOnlineUserCount() {
        return sessions.size();
    }
}
