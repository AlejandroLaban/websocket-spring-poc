package com.alejandrolaban.websocketpoc.configuration;

import com.alejandrolaban.websocketpoc.interceptor.HazelcastBrokerInterceptor;
import com.alejandrolaban.websocketpoc.interceptor.HazelcastInboundInterceptor;
import com.alejandrolaban.websocketpoc.interceptor.HazelcastOutboundInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@EnableWebSocketMessageBroker
@Configuration
public class WebsocketConfig implements WebSocketMessageBrokerConfigurer {

    private final HazelcastBrokerInterceptor hazelcastBrokerInterceptor;
    private final HazelcastInboundInterceptor hazelcastInboundInterceptor;
    private final HazelcastOutboundInterceptor hazelcastOutboundInterceptor;

    public WebsocketConfig(HazelcastBrokerInterceptor hazelcastBrokerInterceptor, HazelcastInboundInterceptor hazelcastInboundInterceptor, HazelcastOutboundInterceptor hazelcastOutboundInterceptor) {
        this.hazelcastBrokerInterceptor = hazelcastBrokerInterceptor;
        this.hazelcastInboundInterceptor = hazelcastInboundInterceptor;
        this.hazelcastOutboundInterceptor = hazelcastOutboundInterceptor;
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic", "/queue");
        config.setApplicationDestinationPrefixes("/app");
        config.setUserDestinationPrefix("/user");
        config.configureBrokerChannel().interceptors(hazelcastBrokerInterceptor);
    }


    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws")
                .setAllowedOrigins("*")
                .withSockJS();
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(hazelcastInboundInterceptor);
    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration registration) {
        registration.interceptors(hazelcastOutboundInterceptor);
    }
}
