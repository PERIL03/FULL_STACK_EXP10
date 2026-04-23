package com.AML3B.Demo_Websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.AML3B.Demo_Websocket.model.Message;

@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(Message message) {
        System.out.println(message.getSender() + " : " + message.getContent());
        return message;
    }
}
