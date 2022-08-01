package com.ak.hrms.appraisal.core.ruleengine.rule.dslparser;

public interface BuildRule<T> {

	SetRule<T> build();
}
