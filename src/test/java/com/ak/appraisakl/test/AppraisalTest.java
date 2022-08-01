package com.ak.appraisakl.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ak.hrms.appraisal.HRWorkflow;
import com.ak.hrms.appraisal.ManagerWorkflow;
import com.ak.hrms.appraisal.Utils;
import com.ak.hrms.appraisal.model.Employee;

public class AppraisalTest {

	private HRWorkflow hrWorkflow;
	private ManagerWorkflow managerWorkflow;

	@Before
	public void setup() {
		hrWorkflow = new HRWorkflow();
		managerWorkflow = new ManagerWorkflow();
	}

	@Test
	public void test_Emp_Tenure_Gtr_OneYr_And_ServingNotice() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureGtrOneYrAndServingNotice();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 0.0", employee.getAppraisal().get().getHike(), 0.0, 0.0);
		Assert.assertEquals("ctc should be 15.0", employee.getCtc(), 15.0, 15.0);
		Assert.assertEquals("hike should not be applicable", employee.getAppraisal().get().isHikeApplicable(), false);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), true);
		Assert.assertEquals("tenure should be greater than 365", employee.getTenure(), 365, 2000);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Employee on notice. Hence not eligible for appraisal");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should be effective ", employee.getAppraisal().get().getAppraisalEffectiveDate(), null);
	
	}
	
	@Test
	public void test_Emp_Tenure_Less_OneYr_And_ServingNotice() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureLessOneYrAndServingNotice();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 0.0", employee.getAppraisal().get().getHike(), 0.0, 0.0);
		Assert.assertEquals("ctc should be 15.0", employee.getCtc(), 15.0, 15.0);
		Assert.assertEquals("hike should not be applicable", employee.getAppraisal().get().isHikeApplicable(), false);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), true);
		Assert.assertEquals("tenure should be less than 365", employee.getTenure(), 1, 365);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Employee on notice. Hence not eligible for appraisal");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should be effective ", employee.getAppraisal().get().getAppraisalEffectiveDate(), null);
	
	}
	
	@Test
	public void test_Emp_Tenure_Less_OneYr_NotServingNotice_ExceptionallyWell() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureLessOneYrNotServingNoticeExceptionallyWell();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should be 0.0", employee.getAppraisal().get().getHike(), 0.0, 0.0);
		Assert.assertEquals("ctc should be 15.5", employee.getCtc(), 15.5, 15.5);
		Assert.assertEquals("rating should be 4 Or 5", employee.getAppraisal().get().getRating(), 4, 1);
		Assert.assertEquals("hike should not be applicable", employee.getAppraisal().get().isHikeApplicable(), false);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
		Assert.assertEquals("tenure should be less than 365", employee.getTenure(), 1, 365);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Must be eligible for good bonus");
		Assert.assertEquals("bonus should be applicable", employee.getAppraisal().get().isBonusApplicable(), true);
		Assert.assertEquals("bonus amount should be between 10000 to 100000", employee.getAppraisal().get().getBonus(), 10000, 90000);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should not be effective ", employee.getAppraisal().get().getAppraisalEffectiveDate(), null);
	
	}
	
	
	@Test
	public void test_Emp_Tenure_Less_OneYr_NotServingNotice_NotWell() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureLessOneYrNotServingNoticeWithRatingLess3();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 0.0", employee.getAppraisal().get().getHike(), 0.0, 0.0);
		Assert.assertEquals("ctc should be 15.5", employee.getCtc(), 15.5, 15.5);
		Assert.assertEquals("rating should be less than 3", employee.getAppraisal().get().getRating(), 0, 2);
		Assert.assertEquals("hike should not be applicable", employee.getAppraisal().get().isHikeApplicable(), false);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
		Assert.assertEquals("tenure should be less than 365", employee.getTenure(), 1, 365);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Recomending for spring training");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should be required ", employee.getAppraisal().get().isTrainingRequired(), true);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should not be effective ", employee.getAppraisal().get().getAppraisalEffectiveDate(), null);
	
	}
	
	@Test
		public void test_Emp_Tenure_Greater_1Yr_NotServingNotice_RatingLess3() {
			Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureGtrOneYrNotServingNoticeWithRatingLess3();
			employee = hrWorkflow.onBoarding(employee);
			hrWorkflow.appraisalInitiated(employee);

			managerWorkflow.inviteEmployeeForDiscussion(employee);
			managerWorkflow.discussionClosed(employee);
			managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
					com.ak.hrms.appraisal.Utils.comment(employee));
			System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
			hrWorkflow.closeAppraisal(employee);
			managerWorkflow.distributeLetter(employee);
			System.out.println("After Appraisal: \n" + employee.toString() + "\n");
			Assert.assertEquals("Hike should 0.0", employee.getAppraisal().get().getHike(), 0.0, 0.0);
			Assert.assertEquals("ctc should be 15.5", employee.getCtc(), 15.5, 15.5);
			Assert.assertEquals("rating should be less than 3", employee.getAppraisal().get().getRating(), 0, 2);
			Assert.assertEquals("hike should not be applicable", employee.getAppraisal().get().isHikeApplicable(), false);
			Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
			Assert.assertEquals("tenure should be greater than 365", employee.getTenure(), 365, 2000);
			Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Recommending for PIP for core areas");
			Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
			Assert.assertEquals("bonus amount should not be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
			Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
			Assert.assertEquals("pip should be applicable ", employee.getAppraisal().get().isPip(), true);
			Assert.assertEquals("appraisal should not be effective ", employee.getAppraisal().get().getAppraisalEffectiveDate(), null);
		
		}
	
	@Test
	public void test_Emp_Tenure_Greater_1Yr_NotServingNotice_Rating3() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureGtrOneYrNotServingNoticeWithRating3();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 5.0", employee.getAppraisal().get().getHike(), 5.0, 5.0);
		Assert.assertEquals("ctc should be 16.275", employee.getCtc(), 16.275, 16.275);
		Assert.assertEquals("rating should be 3", employee.getAppraisal().get().getRating(), 3);
		Assert.assertEquals("hike should be applicable", employee.getAppraisal().get().isHikeApplicable(), true);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
		Assert.assertEquals("tenure should be greater than 365", employee.getTenure(), 365, 2000);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Average throughout the year, must learn new areas");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should be effective 2022-08-01 ", employee.getAppraisal().get().getAppraisalEffectiveDate().toString(), "2022-08-01");
	
	}
	
	@Test
	public void test_Emp_Tenure_Greater_1Yr_NotServingNotice_Rating4() {
		Employee employee = com.ak.hrms.appraisal.Utils.buildEmpTenureGtrOneYrNotServingNoticeWithRating4();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);

		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 7.0", employee.getAppraisal().get().getHike(), 7.0, 7.0);
		Assert.assertEquals("ctc should be 16.585", employee.getCtc(), 16.585, 16.585);
		Assert.assertEquals("rating should be 4", employee.getAppraisal().get().getRating(), 4);
		Assert.assertEquals("hike should be applicable", employee.getAppraisal().get().isHikeApplicable(), true);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
		Assert.assertEquals("tenure should be greater than 365", employee.getTenure(), 365, 2000);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Please starts contributing in interviews and get some certificates done");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should be effective 2022-08-01 ", employee.getAppraisal().get().getAppraisalEffectiveDate().toString(), "2022-08-01");
	}
	
	@Test
	public void test_Emp_Tenure_Greater_1Yr_NotServingNotice_Rating5() {
		Employee employee = Utils.buildEmpTenureGtrOneYrNotServingNoticeWithRating5();
		employee = hrWorkflow.onBoarding(employee);
		hrWorkflow.appraisalInitiated(employee);
		managerWorkflow.inviteEmployeeForDiscussion(employee);
		managerWorkflow.discussionClosed(employee);
		managerWorkflow.setRating(employee, com.ak.hrms.appraisal.Utils.rating(employee),
				com.ak.hrms.appraisal.Utils.comment(employee));
		System.out.println("Before Appraisal: \n" + employee.toString() + "\n");
		hrWorkflow.closeAppraisal(employee);
		managerWorkflow.distributeLetter(employee);
		System.out.println("After Appraisal: \n" + employee.toString() + "\n");
		Assert.assertEquals("Hike should 10.0", employee.getAppraisal().get().getHike(), 10.0, 10.0);
		Assert.assertEquals("ctc should be 16.5", employee.getCtc(), 16.5, 16.5);
		Assert.assertEquals("rating should be 5", employee.getAppraisal().get().getRating(), 5);
		Assert.assertEquals("hike should be applicable", employee.getAppraisal().get().isHikeApplicable(), true);
		Assert.assertEquals("should not on notice", employee.isServingNotice(), false);
		Assert.assertEquals("tenure should be greater than 365", employee.getTenure(), 365, 2000);
		Assert.assertEquals("comment should be ", employee.getAppraisal().get().getComment(), "Keep the good work ahead. You have done very well");
		Assert.assertEquals("bonus should not be applicable", employee.getAppraisal().get().isBonusApplicable(), false);
		Assert.assertEquals("bonus amount should be 0.0", employee.getAppraisal().get().getBonus(), 0.0, 0.0);
		Assert.assertEquals("training should not be required ", employee.getAppraisal().get().isTrainingRequired(), false);
		Assert.assertEquals("pip should not be applicable ", employee.getAppraisal().get().isPip(), false);
		Assert.assertEquals("appraisal should be effective 2022-08-01 ", employee.getAppraisal().get().getAppraisalEffectiveDate().toString(), "2022-08-01");
	}

}
