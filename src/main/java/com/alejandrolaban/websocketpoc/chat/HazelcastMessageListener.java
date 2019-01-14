package com.alejandrolaban.websocketpoc.chat;

import com.hazelcast.core.ItemEvent;
import com.hazelcast.core.ItemListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HazelcastMessageListener implements ItemListener {

    private final ChatService chatService;

    public HazelcastMessageListener(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void itemAdded(ItemEvent item) {
        chatService.start(item);
    }

    @Override
    public void itemRemoved(ItemEvent item) {

    }
}
