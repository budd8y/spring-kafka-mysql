package br.com.rq.kafkamysql.dao;

import java.util.List;

import br.com.rq.kafkamysql.domain.Person;

public interface IPersonDao {

	void addPerson(Person p);
	int[] addLotePeople(List<Person> p);
	List<Person> getAllPeople();
	int getCountOfPeople();
	Person getPerson(int id);
	void updatePerson(Person p, int id);
	void deletePerson(int id);
}
