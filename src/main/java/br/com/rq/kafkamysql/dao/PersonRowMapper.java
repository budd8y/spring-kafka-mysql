package br.com.rq.kafkamysql.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import br.com.rq.kafkamysql.domain.Person;

public class PersonRowMapper implements RowMapper<Person> {

	@Override
	public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
		Person p = new Person();
		p.setId(rs.getInt("id"));
		p.setAge(rs.getInt("age"));
		p.setEmail(rs.getString("email"));
		p.setName(rs.getString("name"));
		
		return p;
	}


}
