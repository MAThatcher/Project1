package dev.thatcher.models;

public class Event {
	private int id = 0;
	private int eventTypeId = 0;
	private int empId = 0;
	private int gradeFormat = 0;
	private String name;
	private String date;
	private String time;
	private String location;
	private String relatedAttachment;

	public Event() {
		super();
	}

	public Event(int id, int eventTypeId, int empId, int gradeFormat, String name, String date, String time,
			String location, String relatedAttachment) {
		super();
		this.id = id;
		this.eventTypeId = eventTypeId;
		this.empId = empId;
		this.gradeFormat = gradeFormat;
		this.name = name;
		this.date = date;
		this.time = time;
		this.location = location;
		this.relatedAttachment = relatedAttachment;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public int getGradeFormat() {
		return gradeFormat;
	}

	public void setGradeFormat(int gradeFormat) {
		this.gradeFormat = gradeFormat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getRelatedAttachment() {
		return relatedAttachment;
	}

	public void setRelatedAttachment(String relatedAttachment) {
		this.relatedAttachment = relatedAttachment;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", eventTypeId=" + eventTypeId + ", empId=" + empId + ", gradeFormat=" + gradeFormat
				+ ", name=" + name + ", date=" + date + ", time=" + time + ", location=" + location
				+ ", relatedAttachment=" + relatedAttachment + "]";
	}

	

	
	

}
