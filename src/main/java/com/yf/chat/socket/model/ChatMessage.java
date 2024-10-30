package com.yf.chat.socket.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ChatMessage {

    // Getters and Setters
    private String content;
    private String username;


    public String msgToClient() {
        return String.join(":", this.username, this.content);
    }
}
