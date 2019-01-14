package com.alejandrolaban.websocketpoc.configuration;

import com.alejandrolaban.websocketpoc.interceptor.HazelcastBrokerInterceptor;
import com.alejandrolaban.websocketpoc.interceptor.HazelcastInboundInterceptor;
import com.alejandrolaban.websocketpoc.interceptor.HazelcastOutboundInterceptor;
import com.alejandrolaban.websocketpoc.messaging.support.HazelcastQueueMessageChannel;
import com.alejandrolaban.websocketpoc.messaging.support.HazelcastTopicMessageChannel;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessagingTemplate;

@Configuration
public class InterceptorConfig {


    private final HazelcastInstance hazelcastInstance;

    public static final String HZ_QUEUE_MESSAGING_TEMPLATE = "hazelcastQueueSimpMessagingTemplate";
    public static final String HZ_TOPIC_MESSAGING_TEMPLATE = "hazelcastTopicSimpMessagingTemplate";


    public InterceptorConfig(@Qualifier(HazelcastConfig.HZ_CLIENT_BEAN) HazelcastInstance hazelcastInstance) {
        this.hazelcastInstance = hazelcastInstance;
    }

    @Bean
    HazelcastInboundInterceptor hazelcastInboundInterceptor() {
        return new HazelcastInboundInterceptor(hazelcastQueueSimpMessagingTemplate());
    }

    @Bean
    HazelcastOutboundInterceptor hazelcastOutboundInterceptor() {
        return new HazelcastOutboundInterceptor(hazelcastTopicSimpMessagingTemplate());
    }


    @Bean
    HazelcastBrokerInterceptor hazelcastBrokerInterceptor() {
        return new HazelcastBrokerInterceptor(hazelcastQueueSimpMessagingTemplate());
    }

    @Bean(HZ_QUEUE_MESSAGING_TEMPLATE)
    SimpMessagingTemplate hazelcastQueueSimpMessagingTemplate() {
        SimpMessagingTemplate simpMessagingTemplate = new SimpMessagingTemplate(hazelcastQueueMessageChannel());
        simpMessagingTemplate.setDefaultDestination("/queue/unresolved");
        return simpMessagingTemplate;
    }

    @Bean(HZ_TOPIC_MESSAGING_TEMPLATE)
    SimpMessagingTemplate hazelcastTopicSimpMessagingTemplate() {
        SimpMessagingTemplate simpMessagingTemplate = new SimpMessagingTemplate(hazelcastTopicMessageChannel());
        simpMessagingTemplate.setDefaultDestination("/topic/unresolved");
        return simpMessagingTemplate;
    }

    @Bean
    HazelcastQueueMessageChannel hazelcastQueueMessageChannel() {
        return new HazelcastQueueMessageChannel(hazelcastInstance);
    }

    @Bean
    HazelcastTopicMessageChannel hazelcastTopicMessageChannel() {
        return new HazelcastTopicMessageChannel(hazelcastInstance);
    }

}
