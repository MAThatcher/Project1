package dev.thatcher.models;

public class Employee {
	private int id = 0;
	private String fname;
	private String lname;
	private String email;
	private String password;
	private int supId;
	private boolean depHead;
	private boolean benco;
	private double funds = 1000;
	public Employee() {
		super();
	}
	public Employee(int id, String fname, String lname, String email, String password, int supId, boolean depHead,
			boolean benco, double funds) {
		super();
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.email = email;
		this.password = password;
		this.supId = supId;
		this.depHead = depHead;
		this.benco = benco;
		this.funds = funds;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getLname() {
		return lname;
	}
	public void setLname(String lname) {
		this.lname = lname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public boolean isDepHead() {
		return depHead;
	}
	public void setDepHead(boolean depHead) {
		this.depHead = depHead;
	}
	public boolean isBenco() {
		return benco;
	}
	public void setBenco(boolean benco) {
		this.benco = benco;
	}
	public double getFunds() {
		return funds;
	}
	public void setFunds(double funds) {
		this.funds = funds;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", fname=" + fname + ", lname=" + lname + ", email=" + email + ", password="
				+ password + ", supId=" + supId + ", depHead=" + depHead + ", benco=" + benco + ", funds=" + funds
				+ "]";
	}
	
	
}
