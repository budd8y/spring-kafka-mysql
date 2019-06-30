package br.com.rq.kafkamysql.kafka.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer {

	private static final Logger log = LoggerFactory.getLogger(Producer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	@Value("${kafka.topic.person}")
	private String topicPerson;
	
	public void producerPerson(String msg) {
		log.info("producerPerson='{}'", msg);
		System.out.println("producerPerson={" + msg + "}");
		kafkaTemplate.send(topicPerson, msg);
	}
	
}
