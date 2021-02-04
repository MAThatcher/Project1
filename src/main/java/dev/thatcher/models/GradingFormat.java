package dev.thatcher.models;

public class GradingFormat {
	private int id = 0;
	private String grade;
	private boolean pass;

	public GradingFormat(int id, String grade, boolean pass) {
		super();
		this.id = id;
		this.grade = grade;
		this.pass = pass;
	}

	public GradingFormat() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public boolean isPass() {
		return pass;
	}

	public void setPass(boolean pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "GradingFormat [id=" + id + ", grade=" + grade + ", pass=" + pass + "]";
	}

}
