package dev.thatcher.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dev.thatcher.models.GradingFormat;
import dev.thatcher.util.JDBCconnection;

public class GradingFormatRepositoryImpl implements GradingFormatRepository {
	public static Connection conn = JDBCconnection.getConnection();

	@Override
	public GradingFormat createGradingFormat(GradingFormat g) {
		try {
			String sql = "INSERT INTO grading_formats(grading_format_grade,grading_format_pass) VALUES(?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			int p = 0;
			if (g.isPass()) {
				p = 1;
			}
			ps.setString(1, String.valueOf(g.getGrade()));
			ps.setString(2, String.valueOf(p));
			ps.executeQuery();
			// now update our event with the proper id
			sql = "SELECT * FROM grading_formats ORDER BY grading_format_id DESC";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				g.setId(rs.getInt("grading_format_id"));
			}
			return g;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	@Override
	public GradingFormat getGradingFormatById(int id) {
		try {
			String sql = "SELECT * FROM grading_formats WHERE grading_format_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(id));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("grading_format_id"));
				gf.setGrade(rs.getString("grading_format_grade"));
				gf.setPass(rs.getBoolean("grading_format_pass"));
				return gf;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<GradingFormat> getAllGradingFormat() {
		try {
			String sql = "SELECT * FROM grading_formats ORDER BY grading_format_id";
			PreparedStatement ps = conn.prepareStatement(sql);
			List<GradingFormat> output = new ArrayList<GradingFormat>();
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				GradingFormat gf = new GradingFormat();
				gf.setId(rs.getInt("grading_format_id"));
				gf.setGrade(rs.getString("grading_format_grade"));
				gf.setPass(rs.getBoolean("grading_format_pass"));
				output.add(gf);
			}
			return output;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateGradingFormat(GradingFormat g) {
		try {
			String sql = "UPDATE grading_formats SET grading_format_grade = ?, grading_format_pass = ? WHERE grading_format_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			int p = 0;
			if (g.isPass()) {
				p = 1;
			}
			ps.setString(1, String.valueOf(g.getGrade()));
			ps.setString(2, String.valueOf(p));
			ps.setString(3, String.valueOf(g.getId()));
			ps.executeQuery();
			return true;

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteGradingFormat(GradingFormat g) {
		try {
			String sql = "DELETE FROM grading_formats WHERE grading_format_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, Integer.toString(g.getId()));
			ps.executeQuery();

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return false;
	}
}
