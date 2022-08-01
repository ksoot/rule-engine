package com.ak.hrms.appraisal.domain.ruleengine;

import java.time.temporal.TemporalAdjusters;
import java.util.function.Predicate;

import com.ak.hrms.appraisal.core.ruleengine.AbstractRuleEngineBuilder;
import com.ak.hrms.appraisal.core.ruleengine.RulesHolder;
import com.ak.hrms.appraisal.model.Appraisal;
import com.ak.hrms.appraisal.model.Employee;

public class AppraisalRuleEngineBuilder extends AbstractRuleEngineBuilder<Employee> {

	@Override
	protected RulesHolder<Employee> buildRules() {
		RulesHolder<Employee> rulesHolder =
			new AppraisalRuleBuilder().
				rule().
				desc(employee -> "When Notice period == "+employee.isServingNotice()+", Then appraisal not applicable")
				.when(employee -> employee.isServingNotice()).then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setComment("Employee on notice. Hence not eligible for appraisal");
				}).build().rule().desc(employee -> "When Tenure < 1 year ("+employee.getTenure()+" days) && rating < 3 (rating is "+employee.getAppraisal().get().getRating()+ "), Then training required")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> !employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() < 3))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setTrainingRequired(true);
				}).build().rule().desc(employee -> "When Tenure < 1 year ("+employee.getTenure()+" days) && rating > 3 (rating is "+employee.getAppraisal().get().getRating()+ "), Then appicable for 10000 < bonus < 100000")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> !employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() > 3))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setBonusApplicable(true);
					a.setBonus(10000 + (Math.random() * (100000 - 10000)));
				}).build().rule().desc(employee -> "When Tenure > 1 year ("+employee.getTenure()+" days) && rating < 3 (rating is "+employee.getAppraisal().get().getRating()+ "), Then PIP or Trainings based on manager comment")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() < 3))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					if (a.getComment().contains("PIP")) {
						a.setPip(true);
					} else {
						a.setTrainingRequired(true);
					}

				}).build().rule().desc(employee -> "When Tenure > 1 year ("+employee.getTenure()+" days) && rating == 3 (rating is "+employee.getAppraisal().get().getRating()+ "), Then hike == 5")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() == 3))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setHikeApplicable(true);
					a.setHike(5);
					a.setAppraisalEffectiveDate(a.getCycle().with(TemporalAdjusters.firstDayOfNextMonth()));
					employee.applyRating();
				}).build().rule().desc(employee -> "When Tenure > 1 year ("+employee.getTenure()+" days) && rating == 4 (rating is "+employee.getAppraisal().get().getRating()+ "), Then hike == 7")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() == 4))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setHikeApplicable(true);
					a.setHike(7);
					a.setAppraisalEffectiveDate(a.getCycle().with(TemporalAdjusters.firstDayOfNextMonth()));
					employee.applyRating();
				}).build().rule().desc(employee -> "When Tenure > 1 year ("+employee.getTenure()+" days) && rating == 5 (rating is "+employee.getAppraisal().get().getRating()+ "), Then hike == 10")
				.when(((Predicate<Employee>) employee -> !employee.isServingNotice())
						.and(employee -> employee.tenureGrtOneYear())
						.and(employee -> employee.getAppraisal().get().getRating() == 5))
				.then(employee -> {
					Appraisal a = employee.getAppraisal().get();
					a.setHikeApplicable(true);
					a.setHike(10);
					a.setAppraisalEffectiveDate(a.getCycle().with(TemporalAdjusters.firstDayOfNextMonth()));
					employee.applyRating();
				}).build().done();
		return rulesHolder;
	}

	
}
