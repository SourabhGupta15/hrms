package com.empMgmt.employee.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;	// Long is a wrapper class so its default value is null
	
	private String name;
	private long salary;	// long is a primitive integer data type so its default value is 0
	private String department;
	
	public Employee() {}
	
	public Employee(Long id, String name, long salary, String department) {
		this.id = id;
		this.name = name;
		this.salary = salary;
		this.department = department;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public long getSalary() {
		return salary;
	}
	
	public void setSalary(long salary) {
		this.salary = salary;
	}
	
	public String getDepartment() {
		return department;
	}
	
	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"id = " + id +
				", name = '" + name + "'" +
				", salary = " + salary +
				", department = '" + department + "'" +
				"}";
	}
}
