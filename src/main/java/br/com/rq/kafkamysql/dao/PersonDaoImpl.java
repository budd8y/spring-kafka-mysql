package br.com.rq.kafkamysql.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import br.com.rq.kafkamysql.domain.Person;
import br.com.rq.kafkamysql.mapper.PersonRowMapper;

@Repository
public class PersonDaoImpl implements IPersonDao {
 
	@Autowired
	private JdbcTemplate jdbcTemplate;
		
	@Override
	public List<Person> getAllPeople() {
		final String query = "SELECT * FROM people";
		return jdbcTemplate.query(query, new PersonRowMapper());
		
	}

	@Override
	public void addPerson(Person p) {
		System.out.println(p);
		System.out.println(jdbcTemplate);
		jdbcTemplate.update("INSERT INTO people(name, age, email) VALUES(?, ?, ?)", p.getName(), p.getAge(), p.getEmail());
	}

	@Override
	public int getCountOfPeople() {
		return jdbcTemplate.queryForObject("SELECT count(1) FROM people", Integer.TYPE);
	}

	@Override
	public Person getPerson(int id) {
		final String query = "SELECT * FROM people WHERE id = ?";
		return jdbcTemplate.queryForObject(query, new Object[] {id}, new PersonRowMapper());
	}

	@Override
	public void updatePerson(Person p, int id) {
		final String query = "UPDATE people SET name = ?, age = ?, email = ? WHERE id = ?";
		jdbcTemplate.update(query, p.getName(), p.getAge(), p.getEmail(), id);		
	}

	@Override
	public int[] addLotePeople(List<Person> p) {
		final String query = "INSERT INTO people(name, age, email) VALUES(?, ?, ?)";
		
		return jdbcTemplate.batchUpdate(query, new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setString(1, p.get(i).getName());
				ps.setInt(2, p.get(i).getAge());
				ps.setString(3, p.get(i).getEmail());
			}
			
			@Override
			public int getBatchSize() {
				// TODO Auto-generated method stub
				return p.size();
			}
		});
		
	}

	@Override
	public void deletePerson(int id) {
		final String query = "DELETE FROM people WHERE id = ?";
		jdbcTemplate.update(query, id);		
	}

	
}
