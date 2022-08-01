package com.ak.hrms.appraisal.core.ruleengine;

import com.ak.hrms.appraisal.core.ruleengine.rule.dslparser.SetRule;

public abstract class AbstractRuleEngineBuilder<T> implements BuildEngine<T>{

	private RulesHolder<T> rulesHolder;

	public AbstractRuleEngineBuilder() {
		rulesHolder = buildRules();
	}
	
	protected abstract RulesHolder<T> buildRules();
	
	@Override
	public RuleEngine<T> build() {
		return new RuleEngine<T>(rulesHolder);
	}
}
