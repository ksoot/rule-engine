package com.ak.hrms.appraisal.core.ruleengine.rule.dslparser;

import java.util.function.Function;

public interface Description<T> {
	public WhenCondition<T> desc(Function<T, String> desc);
}
