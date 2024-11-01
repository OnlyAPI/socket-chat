package com.yf.chat.socket.controller;

import com.yf.chat.socket.config.WebSocketSessionManager;
import com.yf.chat.socket.model.ChatMessage;
import com.yf.chat.socket.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Slf4j
@Controller
public class ChatController {

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;


    @MessageMapping("/send")// 接收前端发送的消息
    @SendTo("/topic/messages")// 广播到订阅了"/topic/messages"的所有客户端
    public ChatMessage sendMessage(ChatMessage chatMessage) {
        log.info("[sendMessage] [{}] send message: [{}]", chatMessage.getUsername(), chatMessage.getContent());
        // 这里可以对消息进行处理，比如保存到数据库等
        return chatMessage;
    }


    @GetMapping("/online")
    @ResponseBody
    public Result<Integer> getOnline() {
        return Result.ok(webSocketSessionManager.getOnlineUserCount());
    }


}
