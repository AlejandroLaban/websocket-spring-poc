package com.alejandrolaban.websocketpoc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class EchoController {

    private SimpMessagingTemplate template;

    @Autowired
    public EchoController(@Qualifier("brokerMessagingTemplate") SimpMessagingTemplate template) {
        this.template = template;
    }

    @RequestMapping(path = "/echo/{message}", method = GET)
    public Object greet(@PathVariable("message") String message) {
        message = "message echo" + message;
        this.template.convertAndSend("/topic/echo", message);
        return message;
    }
}
