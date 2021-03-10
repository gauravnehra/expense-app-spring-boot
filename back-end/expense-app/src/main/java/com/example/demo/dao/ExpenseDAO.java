package com.example.demo.dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Expense;
import com.example.demo.models.ExpenseRowMapper;

@Repository
public class ExpenseDAO {
	@Autowired
	JdbcTemplate jdbcTemplate;
	public boolean add(Expense expense) {
		String SQL = "insert into expense (id, name, cost, descr) values(?,?,?,?)";
		int result = jdbcTemplate.update(SQL,expense.getId(), expense.getName(), expense.getCost(), expense.getDesc());
		return result>0;
		//return expenses.add(expense);
	}
	
	public boolean deleteById(int id) {
		String SQL = "delete from expense where id=?";
		int result = jdbcTemplate.update(SQL,new Object[]{id});
		return result>0;
	}
	
	
	public boolean update(int id) {
		String SQL = "update expense set cost=?  where id=?";
		int result = jdbcTemplate.update(SQL,new Object[]{9999, id});
		return result>0;
	}
	public List<Expense> getAllExpenses(){
		String SQL = "select * from expense";
		List<Expense> expenses  = jdbcTemplate.query(SQL, new ExpenseRowMapper());
		return expenses;
	}
	
	public Expense findById(int id) {
		String SQL = "select id, name, cost, descr from expense where id=?";
		List<Expense> expenses  =jdbcTemplate.query(SQL,new Object[] {id},new ExpenseRowMapper());
		return expenses!=null && expenses.size()>0? expenses.get(0):null;
	}
	
	
	/*List<Expense> expenses  = new ArrayList<>();
	public List<Expense> getAllExpenses(){
		//expenses = Arrays.asList(new Expense(1001, "Abc", 10, "ABCD"),new Expense(1002, "Xyz", 10, "Xyzed"));
		return expenses;

	
	}
	
	public boolean deleteById(int id) {
		int index = expenses.indexOf(new Expense(id));
		if(index==-1) {
			return false;
		}
		else {
		expenses.remove(index);
		return true;
		}
	}
	
	public boolean add(Expense expense) {
		return expenses.add(expense);
	}
	
	public Expense findById(int id) {
		int index = expenses.indexOf(new Expense(id));
		System.out.println("Index is "+index);
		return index==-1?null:expenses.get(index);
//		var expense = new Expense();
//		expense.setId(id);
//		int index = expenses.indexOf(expense);
//		if(index!=-1) {
//			return expenses.get(index);
//		}
//		return null;
	}*/
}