package br.com.rq.kafkamysql.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import br.com.rq.kafkamysql.domain.Person;

public class Consumer {
	
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);
	
	@KafkaListener(topics = "${kafka.topic.person}", containerFactory = "personKafkaListenerContainerFactory")
	public void consumerPerson(Person person) {
		log.info("consumerPerson='{}'", person);
		System.out.println("consumerPerson={" + person + "}");
	}
	
}
