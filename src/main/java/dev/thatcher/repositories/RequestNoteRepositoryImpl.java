package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.RequestNote;
import dev.thatcher.util.JDBCconnection;

public class RequestNoteRepositoryImpl implements RequestNoteRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public RequestNote createRequestNote(RequestNote rn) {
		try {
			String sql = "INSERT INTO request_notes VALUES(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(rn.getId()));
			ps.setString(2, String.valueOf(rn.getRequestId()));
			ps.setString(3, rn.getBody());
			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM request_notes ORDER BY request_note_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				rn.setId(rs.getInt("request_note_id"));
			}
			return rn;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public RequestNote getRequestNoteById(int id) {
		try {
			String sql = "SELECT * FROM request_notes WHERE request_note_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				RequestNote rn = new RequestNote();
				rn.setId(rs.getInt("request_note_id"));
				rn.setRequestId(rs.getInt("request_id"));
				rn.setBody(rs.getString("request_note_body"));
				return rn;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<RequestNote> getAllRequestNotes() {
		try {
			String sql = "select * from request_notes";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<RequestNote> output = new ArrayList<RequestNote>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				RequestNote rn = new RequestNote();
				rn.setId(rs.getInt("request_note_id"));
				rn.setRequestId(rs.getInt("request_id"));
				rn.setBody(rs.getString("request_note_body"));
				output.add(rn);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateRequestNote(RequestNote rn) {
		try {
			String sql = "UPDATE request_notes SET request_id = ?, request_note_body = ? WHERE request_note_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, String.valueOf(rn.getRequestId()));
			ps.setString(2, rn.getBody());
			ps.setString(3, String.valueOf(rn.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteRequestNote(RequestNote rn) {
		try {
			String sql = "DELETE FROM request_notes WHERE request_note_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(rn.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
