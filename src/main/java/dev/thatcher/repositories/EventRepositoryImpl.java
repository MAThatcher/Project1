package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.Event;
import dev.thatcher.util.JDBCconnection;

public class EventRepositoryImpl implements EventRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public Event createEvent(Event e) {
		try {
			String sql = "INSERT INTO events VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(e.getId()));
			ps.setString(2, String.valueOf(e.getEventTypeId()));
			ps.setString(3, String.valueOf(e.getEmpId()));
			ps.setString(4, e.getName());
			ps.setString(5, String.valueOf(e.getGradeFormat()));
			ps.setString(6, e.getRelatedAttachment());
			ps.setString(7, e.getDate());
			ps.setString(8, e.getTime());
			ps.setString(9, e.getLocation());
			
			ps.executeQuery();
			// now update our employee with the proper id
			sql = "SELECT * FROM events ORDER BY event_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				e.setId(rs.getInt("event_id"));
			}
			return e;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public Event getEventById(int id) {
		try {
			String sql = "SELECT * FROM events WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Event newEvent = new Event();
				newEvent.setId(rs.getInt("event_id"));
				newEvent.setEventTypeId(rs.getInt("event_type_id"));
				newEvent.setEmpId(rs.getInt("event_emp_id"));
				newEvent.setName(rs.getString("event_name"));
				newEvent.setGradeFormat(rs.getInt("event_grading_format_id"));
				newEvent.setDate(rs.getString("event_date"));
				newEvent.setLocation(rs.getString("event_location"));
				newEvent.setRelatedAttachment(rs.getString("event_related_attachments"));
				newEvent.setTime(rs.getString("event_time"));
				return newEvent;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Event> getAllEvents() {
		try {
			String sql = "SELECT * FROM events";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Event> output = new ArrayList<Event>();
			while (rs.next()) {
				Event newEvent = new Event();
				newEvent.setId(rs.getInt("event_id"));
				newEvent.setEventTypeId(rs.getInt("event_type_id"));
				newEvent.setEmpId(rs.getInt("event_emp_id"));
				newEvent.setName(rs.getString("event_name"));
				newEvent.setGradeFormat(rs.getInt("event_grading_format_id"));
				newEvent.setDate(rs.getString("event_date"));
				newEvent.setLocation(rs.getString("event_location"));
				newEvent.setRelatedAttachment(rs.getString("event_related_attachments"));
				newEvent.setTime(rs.getString("event_time"));
				
				output.add(newEvent);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEvent(Event e) {
		try {
			String sql = "UPDATE events SET event_type_id = ?, event_emp_id = ?, event_name = ?, event_grading_format_id = ?, event_date = ?, event_location = ?, event_related_attachments = ?, event_time = ? WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, String.valueOf(e.getEventTypeId()));
			ps.setString(2, String.valueOf(e.getEmpId()));
			ps.setString(3, e.getName());
			ps.setString(4, String.valueOf(e.getGradeFormat()));
			ps.setString(5, e.getDate());
			ps.setString(6, e.getLocation());
			ps.setString(7, e.getRelatedAttachment());
			ps.setString(8, e.getTime());
			ps.setString(9, String.valueOf(e.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteEvent(Event e) {
		try {
			String sql = "DELETE FROM events WHERE event_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(e.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
