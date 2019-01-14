package com.alejandrolaban.websocketpoc.interceptor;

import com.alejandrolaban.websocketpoc.configuration.InterceptorConfig;
import com.hazelcast.core.ITopic;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.support.ExecutorChannelInterceptor;

@Slf4j
public class HazelcastInboundInterceptor implements ExecutorChannelInterceptor {

    private final SimpMessagingTemplate simpMessagingTemplate;

    public HazelcastInboundInterceptor(@Qualifier(InterceptorConfig.HZ_QUEUE_MESSAGING_TEMPLATE) SimpMessagingTemplate simpMessagingTemplate) {
        this.simpMessagingTemplate = simpMessagingTemplate;
    }


    @Override
    public void postSend(Message<?> message, MessageChannel channel, boolean sent) {
        simpMessagingTemplate.send(message);
    }

    @Override
    public Message<?> postReceive(Message<?> message, MessageChannel channel) {
        return message;
    }

    @Override
    public void afterReceiveCompletion(Message<?> message, MessageChannel channel, Exception ex) {
        log.info("HazelcastInboundInterceptor::afterReceiveCompletion");
        //simpMessagingTemplate.send(message);
    }

    private void addListener(ITopic<Message> iTopic) {
        iTopic.addMessageListener(message -> {
            log.info("iTopic::MessageListener::HazelcastInboundInterceptor");
        });
    }

}
