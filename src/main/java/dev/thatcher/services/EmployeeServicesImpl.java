package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.Employee;
import dev.thatcher.repositories.EmployeeRepository;
import dev.thatcher.repositories.EmployeeRepositoryImpl;

public class EmployeeServicesImpl implements EmployeeServices {

	public EmployeeRepository er = new EmployeeRepositoryImpl();
	
	@Override
	public Employee createEmployee(Employee e) {
		return er.createEmployee(e);
	}
	@Override
	public Employee getEmployeeById(int id) {
		return er.getEmployeeById(id);
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		return er.getEmployeeByEmail(email);
	}
	@Override
	public List<Employee> getAllEmployees() {
		return er.getAllEmployees();
	}
	@Override
	public boolean updateEmployee(Employee e) {
		return er.updateEmployee(e);
	}
	@Override
	public boolean deleteEmployee(Employee e) {
		return er.deleteEmployee(e);
	}
	@Override
	public Employee login(String email, String password) {
		Employee e = er.getEmployeeByEmail(email);
		if (e!=null && e.getPassword().equals(password)) {
			return e;
		}
		return null;
	}

	

}
