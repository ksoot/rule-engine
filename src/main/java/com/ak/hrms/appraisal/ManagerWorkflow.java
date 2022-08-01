package com.ak.hrms.appraisal;

import com.ak.hrms.appraisal.model.Employee;
import com.ak.hrms.appraisal.notification.NotificationRequest;

public class ManagerWorkflow {

	public void setRating(Employee employee, byte rating, String comment) {
		if (employee.getAppraisal().get().isDiscussionClosed()) {
			employee.getAppraisal().get().setRating(rating);
			employee.getAppraisal().get().setComment(comment);
		} else {
			inviteEmployeeForDiscussion(employee);
		}
	}

	public void inviteEmployeeForDiscussion(Employee employee) {
		NotificationRequest notificationRequest = new NotificationRequest(employee.getEmail(),
				"Please join me for your kra rating");
		Utils.sendNotification(notificationRequest);
	}

	public void discussionClosed(Employee employee) {
		employee.getAppraisal().get().setDiscussionClosed(true);
	}

	public void distributeLetter(Employee employee) {
		NotificationRequest notificationRequest = new NotificationRequest(employee.getEmail(),
				"Congratulation " + employee.getFirstName() + ". Please check and acknowledge your appraisal letter");
		Utils.sendNotification(notificationRequest);
	}

}
