package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
 
    @Value("${spring.kafka.producer.bootstrap.servers}") String bootstrapServers;
    @Value("${spring.kafka.producer.client.id}") String clientId;
 
    @Bean
    public ProducerFactory<String, String> producerFactory() {
        Map<String, Object> configProps = new HashMap<String, Object>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServers);
        configProps.put(
                ProducerConfig.CLIENT_ID_CONFIG,
                clientId);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
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
        /*String saslJaasConfig =
                "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"USERNAME\" password=\"PASSWORD\";";

              String user = "JhzI79pqpumXFeyW";
              String password= "DGnwhbbUSewPiQpwRwU5aLDUxkEWrNa2";
              saslJaasConfig = saslJaasConfig.replace("USERNAME", user).replace("PASSWORD", password);

        configProps.put("sasl.jaas.config", saslJaasConfig);*/
        
        return new DefaultKafkaProducerFactory<String, String>(configProps);
    }
 
    @Bean
    public KafkaTemplate<String, String > kafkaTemplate() {
        return new KafkaTemplate<String, String>(producerFactory());
    }
}
