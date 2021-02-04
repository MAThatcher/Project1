package dev.thatcher.models;

public class Request {
	private int id = 0;
	private int empId = 0;
	private int eventId = 0;
	private String date;
	private double amount;
	private String justfication;
	private int timeMissed;
	private String attachedApproval;

	public Request() {
		super();
	}

	public Request(int id, int empId, int eventId, String date, double amount, String justfication, int timeMissed,
			String attachedApproval) {
		super();
		this.id = id;
		this.empId = empId;
		this.eventId = eventId;
		this.date = date;
		this.amount = amount;
		this.justfication = justfication;
		this.timeMissed = timeMissed;
		this.attachedApproval = attachedApproval;
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

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getJustfication() {
		return justfication;
	}

	public void setJustfication(String justfication) {
		this.justfication = justfication;
	}

	public int getTimeMissed() {
		return timeMissed;
	}

	public void setTimeMissed(int timeMissed) {
		this.timeMissed = timeMissed;
	}

	public String getAttachedApproval() {
		return attachedApproval;
	}

	public void setAttachedApproval(String attachedApproval) {
		this.attachedApproval = attachedApproval;
	}

	@Override
	public String toString() {
		return "Request [id=" + id + ", empId=" + empId + ", eventId=" + eventId + ", date=" + date + ", amount="
				+ amount + ", justfication=" + justfication + ", timeMissed=" + timeMissed + ", attachedApproval="
				+ attachedApproval + "]";
	}

	

}
