package br.com.rq.kafkamysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.rq.kafkamysql.dao.PersonDaoImpl;
import br.com.rq.kafkamysql.domain.Person;

@SpringBootApplication
public class KafkaMysqlApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(KafkaMysqlApplication.class, args);
	}

	@Autowired
	private PersonDaoImpl dao;

	@Override
	public void run(String... args) throws Exception {

		List<Person> list = dao.getAllPeople();
		for (Person p : list) {
			System.out.println(p);
			//dao.deletePerson(p.getId());
		}
		
		
		
		System.out.println("Qtde pessoas: " + dao.getCountOfPeople());
		
		//dao.updatePerson(new Person("Nova Pessoa 12", 12, "nova-pessoa-12@gmail.com"), 12);
		
		//System.out.println("Selecionando pessoa com id = 12 > " + dao.getPerson(12));
		
		//System.out.println("Add em lote= " + dao.addLotePeople(list));
		
	}

}
