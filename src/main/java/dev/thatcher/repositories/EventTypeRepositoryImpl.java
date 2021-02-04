package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.EventType;
import dev.thatcher.util.JDBCconnection;

public class EventTypeRepositoryImpl implements EventTypeRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public EventType createEventType(EventType e) {
		try {
			String sql = "INSERT INTO event_types(event_type_coverage,event_type_name) VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(e.getCoverage()));
			ps.setString(2, String.valueOf(e.getName()));

			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM event_types ORDER BY event_types_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt("event_type_id"));
			}
			return e;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public EventType getEventTypeById(int id) {
		try {
			String sql = "SELECT * FROM event_types WHERE event_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EventType newEventType = new EventType();
				newEventType.setId(rs.getInt("event_type_id"));
				newEventType.setCoverage(rs.getDouble("event_type_coverage"));
				newEventType.setName(rs.getString("event_type_name"));
				return newEventType;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<EventType> getAllEventTypes() {
		try {
			String sql = "SELECT * FROM event_types";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<EventType> output = new ArrayList<EventType>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				EventType newEventType = new EventType();
				newEventType.setId(rs.getInt("event_type_id"));
				newEventType.setCoverage(rs.getDouble("event_type_coverage"));
				newEventType.setName(rs.getString("event_type_name"));
				output.add(newEventType);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEventType(EventType e) {
		try {
			String sql = "UPDATE event_types SET event_type_coverage = ?, event_type_name = ? WHERE event_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(e.getCoverage()));
			ps.setString(2, e.getName());
			ps.setString(3, String.valueOf(e.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEventType(EventType e) {
		try {
			String sql = "DELETE FROM event_types WHERE event_type_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(e.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
