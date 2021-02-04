package dev.thatcher.models;

public class RequestNote {
	private int id = 0;
	private int requestId = 0;
	private String body;

	public RequestNote(int id, int requestId, String body) {
		super();
		this.id = id;
		this.requestId = requestId;
		this.body = body;
	}

	public RequestNote() {
		super();
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

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "RequestNote [id=" + id + ", requestId=" + requestId + ", body=" + body + "]";
	}

}
