package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.RequestTracker;
import dev.thatcher.util.JDBCconnection;

public class RequestTrackerRepositoryImpl implements RequestTrackerRepository {
	public static Connection conn = JDBCconnection.getConnection();
	@Override
	public RequestTracker createRequestTracker(RequestTracker rt) {
		try {
			String sql = "INSERT INTO request_trackers VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int u = 0;
			if (rt.isUrgent()) {
				u=1;
			}
			rt.setState(1);
			ps.setString(1, String.valueOf(rt.getId()));
			ps.setString(2, String.valueOf(rt.getRequestId()));
			ps.setString(3, String.valueOf(rt.getSupId()));
			ps.setString(4, String.valueOf(rt.getDepId()));
			ps.setString(5, String.valueOf(rt.getBencoId()));
			ps.setString(6, String.valueOf(rt.getState()));
			ps.setString(7, "30-JAN-21");
			ps.setString(8, String.valueOf(rt.getGrade()));
			ps.setString(9, String.valueOf(u));
			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM request_trackers ORDER BY request_tracker_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rt.setId(rs.getInt("request_tracker_id"));
			}
			return rt;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public RequestTracker getRequestTrackerById(int id) {
		try {
			String sql = "SELECT * FROM request_trackers WHERE request_tracker_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequestTracker r = new RequestTracker();
				r.setId(rs.getInt("request_tracker_id"));
				r.setRequestId(rs.getInt("request_id"));
				r.setSupId(rs.getInt("request_tracker_sup_id"));
				r.setDepId(rs.getInt("request_tracker_dep_id"));
				r.setBencoId(rs.getInt("request_tracker_benco_id"));
				r.setState(rs.getInt("request_tracker_state"));
				r.setDate(rs.getString("request_tracker_escalation_date"));
				r.setGrade(rs.getInt("request_tracker_grade"));
				r.setUrgent(rs.getBoolean("request_tracker_urgent"));
				return r;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RequestTracker> getAllRequestTrackers() {
		try {
			String sql = "SELECT * FROM request_trackers ORDER BY request_tracker_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<RequestTracker> output = new ArrayList<RequestTracker>();
			while (rs.next()) {
				RequestTracker r = new RequestTracker();
				r.setId(rs.getInt("request_tracker_id"));
				r.setRequestId(rs.getInt("request_id"));
				r.setSupId(rs.getInt("request_tracker_sup_id"));
				r.setDepId(rs.getInt("request_tracker_dep_id"));
				r.setBencoId(rs.getInt("request_tracker_benco_id"));
				r.setState(rs.getInt("request_tracker_state"));
				r.setDate(rs.getString("request_tracker_escalation_date"));
				r.setGrade(rs.getInt("request_tracker_grade"));
				r.setUrgent(rs.getBoolean("request_tracker_urgent"));
				output.add(r);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateRequestTracker(RequestTracker rt) {
		try {
			String sql = "UPDATE request_trackers SET request_tracker_sup_id = ?, request_tracker_dep_id = ?, request_tracker_benco_id = ?, request_tracker_state = ?, request_tracker_grade = ? WHERE request_tracker_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(rt.getSupId()));
			ps.setString(2, String.valueOf(rt.getDepId()));
			ps.setString(3, String.valueOf(rt.getBencoId()));
			ps.setString(4, String.valueOf(rt.getState()));
			ps.setString(5, String.valueOf(rt.getGrade()));
			ps.setString(6, String.valueOf(rt.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRequestTracker(RequestTracker rt) {
		try {
			String sql = "DELETE FROM request_trackers WHERE request_tracker_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(rt.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
