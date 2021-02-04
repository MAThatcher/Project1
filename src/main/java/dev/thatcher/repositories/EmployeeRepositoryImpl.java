package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.Employee;
import dev.thatcher.util.JDBCconnection;

public class EmployeeRepositoryImpl implements EmployeeRepository {

	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public Employee createEmployee(Employee e) {
		try {
			String sql = "INSERT INTO employees(emp_fname,emp_lname,emp_email,emp_password,emp_supervisor,emp_dep_head,emp_benco,emp_funds) VALUES(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int depHead = 0;
			int benco = 0;
			if (e.isDepHead()) {
				depHead = 1;
			}
			if (e.isBenco()) {
				benco = 1;
			}
			ps.setString(1, e.getFname());
			ps.setString(2, e.getLname());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPassword());
			ps.setString(5, String.valueOf(e.getSupId()));
			ps.setString(6, String.valueOf(depHead));
			ps.setString(7, String.valueOf(benco));
			ps.setString(8, String.valueOf(e.getFunds()));
			ps.executeQuery();
			
			// now update our event with the proper id
			sql = "SELECT * FROM employees ORDER BY emp_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt("emp_id"));
			}
			return e;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeById(int id) {
		try {
			String sql = "SELECT * FROM employees WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee newEmp = new Employee();
				newEmp.setId(rs.getInt("emp_id"));
				newEmp.setFname(rs.getString("emp_fname"));
				newEmp.setLname(rs.getString("emp_lname"));
				newEmp.setEmail(rs.getString("emp_email"));
				newEmp.setPassword(rs.getString("emp_password"));
				newEmp.setSupId(rs.getInt("emp_supervisor"));
				newEmp.setDepHead(rs.getBoolean("emp_dep_head"));
				newEmp.setBenco(rs.getBoolean("emp_benco"));
				newEmp.setFunds(rs.getDouble("emp_funds"));
				return newEmp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Employee getEmployeeByEmail(String email) {
		try {
			String sql = "SELECT * FROM employees WHERE emp_email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Employee newEmp = new Employee();
				newEmp.setId(rs.getInt("emp_id"));
				newEmp.setFname(rs.getString("emp_fname"));
				newEmp.setLname(rs.getString("emp_lname"));
				newEmp.setEmail(rs.getString("emp_email"));
				newEmp.setPassword(rs.getString("emp_password"));
				newEmp.setSupId(rs.getInt("emp_supervisor"));
				newEmp.setDepHead(rs.getBoolean("emp_dep_head"));
				newEmp.setBenco(rs.getBoolean("emp_benco"));
				newEmp.setFunds(rs.getDouble("emp_funds"));
				return newEmp;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Employee> getAllEmployees() {
		try {
			String sql = "SELECT * FROM employees";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Employee> output = new ArrayList<Employee>();
			while (rs.next()) {
				Employee newEmp = new Employee();
				newEmp.setId(rs.getInt("emp_id"));
				newEmp.setFname(rs.getString("emp_fname"));
				newEmp.setLname(rs.getString("emp_lname"));
				newEmp.setEmail(rs.getString("emp_email"));
				newEmp.setPassword(rs.getString("emp_password"));
				newEmp.setSupId(rs.getInt("emp_supervisor"));
				newEmp.setDepHead(rs.getBoolean("emp_dep_head"));
				newEmp.setBenco(rs.getBoolean("emp_benco"));
				newEmp.setFunds(rs.getDouble("emp_funds"));
				output.add(newEmp);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	@Override
	public boolean updateEmployee(Employee e) {
		try {
			String sql = "UPDATE employees SET emp_fname = ?, emp_lname = ?, emp_email = ?, emp_password = ?, emp_supervisor = ?, emp_dep_head = ?, emp_benco = ?, emp_funds = ? WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int depHead = 0;
			int benco = 0;
			if (e.isDepHead()) {
				depHead = 1;
			}
			if(e.isBenco()) {
				benco = 1;
			}
				
			ps.setString(1, e.getFname());
			ps.setString(2, e.getLname());
			ps.setString(3, e.getEmail());
			ps.setString(4, e.getPassword());
			ps.setString(5, String.valueOf(e.getSupId()));
			ps.setString(6, String.valueOf(depHead));
			ps.setString(7, String.valueOf(benco));
			ps.setString(8, String.valueOf(e.getFunds()));
			ps.setString(9, String.valueOf(e.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEmployee(Employee e) {
		try {
			String sql = "DELETE FROM employees WHERE emp_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(e.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

}
