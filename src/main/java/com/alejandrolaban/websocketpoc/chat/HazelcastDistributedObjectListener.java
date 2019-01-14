package com.alejandrolaban.websocketpoc.chat;

import com.hazelcast.core.DistributedObjectEvent;
import com.hazelcast.core.DistributedObjectListener;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HazelcastDistributedObjectListener implements DistributedObjectListener {

    private final ChatService chatService;

    public HazelcastDistributedObjectListener(ChatService chatService) {
        this.chatService = chatService;
    }

    @Override
    public void distributedObjectCreated(DistributedObjectEvent event) {
        chatService.start(event);
    }

    @Override
    public void distributedObjectDestroyed(DistributedObjectEvent event) {

    }
}
