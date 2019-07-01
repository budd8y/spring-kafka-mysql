package br.com.rq.kafkamysql.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import br.com.rq.kafkamysql.dao.PersonDaoImpl;
import br.com.rq.kafkamysql.domain.Person;

public class Consumer {
	
	private static final Logger log = LoggerFactory.getLogger(Consumer.class);
	private static int COUNTER_P1 = 1;
	private static int COUNTER_P2 = 1;
	
	
	@Autowired
	private PersonDaoImpl dao;
	
	/*
	@KafkaListener(topics = "${kafka.topic.person}", containerFactory = "personKafkaListenerContainerFactory", groupId = "teste1")
	public void consumerPerson(Person person) {
		log.info("[==1== ("+ COUNTER_P1 +")]consumerPerson='{}'", person.getName());
		//dao.addPerson(person);
		System.out.println("[==1== (" + COUNTER_P1 + ")]consumerPerson={" + person.getName() + "}");
		COUNTER_P1++;
	}
	*/
	
	
	@KafkaListener(topics = "${kafka.topic.person}", containerFactory = "personKafkaListenerContainerFactory", groupId = "teste2")
	public void consumerPerson2(Person person) {
		log.info("[==2== (" + COUNTER_P2 + ")]consumerPerson2='{}'", person.getName());
		System.out.println("[==2== (" + COUNTER_P2 + ")]consumerPerson={" + person.getName() + "}");
		COUNTER_P2++;
	}
	
}
