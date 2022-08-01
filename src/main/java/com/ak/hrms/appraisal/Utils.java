package com.ak.hrms.appraisal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.ak.hrms.appraisal.model.Employee;
import com.ak.hrms.appraisal.model.Gender;
import com.ak.hrms.appraisal.model.Employee.EmployeeBuilder;
import com.ak.hrms.appraisal.notification.NotificationRequest;

public class Utils {

	public static void sendNotification(NotificationRequest notificationRequest) {
		System.out.println("notification sent to : "+notificationRequest.toString());
	}
	
	public static byte rating(Employee employee) {

		switch (employee.getEmpId()) {
		case "1": {
			return (byte) 5;
		}
		case "2": {
			return (byte) 1;
		}
		case "3": {
			return (byte) 5;
		}
		case "4": {
			return (byte) 2;
		}
		case "5": {
			return (byte) 1;
		}
		case "6": {
			return (byte) 3;
		}
		case "7": {
			return (byte) 4;
		}
		case "8": {
			return (byte) 5;
		}
		default:
			return (byte) 0;
		}
	}

	
	public static String comment(Employee employee) {

		switch (employee.getEmpId()) {
		case "1": {
			return "No comments. Notice period";
		}
		case "2": {
			return "No comments. Notice period";
		}
		case "3": {
			return "Must be eligible for good bonus";
		}
		case "4": {
			return "Recomending for spring training";
		}
		case "5": {
			return "Recommending for PIP for core areas";
		}
		case "6": {
			return "Average throughout the year, must learn new areas";
		}
		case "7": {
			return "Please starts contributing in interviews and get some certificates done";
		}
		case "8": {
			return "Keep the good work ahead. You have done very well";
		}
		default:
			return "No";
		}
	}

	public static List<Employee> getAllEmployees() {
		List<Employee> employees = new ArrayList<>();
		employees.add(buildEmpTenureLessOneYrAndServingNotice());
		employees.add(buildEmpTenureGtrOneYrAndServingNotice());
		employees.add(buildEmpTenureLessOneYrNotServingNoticeExceptionallyWell());
		employees.add(buildEmpTenureLessOneYrNotServingNoticeWithRatingLess3());
		employees.add(buildEmpTenureGtrOneYrNotServingNoticeWithRatingLess3());
		employees.add(buildEmpTenureGtrOneYrNotServingNoticeWithRating3());
		employees.add(buildEmpTenureGtrOneYrNotServingNoticeWithRating4());
		employees.add(buildEmpTenureGtrOneYrNotServingNoticeWithRating5());
		return employees;
	}

	public static Employee buildEmpTenureLessOneYrAndServingNotice() {
		Employee emp = EmployeeBuilder.getBuilder("1", "101", LocalDate.of(2022, 1, 1)).firstName("Akshay")
				.lastName("Kumar").email("akshay.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		emp.setServingNotice(true);
		return emp;
	}

	public static Employee buildEmpTenureGtrOneYrAndServingNotice() {
		Employee emp = EmployeeBuilder.getBuilder("2", "102", LocalDate.of(2021, 1, 1)).firstName("Mohit")
				.lastName("Kumar").email("mohit.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		emp.setServingNotice(true);
		return emp;
	}

	public static Employee buildEmpTenureLessOneYrNotServingNoticeExceptionallyWell() {
		Employee emp = EmployeeBuilder.getBuilder("3", "101", LocalDate.of(2022, 1, 1)).firstName("Ankit")
				.lastName("Kumar").email("ankit.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		return emp;
	}

	public static Employee buildEmpTenureLessOneYrNotServingNoticeWithRatingLess3() {
		Employee emp = EmployeeBuilder.getBuilder("4", "101", LocalDate.of(2022, 1, 1)).firstName("Suman")
				.lastName("Kumar").email("suman.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		return emp;
	}

	public static Employee buildEmpTenureGtrOneYrNotServingNoticeWithRatingLess3() {
		Employee emp = EmployeeBuilder.getBuilder("5", "102", LocalDate.of(2020, 1, 1)).firstName("Raj")
				.lastName("Kumar").email("raj.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		return emp;
	}

	public static Employee buildEmpTenureGtrOneYrNotServingNoticeWithRating3() {
		Employee emp = EmployeeBuilder.getBuilder("6", "102", LocalDate.of(2019, 1, 1)).firstName("Manoj")
				.lastName("Kumar").email("manoj.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		return emp;
	}

	public static Employee buildEmpTenureGtrOneYrNotServingNoticeWithRating4() {
		Employee emp = EmployeeBuilder.getBuilder("7", "101", LocalDate.of(2019, 1, 1)).firstName("Sumit")
				.lastName("Kumar").email("sumit.kumar@abcdomain.in").gender(Gender.MALE).ctc(15).build();
		return emp;
	}

	public static Employee buildEmpTenureGtrOneYrNotServingNoticeWithRating5() {
		Employee emp = EmployeeBuilder.getBuilder("8", "102", LocalDate.of(2019, 1, 1)).firstName("Monica")
				.lastName("Singh").email("monica.kumar@abcdomain.in").gender(Gender.FEMALE).ctc(15).build();
		return emp;
	}


}
