package com.wmz.bus;

import java.util.Date;
import java.util.logging.Logger;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sender {
 
    @Autowired
    private AmqpTemplate amqpTemplate;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
 
    public void send() {
        String context = "hello " + new Date();
        this.amqpTemplate.convertAndSend("hello", context);
    }
}
