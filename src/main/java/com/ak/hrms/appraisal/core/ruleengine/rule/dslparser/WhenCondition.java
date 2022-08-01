package com.ak.hrms.appraisal.core.ruleengine.rule.dslparser;

import java.util.function.Predicate;

public interface WhenCondition<T> {

	public ThenCondition<T> when(Predicate<T> condition);

}
