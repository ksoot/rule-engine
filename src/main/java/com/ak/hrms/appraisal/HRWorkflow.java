package com.ak.hrms.appraisal;

import java.time.LocalDate;
import java.util.Optional;

import com.ak.hrms.appraisal.core.ruleengine.RuleEngine;
import com.ak.hrms.appraisal.domain.ruleengine.AppraisalRuleEngineBuilder;
import com.ak.hrms.appraisal.model.Appraisal;
import com.ak.hrms.appraisal.model.Employee;

public class HRWorkflow {

	RuleEngine<Employee> ruleEngine = new AppraisalRuleEngineBuilder().build();

	public Employee onBoarding(Employee employee) {
		System.out.println("Employee Onboarded -> ID: " + employee.getEmpId() +" Name: "+employee.getFirstName());
		return employee;
	}

	public void appraisalInitiated(Employee employee) {
		Optional<Appraisal> appraisal =  Optional.of(new Appraisal(LocalDate.now()));
		employee.setAppraisal(appraisal);
	}

	public void closeAppraisal(Employee employee) {
		
		ruleEngine.run(employee);
	}

}
