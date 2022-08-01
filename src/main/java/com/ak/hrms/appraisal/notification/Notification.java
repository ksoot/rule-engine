package com.ak.hrms.appraisal.notification;

public class Notification {

	
	public static void notify(NotificationRequest notificationRequest) {
		System.out.println("Sent notification to :" +notificationRequest.toString());
	}
	
}
