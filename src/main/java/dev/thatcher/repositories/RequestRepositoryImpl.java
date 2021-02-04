package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.Request;
import dev.thatcher.util.JDBCconnection;

public class RequestRepositoryImpl implements RequestRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public Request createRequest(Request r) {
		try {
			String sql = "INSERT INTO requests(request_emp_id,request_event_id,request_amount,request_justification,request_time_missed,request_attatched_approval) VALUES(?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(r.getEmpId()));
			ps.setString(2, String.valueOf(r.getEventId()));
			ps.setString(3, String.valueOf(r.getAmount()));
			ps.setString(4, r.getJustfication());
			ps.setString(5, String.valueOf(r.getTimeMissed()));
			ps.setString(6, r.getAttachedApproval());
			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM requests ORDER BY request_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				r.setId(rs.getInt("request_id"));
			}
			return r;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Request getRequestById(int id) {
		try {
			String sql = "SELECT * FROM requests WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Request r = new Request();
				r.setId(rs.getInt("request_id"));
				r.setEmpId(rs.getInt("request_emp_id"));
				r.setEventId(rs.getInt("request_event_id"));
				r.setDate(rs.getString("request_date"));
				r.setAmount(rs.getDouble("request_amount"));
				r.setJustfication(rs.getString("request_justification"));
				r.setTimeMissed(rs.getInt("request_time_missed"));
				r.setAttachedApproval(rs.getString("request_attatched_approval"));
				return r;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Request> getAllRequests() {
		try {
			String sql = "SELECT * FROM requests";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Request> output = new ArrayList<Request>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Request r = new Request();
				r.setId(rs.getInt("request_id"));
				r.setEmpId(rs.getInt("request_emp_id"));
				r.setEventId(rs.getInt("request_event_id"));
				r.setDate(rs.getString("request_date"));
				r.setAmount(rs.getDouble("request_amount"));
				r.setJustfication(rs.getString("request_justification"));
				r.setTimeMissed(rs.getInt("request_time_missed"));
				r.setAttachedApproval(rs.getString("request_attatched_approval"));
				output.add(r);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateRequest(Request r) {
		try {
			String sql = "UPDATE requests SET request_amount = ?, request_justification = ?, request_time_missed = ?, request_attatched_approval = ? WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(r.getAmount()));
			ps.setString(2, r.getJustfication());
			ps.setString(3, String.valueOf(r.getTimeMissed()));
			ps.setString(4, String.valueOf(r.getAttachedApproval()));
			ps.setString(5, String.valueOf(r.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRequest(Request r) {
		try {
			String sql = "DELETE FROM requests WHERE request_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(r.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
