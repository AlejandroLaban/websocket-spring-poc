package com.alejandrolaban.websocketpoc.configuration;

import com.alejandrolaban.websocketpoc.chat.HazelcastMessageListener;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.XmlClientConfigBuilder;
import com.hazelcast.config.Config;
import com.hazelcast.config.XmlConfigBuilder;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.io.InputStream;

@Configuration
public class HazelcastConfig {

    public static final String HZ_SERVER_BEAN = "HZ_SERVER_BEAN";
    public static final String HZ_CLIENT_BEAN = "HZ_CLIENT_BEAN";

    @Bean(name = HZ_SERVER_BEAN)
    HazelcastInstance hazelcastInstance() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hazelcast.xml");
        Config config = new XmlConfigBuilder(inputStream).build();
        return Hazelcast.newHazelcastInstance(config);
    }

    @Bean(name = HZ_CLIENT_BEAN)
    HazelcastInstance hazelcastClientInstance() {
        InputStream inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("hazelcast-client.xml");
        ClientConfig config = new XmlClientConfigBuilder(inputStream).build();
        return HazelcastClient.newHazelcastClient(config);
    }
}

