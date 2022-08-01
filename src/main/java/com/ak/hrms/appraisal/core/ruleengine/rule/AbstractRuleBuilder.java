package com.ak.hrms.appraisal.core.ruleengine.rule;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import com.ak.hrms.appraisal.core.ruleengine.RulesHolder;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.BuildRule;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.Description;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.SetRule;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.ThenCondition;
import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.WhenCondition;

public abstract class AbstractRuleBuilder<T> implements SetRule<T>, WhenCondition<T>, ThenCondition<T>, BuildRule<T>, Description<T> {

	private RulesHolder<T> rulesHolder;
	private Predicate<T> condition;
	private Consumer<T> action;
	private Function<T, String> description;

	public AbstractRuleBuilder() {
		this.rulesHolder = new RulesHolder<T>();
	}

	@Override
	public RulesHolder<T> done() {
		return rulesHolder;
	}

	@Override
	public ThenCondition<T> when(Predicate<T> condition) {
		this.condition = condition;
		return this;
	}

	@Override
	public BuildRule<T> then(Consumer<T> action) {
		this.action = action;
		return this;
	}

	
	@Override
	public WhenCondition<T> desc(Function<T, String> desc) {
		this.description = desc;
		return this;
	}

	@Override
	public Description<T> rule() {
		return this;
	}
	
	public Function<T, String> getDescription() {
		return description;
	}
	
	public Predicate<T> getCondition() {
		return condition;
	}
	
	public Consumer<T> getAction() {
		return action;
	}

	public RulesHolder<T> getRulesHolder() {
		return rulesHolder;
	}
}
