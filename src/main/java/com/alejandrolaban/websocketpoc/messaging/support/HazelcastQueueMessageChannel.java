package com.alejandrolaban.websocketpoc.messaging.support;

import com.alejandrolaban.websocketpoc.configuration.HazelcastConfig;
import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.support.AbstractMessageChannel;

@Slf4j
public class HazelcastQueueMessageChannel extends AbstractMessageChannel {

    private final HazelcastInstance hazelcastInstance;

    public HazelcastQueueMessageChannel(@Qualifier(HazelcastConfig.HZ_CLIENT_BEAN) HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Override
    protected boolean sendInternal(Message<?> message, long timeout) {
        String destination = SimpMessageHeaderAccessor.getDestination(message.getHeaders());

        if (destination == null) {
            return false;
        }
        log.info("getQueue::offer {} {}", destination, message);
        return hazelcastInstance.getQueue(destination).offer(message);
    }
}
