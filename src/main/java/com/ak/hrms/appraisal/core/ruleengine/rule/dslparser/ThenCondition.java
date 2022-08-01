package com.ak.hrms.appraisal.core.ruleengine.rule.dslparser;

import java.util.function.Consumer;

public interface ThenCondition<T> {

	BuildRule<T> then(Consumer<T> action);
}
