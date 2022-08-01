package com.ak.hrms.appraisal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.ak.hrms.appraisal.model.Employee;
import com.ak.hrms.appraisal.notification.NotificationRequest;

public class AppraisalMain {

	private static List<Employee> employees = new ArrayList<>();
	private static List<Employee> ratingSetEmployees = new ArrayList<>();

	public static void main(String[] args) {
		ExecutorService ex = Executors.newFixedThreadPool(3);

		CyclicBarrier barrier = new CyclicBarrier(2);
		ex.execute(new HRRunnable(employees, ratingSetEmployees, barrier));
		ex.execute(new ManagerRunnable(employees, ratingSetEmployees, barrier));
		ex.shutdown();
	}
}

class HRRunnable implements Runnable {
	private List<Employee> employeesOnboarded;
	List<Employee> ratingSetEmployees;
	private HRWorkflow workFlow = new HRWorkflow();
	private CyclicBarrier barrier;

	public HRRunnable(List<Employee> employeesOnboarded, List<Employee> ratingSetEmployees, CyclicBarrier barrier) {
		this.employeesOnboarded = employeesOnboarded;
		this.ratingSetEmployees = ratingSetEmployees;
		this.barrier = barrier;
	}

	@Override
	public void run() {

		if (employeesOnboarded.isEmpty()) {
			Utils.getAllEmployees().stream().forEach(employee -> {
				employeesOnboarded.add(workFlow.onBoarding(employee));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}

		employeesOnboarded.stream()
				.peek(employee -> System.out.println(
						"Appraisal Initiated by HR for -> ID: " + employee.getEmpId() + " Name: " + employee.getFirstName()))
				.forEach(employee -> {
					workFlow.appraisalInitiated(employee);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});

		employeesOnboarded.stream().map(employee -> employee.getMgrId()).distinct()
				.peek(mgrId -> System.out.println("System informed Manager to start discussion -> MgrID: " + mgrId))
				.forEach(mgrId -> {
					Utils.sendNotification(new NotificationRequest(mgrId, "Please start discussion with your team"));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});
		try {
			barrier.await(); 
			Thread.sleep(2000);
			
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		// waiting for rating to be set
		barrier.reset();

		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		ratingSetEmployees.stream()
				.peek(employee -> System.out.println("=========================Next Employee================="))
				.peek(employee -> System.out.println("Before Appraisal: \n" + employee.toString() + "\n"))
				.forEach(employee -> {
					workFlow.closeAppraisal(employee);
					System.out.println("After Appraisal: \n" + employee.toString() + "\n");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				});

	}

}

class ManagerRunnable implements Runnable {
	private List<Employee> employeesToNotify;
	private List<Employee> discussionClosedEmployeess = new ArrayList<>();
	private List<Employee> ratingSetEmployees;
	private ManagerWorkflow workFlow = new ManagerWorkflow();
	private CyclicBarrier barrier;

	public ManagerRunnable(List<Employee> employeesToNotify, List<Employee> ratingSetEmployees, CyclicBarrier barrier) {
		this.employeesToNotify = employeesToNotify;
		this.ratingSetEmployees = ratingSetEmployees;
		this.barrier = barrier;
	}

	@Override
	public void run() {
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e1) {
			e1.printStackTrace();
		}

		while (!employeesToNotify.isEmpty() || !discussionClosedEmployeess.isEmpty()) {
			employeesToNotify.stream().forEach(employee -> {
				workFlow.inviteEmployeeForDiscussion(employee);
				discussionClosedEmployeess.add(employee);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			employeesToNotify.clear();
			discussionClosedEmployeess.stream().peek(employee -> System.out.println("Discussion closed with employee : "+employee.getFirstName())).forEach(employee -> {
				workFlow.discussionClosed(employee);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});

			discussionClosedEmployeess.stream().peek(employee -> System.out.println("Rating set for employee : "+employee.getFirstName())).forEach(employee -> {
				workFlow.setRating(employee, Utils.rating(employee), Utils.comment(employee));
				ratingSetEmployees.add(employee);

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
			discussionClosedEmployeess.clear();
		}
		try {
			barrier.await();
		} catch (InterruptedException | BrokenBarrierException e) {
			e.printStackTrace();
		}
	}

}