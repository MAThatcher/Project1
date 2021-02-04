package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Employee;

public interface EmployeeServices {
	public Employee createEmployee(Employee e);
	
	public Employee getEmployeeById(int id);

	public Employee getEmployeeByEmail(String email);

	public List<Employee> getAllEmployees();
	
	public boolean updateEmployee(Employee e);
	
	public boolean deleteEmployee(Employee e);
	
	public Employee login(String email,String password);
}
