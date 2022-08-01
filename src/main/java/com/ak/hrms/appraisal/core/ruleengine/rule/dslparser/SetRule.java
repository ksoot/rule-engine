package com.ak.hrms.appraisal.core.ruleengine.rule.dslparser;

import com.ak.hrms.appraisal.core.ruleengine.RulesHolder;

public interface SetRule<T>{

	public Description<T> rule();
	public RulesHolder<T> done();

}
