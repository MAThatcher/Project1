package dev.thatcher.models;

public class RequestTracker {
	private int id = 0;
	private int requestId = 0;
	private int supId = 0;
	private int depId = 0;
	private int bencoId = 0;
	private int state = 0;
	private String date;
	private int grade = 0;
	private boolean urgent = false;
	public RequestTracker() {
		super();
	}
	public RequestTracker(int id, int requestId, int supId, int depId, int bencoId, int state, String date, int grade,
			boolean urgent) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.supId = supId;
		this.depId = depId;
		this.bencoId = bencoId;
		this.state = state;
		this.date = date;
		this.grade = grade;
		this.urgent = urgent;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRequestId() {
		return requestId;
	}
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public int getDepId() {
		return depId;
	}
	public void setDepId(int depId) {
		this.depId = depId;
	}
	public int getBencoId() {
		return bencoId;
	}
	public void setBencoId(int bencoId) {
		this.bencoId = bencoId;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public boolean isUrgent() {
		return urgent;
	}
	public void setUrgent(boolean urgent) {
		this.urgent = urgent;
	}
	@Override
	public String toString() {
		return "RequestTracker [id=" + id + ", requestId=" + requestId + ", supId=" + supId + ", depId=" + depId
				+ ", bencoId=" + bencoId + ", state=" + state + ", date=" + date + ", grade=" + grade + ", urgent="
				+ urgent + "]";
	}
	
	
}
