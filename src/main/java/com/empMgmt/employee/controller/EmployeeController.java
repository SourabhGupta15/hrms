package com.empMgmt.employee.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empMgmt.employee.entity.Employee;
import com.empMgmt.employee.service.EmployeeService;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "http://localhost:4200/")
public class EmployeeController {

	private String abc;

//	List<Employee> employees = List.of(
//			new Employee(101, "Akash", 20000, "IT"),
//			new Employee(102, "Rohit", 45000, "Development"),
//			new Employee(103, "Rushi", 50000, "Development"),
//			new Employee(104, "Praful", 35000, "Development")
//			);
	
//	List<Employee> employees = new ArrayList<Employee>();
	
	//Field Injection
//	@Autowired
	private EmployeeService employeeServices;
	
	//Constructor Injection
	public EmployeeController(EmployeeService employeeServices) {
	System.out.println("EmployeeController : Constructor Injection");	//Prints below in console
	this.employeeServices = employeeServices;
	}
	
	@PostConstruct
	public void init() {
		abc = "Vishal";
		System.out.println("EmployeeController has been initialized.");
	}

	@PostMapping
	public ResponseEntity<Map<String, String>> addEmployee(@RequestBody Employee employee) {
		System.out.println("Input request : " + employee.toString());
//		employees.add(employee);
		Employee savedEmployee = employeeServices.saveEmployee(employee);
		String resp = "Employee inserted successfully with id : " + savedEmployee.getId();
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", resp);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
//		System.out.println("Inside first getEmployee");		//This line prints in console.
//		return employees;
		System.out.println("abc : " + abc);
		List<Employee> empList = employeeServices.getEmployees();
		if (empList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(empList, HttpStatus.OK);
	}
	
//	@GetMapping("/emp")
//	public List<Employee> getEmployees1() {
//		System.out.println("Inside second getEmployee");	//This line prints in console.
//		return employees;
//	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeesById(@PathVariable long id) {
//		System.out.println("id : " + id);
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
//		return employees.stream().filter(emp -> emp.getId() == id).findFirst().orElse(new Employee());		//You can also return null instead of new Employee(), but is better to return an object of employee without initializing.
		
		Employee savedEmployee = employeeServices.getEmployeeById(id);
		if (savedEmployee != null) {
			return new ResponseEntity<>(savedEmployee, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Map<String, String>> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
		String updateResponse = employeeServices.updateEmployee(id, employee);
		Map<String, String> response = new HashMap<String, String>();
		response.put("message", updateResponse);
		if (updateResponse.contains("updated")) {
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, String>> deleteEmployee(@PathVariable Long id) {
		String response = employeeServices.deleteEmployee(id);
		Map<String, String> resp = new HashMap<String, String>();
		resp.put("message", response);
		if (response.contains("successfully")) {
			return new ResponseEntity<>(resp, HttpStatus.OK);
		}
		return new ResponseEntity<>(resp, HttpStatus.NOT_FOUND);
		
//		try {
//			employeeServices.deleteEmployee(id);
//			return new ResponseEntity<String>(HttpStatus.OK);
//		} catch (Exception e) {
//			return new ResponseEntity<String>("Employee not found or unable to delete.", HttpStatus.NOT_FOUND);
//		}
	}
	
//	@PostMapping("/emp")
//	public List<Employee> addEmployee1(@RequestBody Employee employee) {
//		System.out.println("Input request : " + employee.toString());
//		//List.of() creates immutable list. While creating employees use new ArrayList<>() instead of List.of().
//		List<Employee> emp = new ArrayList<>();
//		emp.add(employee);
//		return emp;
//	}
}
