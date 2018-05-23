package com.example.demo;

import java.util.Properties;
import java.util.concurrent.Future;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaTestController {
	
	@Autowired
    private KafkaTemplate<String, String> template;
	
	@RequestMapping("/kafkaTest/{message}")
	 public @ResponseBody ResponseEntity kafkaTest(@PathVariable String message){
		String topic = "rate-update";
		template.send(topic, message);
		 
        return new ResponseEntity("Sent: " + message, HttpStatus.OK);
		
		/*String topic = "rate-update";
		KafkaProducer< String, String> kafkaProducer = new KafkaProducer<String, String>(getKafkaProducerProperties());
        
        System.out.println("after Kafka producer is generated ");
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(
        		topic,"key",message);
        System.out.println("Record: " + record.toString());
        // Send record asynchronously
        Future<RecordMetadata> future = kafkaProducer.send(record);
        System.out.println("Future: " + future.toString());
        
		return "Hello -----"+message;*/
	}
	
	//getProperties
	/*public Properties getKafkaProducerProperties()  {

    	String bootstrapServers = "kafka01-prod01.messagehub.services.us-south.bluemix.net:9093,"+
				"kafka02-prod01.messagehub.services.us-south.bluemix.net:9093,"
				+"kafka03-prod01.messagehub.services.us-south.bluemix.net:9093,"
				+"kafka04-prod01.messagehub.services.us-south.bluemix.net:9093,"
				+"kafka05-prod01.messagehub.services.us-south.bluemix.net:9093";
    	
        Properties props = new Properties();
        props.put("bootstrap.servers", bootstrapServers);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("client.id", "lab-4-sentence");
        props.put("security.protocol", "SASL_SSL");
        props.put("acks", "1");
        props.put("sasl.mechanism", "PLAIN");
        props.put("ssl.protocol", "TLSv1.2");
        props.put("ssl.enabled.protocols", "TLSv1.2");
        props.put("ssl.endpoint.identification.algorithm", "HTTPS");

        String saslJaasConfig =
          "org.apache.kafka.common.security.plain.PlainLoginModule required username=\"USERNAME\" password=\"PASSWORD\";";

        String user = "JhzI79pqpumXFeyW";
        String password= "DGnwhbbUSewPiQpwRwU5aLDUxkEWrNa2";
        saslJaasConfig = saslJaasConfig.replace("USERNAME", user).replace("PASSWORD", password);

        props.put("sasl.jaas.config", saslJaasConfig);

        return props;
      }*/

}
