package com.example.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ExpenseDAO;
import com.example.demo.exceptions.ExpenseNotFoundException;
import com.example.demo.models.Expense;

@CrossOrigin
@RestController
public class MyController {
	@Autowired
	ExpenseDAO expenseDAO;
	@RequestMapping(method = RequestMethod.GET, path = "/")
	public String sayHello() {
		return "Hello Spring Boot 2.4.....";
	}
	
	@GetMapping(path="/hello")
	public String hello() {
		return "Hello Spring Boot";
	}
	
	@GetMapping(path="/hi")
	public EntityModel<Expense> hi() {
		//return "Hello Spring Boot HATEOAS";
		Expense expense = new Expense(1001,"AAA",43,"gdsgfdgfdg");
		EntityModel<Expense> entityModel = new EntityModel<>(expense);
		WebMvcLinkBuilder webMVCLinkBuilder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder
				.methodOn(this.getClass()).getAllExpenses());
		entityModel.add(webMVCLinkBuilder.withRel("all-expenses"));
		return entityModel;
		
		
	}
	
	@GetMapping(path = "/expense/{id}")
	public Expense getExpense(@PathVariable int id) {
		//Expense expense = new Expense(1001, "A",90,"AAAA");
		Expense expense =  expenseDAO.findById(id);
		if(expense==null) {
			throw new ExpenseNotFoundException(id+" Not Found ...");
		}
		return expense;
	}
	
	
	
	
	
	
	
	@GetMapping(path="/expenses")
	public Map<String,List<Expense>> getAllExpenses(){
		//return expenseDAO.getAllExpenses();
		Map<String, List<Expense>> map = new HashMap<>();
		 map.put("expenses", expenseDAO.getAllExpenses());
		 return map;
	}
	@PostMapping("/addexpense")
	public String addExpense(@RequestBody Expense expense) {
		return expenseDAO.add(expense)?"Record Added":"Not Added";
	}
	
	@DeleteMapping("/deleteexpense/{id}")
	public String deleteExpense(@PathVariable int id) {
		return expenseDAO.deleteById(id)?"Record Deleted":"Not Deleted";
	}
	
	
}
