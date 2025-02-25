package com.empMgmt.employee.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empMgmt.employee.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	List<Employee> employees = List.of(new Employee(1, "Akash", 20000, "IT"),
			new Employee(2, "Rohit", 45000, "Development"));
	
	@RequestMapping
	public List<Employee> getEmployees() {
		System.out.println("Inside first getEmployee");		//This line prints in console.
		return employees;
	}
	
	@RequestMapping("/emp")
	public List<Employee> getEmployees1() {
		System.out.println("Inside second getEmployee");	//This line prints in console.
		return employees;
	}
}
