package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
 
import java.util.HashMap;
import java.util.Map;
 
@EnableKafka
@Configuration
public class KafkaConsumerConfig {
 
    @Value("${spring.kafka.consumer.bootstrap.servers}") String bootstrapServers;
    @Value("${spring.kafka.consumer.client.id}") String clientId;
    @Value("${spring.kafka.consumer.group.id}") String groupId;
 
    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> configProps = new HashMap<String, Object>();
        configProps.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ConsumerConfig.CLIENT_ID_CONFIG,
                clientId);
        configProps.put(
                ConsumerConfig.GROUP_ID_CONFIG,
                groupId);
        configProps.put(
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        configProps.put(
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
                StringDeserializer.class);
        configProps.put(
                ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
                "latest");
        configProps.put(
                "security.protocol",
                "SASL_SSL");
        configProps.put(
                "sasl.mechanism",
                "PLAIN");
        configProps.put(
                "ssl.protocol",
                "TLSv1.2");
        configProps.put(
                "ssl.enabled.protocols",
                "TLSv1.2");
        configProps.put(
                "ssl.endpoint.identification.algorithm",
                "HTTPS");
        
        String saslJaasConfig =
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"USERNAME\" password=\"PASSWORD\";";

              String user = "JhzI79pqpumXFeyW";
              String password= "DGnwhbbUSewPiQpwRwU5aLDUxkEWrNa2";
              saslJaasConfig = saslJaasConfig.replace("USERNAME", user).replace("PASSWORD", password);

        configProps.put("sasl.jaas.config", saslJaasConfig);
 
        return new DefaultKafkaConsumerFactory<String, String>(configProps);
    }
 
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<String, String>();
        factory.setConsumerFactory(consumerFactory());
 
        return factory;
    }
}