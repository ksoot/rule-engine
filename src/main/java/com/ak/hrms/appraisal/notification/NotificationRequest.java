package com.ak.hrms.appraisal.notification;

// Immutable notification information holder
public final class NotificationRequest {

	final private String fromEmail = "no-reply@xyz.com";
	final private String toEmail;
	final private String message;

	public NotificationRequest(String toEmail, String message) {
		this.toEmail = toEmail;
		this.message = message;
	}


	public String getToEmail() {
		return toEmail;
	}
	
	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return toEmail + ", message=" + message;
	}
	

}
