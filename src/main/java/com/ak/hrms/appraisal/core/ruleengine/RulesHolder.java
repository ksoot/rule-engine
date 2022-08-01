package com.ak.hrms.appraisal.core.ruleengine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.ak.hrms.appraisal.core.ruleengine.rule.Rule;

public class RulesHolder<T> {

	private List<Rule<T>> allRules = new ArrayList<>();

	public void addRule(Rule<T> rule) {
		allRules.add(rule);
	}

	public List<Rule<T>> getAllRules() {
		return Collections.unmodifiableList(allRules);
	}

	public void reload(List<Rule<T>> newRules) {
		if (!allRules.isEmpty()) {
			clear();
		}
		allRules.addAll(newRules);
	}

	private void clear() {
		allRules.clear();
	}
}
