package com.ak.hrms.appraisal.domain.ruleengine;

import com.ak.hrms.appraisal.core.ruleengine.rule.AbstractRuleBuilder;
import com.ak.hrms.appraisal.core.ruleengine.rule.Rule;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.SetRule;
import com.ak.hrms.appraisal.model.Employee;

public class AppraisalRuleBuilder extends AbstractRuleBuilder<Employee> {

	@Override
	public SetRule<Employee> build() {
		Rule<Employee> rule = new EmployeeRule(this.getDescription(), this.getCondition(), this.getAction());
		this.getRulesHolder().addRule(rule);
		return this;
	}
}
