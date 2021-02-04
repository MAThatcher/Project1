package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.Notifications;
import dev.thatcher.util.JDBCconnection;

public class NotificationRepositoryImpl implements NotificationRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public Notifications createNotification(Notifications n) {
		try {
			String sql = "INSERT INTO notifications VALUES(?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int p = 0;
			if (n.isSeen()) {
				p = 1;
			}
			ps.setString(1, String.valueOf(n.getId()));
			ps.setString(2, String.valueOf(n.getEmpId()));
			ps.setString(3, n.getBody());
			ps.setString(4, String.valueOf(p));
			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM notifications ORDER BY notification_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				n.setId(rs.getInt("notification_id"));
			}
			return n;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Notifications getNotificationById(int id) {
		try {
			String sql = "SELECT * FROM notifications WHERE notification_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Notifications n = new Notifications();
				n.setId(rs.getInt("notification_id"));
				n.setEmpId(rs.getInt("emp_id"));
				n.setBody(rs.getString("notification_body"));
				n.setSeen(rs.getBoolean("notification_seen"));
				return n;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Notifications> getAllNotifications() {
		try {
			String sql = "SELECT * FROM notifications";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<Notifications> output = new ArrayList<Notifications>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notifications n = new Notifications();
				n.setId(rs.getInt("notification_id"));
				n.setEmpId(rs.getInt("emp_id"));
				n.setBody(rs.getString("notification_body"));
				n.setSeen(rs.getBoolean("notification_seen"));
				output.add(n);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateNotification(Notifications n) {
		try {
			String sql = "UPDATE notifications SET emp_id = ?, notification_body = ?, notification_seen = ? WHERE notification_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int p = 0;
			if (n.isSeen()) {
				p = 1;
			}
			ps.setString(1, String.valueOf(n.getEmpId()));
			ps.setString(2, n.getBody());
			ps.setString(3, String.valueOf(p));
			ps.setString(4, String.valueOf(n.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteNotification(Notifications n) {
		try {
			String sql = "DELETE FROM notifications WHERE notification_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(n.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
