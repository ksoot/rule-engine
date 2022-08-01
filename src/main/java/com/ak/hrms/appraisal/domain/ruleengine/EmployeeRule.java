package com.ak.hrms.appraisal.domain.ruleengine;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.ak.hrms.appraisal.core.ruleengine.rule.AbstractRule;
import com.ak.hrms.appraisal.model.Employee;

public class EmployeeRule extends AbstractRule<Employee> {

	public EmployeeRule(Function<Employee, String> description, Predicate<Employee> condition, Consumer<Employee> action) {
		super(description, condition, action);

	}
}
