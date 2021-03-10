package com.example.demo.models;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class ExpenseRowMapper implements RowMapper<Expense> {

	@Override
	public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Expense expense = new Expense();
		expense.setId(rs.getInt("id"));
		expense.setName(rs.getString("name"));
		expense.setCost(rs.getDouble("cost"));
		expense.setDesc(rs.getString("descr"));
		return expense;
	}

}