package com.healthfullu.data.model;


/**
 * Encapsulates Email object.
 * 
 * @author Zhi Chen
 */
public class Email {

	private String from;
	private String to;
	private String senderName;
	private String receiverName;
	private String subject;
	private String body;
	
	public Email() {}
	
	public Email(String from, String to, String receiverName, String senderName, 
				 String subject, String body) {
		this.from = from;
		this.to = to;
		this.receiverName = receiverName;
		this.senderName = senderName;
		this.subject = subject;
		this.body = body;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getFrom() {
		return from;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getTo() {
		return to;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSubject() {
		return subject;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getBody() {
		return body;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public String getSenderName() {
		return senderName;
	}
}
