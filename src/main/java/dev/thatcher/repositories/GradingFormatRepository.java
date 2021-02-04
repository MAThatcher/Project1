package dev.thatcher.repositories;

import java.util.List;

import dev.thatcher.models.GradingFormat;

public interface GradingFormatRepository {
	public GradingFormat createGradingFormat(GradingFormat g);

	public GradingFormat getGradingFormatById(int id);

	public List<GradingFormat> getAllGradingFormat();

	public boolean updateGradingFormat(GradingFormat g);

	public boolean deleteGradingFormat(GradingFormat g);
}
