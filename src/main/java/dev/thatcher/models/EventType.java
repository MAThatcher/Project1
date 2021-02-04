package dev.thatcher.models;

public class EventType {
	private int id = 0;
	private double coverage;
	private String name;

	public EventType(int id, double coverage, String name) {
		super();
		this.id = id;
		this.coverage = coverage;
		this.name = name;
	}

	public EventType() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCoverage() {
		return coverage;
	}

	public void setCoverage(double coverage) {
		this.coverage = coverage;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "EventType [id=" + id + ", coverage=" + coverage + ", name=" + name + "]";
	}

}
