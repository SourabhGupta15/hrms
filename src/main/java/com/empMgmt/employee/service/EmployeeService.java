package com.empMgmt.employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.empMgmt.employee.entity.Employee;
import com.empMgmt.employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

	//Field Injection
//	@Autowired
	private EmployeeRepository employeeRepository;

	//Setter Injection
	@Autowired
	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		System.out.println("EmployeeService : Setter Injection");
		this.employeeRepository = employeeRepository;
	}

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();	// SELECT * FROM employee
	}

	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = employeeRepository.findById(id);	// SELECT * FROM employee WHERE id =
		return employee.orElse(null);	// Optional class is used to handle null pointer exception so we use orElse(null) OR orElse(new Employee())
	}

	public String updateEmployee(Long id, Employee employee) {

//		Employee empFromDb = employeeRepository.findById(id).orElse(null);
		Employee empFromDb = getEmployeeById(id);
		
		if (empFromDb != null) {
			empFromDb.setDepartment(employee.getDepartment());
			empFromDb.setSalary(employee.getSalary());
			empFromDb.setName(employee.getName());
//			employeeRepository.save(empFromDb);
			saveEmployee(empFromDb);
			return "Employee updated successfully!";
		} else {
			return "Requested Employee is not present in the Database. Please use different ID.";
		}
	}

	public String deleteEmployee(Long id) {
		Employee empFromDb = getEmployeeById(id);
		if (empFromDb != null) {
			employeeRepository.deleteById(id);
			return "Employee deleted successfully!";
		}
		return "Requested Employee is not present in the Database. Please use different ID.";
	}

}
