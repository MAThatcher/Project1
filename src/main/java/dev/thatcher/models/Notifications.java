package dev.thatcher.models;

public class Notifications {
	private int id = 0;
	private int empId = 0;
	private String body;
	private boolean seen = false;

	public Notifications(int id, int empId, String body, boolean seen) {
		super();
		this.id = id;
		this.empId = empId;
		this.body = body;
		this.seen = seen;
	}

	public Notifications() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public boolean isSeen() {
		return seen;
	}

	public void setSeen(boolean seen) {
		this.seen = seen;
	}

	@Override
	public String toString() {
		return "Notfication [id=" + id + ", empId=" + empId + ", body=" + body + ", seen=" + seen + "]";
	}

}
