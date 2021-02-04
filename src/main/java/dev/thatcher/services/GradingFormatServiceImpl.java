package dev.thatcher.services;

import java.util.List;

import dev.thatcher.models.GradingFormat;
import dev.thatcher.repositories.GradingFormatRepository;
import dev.thatcher.repositories.GradingFormatRepositoryImpl;

public class GradingFormatServiceImpl implements GradingFormatService{
	public GradingFormatRepository gfr = new GradingFormatRepositoryImpl();

	@Override
	public GradingFormat createGradingFormat(GradingFormat g) {
		return gfr.createGradingFormat(g);
	}

	@Override
	public GradingFormat getGradingFormatById(int id) {
		return gfr.getGradingFormatById(id);
	}

	@Override
	public List<GradingFormat> getAllGradingFormat() {
		return gfr.getAllGradingFormat();
	}

	@Override
	public boolean updateGradingFormat(GradingFormat g) {
		return gfr.updateGradingFormat(g);
	}

	@Override
	public boolean deleteGradingFormat(GradingFormat g) {
		return gfr.deleteGradingFormat(g);
	}
}
