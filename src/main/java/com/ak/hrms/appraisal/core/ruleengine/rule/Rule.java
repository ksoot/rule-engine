package com.ak.hrms.appraisal.core.ruleengine.rule;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Rule<T> {

	Function<T, String> desc();
	Predicate<T> condition();
	Consumer<T> action();
	boolean check(T type);
	void fire(T type);
	

}
