package br.com.rq.kafkamysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rq.kafkamysql.dao.PersonDaoImpl;
import br.com.rq.kafkamysql.domain.Person;
import br.com.rq.kafkamysql.kafka.producer.Producer;

@SpringBootApplication
public class KafkaMysqlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMysqlApplication.class, args);
	}

	@Autowired
	private PersonDaoImpl dao;
	
	@Autowired
	private Producer producer;

	@Override
	public void run(String... args) throws Exception {

		List<Person> list = dao.getAllPeople();
		for (Person p : list) {
			System.out.println(p);
			//dao.deletePerson(p.getId());
		}
		
		for (int i = 0; i < 10; i++) {
			producer.producerPerson("{\"name\": \"Person " + i + "\", \"age\": \"35\", \"email\": \"person" + i + "@gmail.com\"}");
		}
		
		System.out.println("Qtde pessoas: " + dao.getCountOfPeople());
		
		//dao.updatePerson(new Person("Nova Pessoa 12", 12, "nova-pessoa-12@gmail.com"), 12);
		
		//System.out.println("Selecionando pessoa com id = 12 > " + dao.getPerson(12));
		
		//System.out.println("Add em lote= " + dao.addLotePeople(list));
		
	}
	

}
