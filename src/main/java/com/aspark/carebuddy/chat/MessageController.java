package com.aspark.carebuddy.chat;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @MessageMapping("/send")
    @SendTo("/topic/receive")
    public Message sendMessage(Message inputMessage) {

        System.out.println("Got a message " + inputMessage.content);

        //send FCM notification
        Message message = new Message();
        message.content = inputMessage.content;
        message.from = inputMessage.from;
        message.to = inputMessage.to;
        return message;
    }

}
