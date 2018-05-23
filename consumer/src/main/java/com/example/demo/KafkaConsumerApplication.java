package com.example.demo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
public class KafkaConsumerApplication {

	public static Logger logger = LoggerFactory.getLogger(KafkaConsumerApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(KafkaConsumerApplication.class, args);
	}
	@KafkaListener(topics = "rate-update")
    public void listen(ConsumerRecord<?, ?> cr) throws Exception {
        logger.info(cr.toString());
    }
}
