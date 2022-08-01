package com.ak.hrms.appraisal.core.ruleengine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ak.hrms.appraisal.core.ruleengine.rule.Rule;




public class RuleEngine<T> {

	private RulesHolder<T> rulesHolder = new RulesHolder<T>();
	private List<Rule<T>> allRules = new ArrayList<>();
	private List<Rule<T>> agendas = new ArrayList<>();

	public RuleEngine(RulesHolder<T> rulesHolder) {
		Assert.checkNonNull(rulesHolder, "rulesHolder should not be null");
		Assert.check(!rulesHolder.getAllRules().isEmpty(), "rulesHolder rules list should not be empty");
		this.rulesHolder.reload(rulesHolder.getAllRules());
		this.allRules.addAll(this.rulesHolder.getAllRules());
	}

	public void run(T type) {
		rulesOnAgenda(type);
		fireRulesOnAgenda(type);
	}

	private void fireRulesOnAgenda(T type) {
		agendas.stream().peek(rule -> System.out.println("Rule fired : " + rule.desc().apply(type) + "\n"))
				.forEach(rule -> rule.fire(type));
		agendas.clear();
	}

	private void rulesOnAgenda(T type) {
		allRules.stream().filter(rule -> rule.check(type))
				.peek(rule -> System.out.println("Rule on agenda : " + rule.desc().apply(type)))
				.collect(Collectors.toCollection(() -> agendas));
	}

}
