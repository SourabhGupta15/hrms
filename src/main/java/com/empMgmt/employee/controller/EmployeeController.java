package com.empMgmt.employee.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empMgmt.employee.entity.Employee;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

	List<Employee> employees = List.of(
			new Employee(101, "Akash", 20000, "IT"),
			new Employee(102, "Rohit", 45000, "Development"),
			new Employee(103, "Rushi", 50000, "Development"),
			new Employee(104, "Praful", 35000, "Development")
			);
	
	@GetMapping
	public List<Employee> getEmployees() {
		System.out.println("Inside first getEmployee");		//This line prints in console.
		return employees;
	}
	
	@GetMapping("/emp")
	public List<Employee> getEmployees1() {
		System.out.println("Inside second getEmployee");	//This line prints in console.
		return employees;
	}
	
	@GetMapping("/{id}")
	public Employee getEmployeesById(@PathVariable long id) {
		System.out.println("id : " + id);
//		for (Employee employee : employees) {
//			if(employee.getId() == id) {
//				return employee;
//			}
//		}
//		return new Employee();
		
//		Employee emp = null;
//		for (Employee employee : employees) {
//			if(employee.getId() == id) {
//				emp = employee;
//			}
//		}
//		return emp;
		
		//By using Java 8 and Stream API
		return employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(new Employee());		//You can also return null instead of new Employee(), but is better to return an object of employee without initializing.
	}
	
	@PostMapping
	public String addEmployee(@RequestBody Employee employee) {
		System.out.println("Input request : " + employee.toString());
//		employees.add(employee);		//Not working. It shows the list is immutable.
		List<Employee> emp = new ArrayList<>();
		emp.add(employee);
		System.out.println(emp.toString());
		return "Request coming to addEmployee";
	}
	
	@PostMapping("/emp")
	public List<Employee> addEmployee1(@RequestBody Employee employee) {
		System.out.println("Input request : " + employee.toString());
		List<Employee> emp = new ArrayList<>();
		emp.add(employee);
		return emp;
	}
}
