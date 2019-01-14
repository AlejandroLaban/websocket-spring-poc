package com.alejandrolaban.websocketpoc.chat;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ChatServiceImpl implements ChatService {

    @Override
    public Object start(Object o) {
        return getMock();
    }

    @Override
    public Object setTypingState(Object o) {
        return getMock();
    }

    @Override
    public Object sendMessage(Object o) {
        return getMock();
    }

    @Override
    public Object history(Object o) {
        return getMock();
    }

    @Override
    public Object pool(Object o) {
        return getMock();
    }

    private Object getMock() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Map<String, String> stringMap = new HashMap<String, String>() {{
            put("event", "pool");
        }};
        return stringMap;
    }

}
