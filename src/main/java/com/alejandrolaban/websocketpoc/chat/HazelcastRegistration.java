package com.alejandrolaban.websocketpoc.chat;

import com.alejandrolaban.websocketpoc.configuration.HazelcastConfig;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.LifecycleEvent;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HazelcastRegistration {

    private final HazelcastInstance hazelcastInstance;
    private final ChatService chatService;

    public HazelcastRegistration(@Qualifier(HazelcastConfig.HZ_CLIENT_BEAN) HazelcastInstance hazelcastInstance, ChatService chatService) {
        this.hazelcastInstance = hazelcastInstance;
        this.chatService = chatService;
    }

    @PostConstruct
    void setListener() {
        hazelcastInstance.getLifecycleService().addLifecycleListener(event -> {
            if (LifecycleEvent.LifecycleState.CLIENT_CONNECTED.equals(event.getState())) {
                hazelcastInstance.getQueue("*topic*").addItemListener(new HazelcastMessageListener(chatService), false);
                hazelcastInstance.addDistributedObjectListener(new HazelcastDistributedObjectListener(chatService));
            }
        });
    }


}
