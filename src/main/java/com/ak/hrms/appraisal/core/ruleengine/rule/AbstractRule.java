package com.ak.hrms.appraisal.core.ruleengine.rule;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public abstract class AbstractRule<T> implements Rule<T> {

	private Predicate<T> condition;
	private Consumer<T> action;
	private Function<T, String> description;

	public AbstractRule(Function<T, String> description, Predicate<T> condition, Consumer<T> action) {
		this.description = description;
		this.condition = condition;
		this.action = action;
	}

	@Override
	public boolean check(T type) {
		return condition.test(type);
	}

	@Override
	public void fire(T type) {
		action.accept(type);
	}

	@Override
	public Function<T, String> desc() {
		return description;
	}

	@Override
	public Predicate<T> condition() {
		return condition;
	}

	@Override
	public Consumer<T> action() {
		return action;
	}

}
