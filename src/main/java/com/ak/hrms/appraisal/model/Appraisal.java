package com.ak.hrms.appraisal.model;

import java.time.LocalDate;

import com.sun.tools.javac.util.Assert;

public class Appraisal {
	
	private LocalDate cycle;
	private byte rating;
	private boolean ratingSet; // useful to remind manager to close rating
	private float hike;
	private boolean hikeApplicable; // useful to prevent wrong state of appraisal
	private String comment;
	private boolean bonusApplicable; // useful to prevent wrong state of appraisal
	private double bonus;
	private boolean trainingRequired;
	private boolean pip;
	private LocalDate appraisalEffectiveDate;
	private boolean discussionClosed;

	public Appraisal(LocalDate cycle) {
		this.cycle = cycle;
	}
	
	public boolean isDiscussionClosed() {
		return discussionClosed;
	}
	
	public void setDiscussionClosed(boolean discussionClosed) {
		this.discussionClosed = discussionClosed;
	}

	public void setAppraisalEffectiveDate(LocalDate appraisalEffectiveDate) {
		this.appraisalEffectiveDate = appraisalEffectiveDate;
	}

	public LocalDate getAppraisalEffectiveDate() {
		return appraisalEffectiveDate;
	}

	public void setTrainingRequired(boolean trainingRequired) {
		this.trainingRequired = trainingRequired;
	}

	public void setPip(boolean pip) {
		this.pip = pip;
	}

	public boolean isBonusApplicable() {
		return bonusApplicable;
	}

	public void setBonusApplicable(boolean bonusApplicable) {
		this.bonusApplicable = bonusApplicable;
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		Assert.check(bonusApplicable);
		this.bonus = bonus;
	}

	public void setCycle(LocalDate cycle) {
		this.cycle = cycle;
	}

	public LocalDate getCycle() {
		return cycle;
	}

	public byte getRating() {
		return rating;
	}

	public void setRating(byte rating) {
		this.rating = rating;
		this.ratingSet = true;
	}

	public float getHike() {
		return hike;
	}

	public void setHike(float hike) {
		Assert.check(hikeApplicable);
		Assert.check(!trainingRequired);
		Assert.check(!pip);

		this.hike = hike;
	}

	public void setHikeApplicable(boolean hikeApplicable) {
		this.hikeApplicable = hikeApplicable;
	}

	public boolean isHikeApplicable() {
		return hikeApplicable;
	}

	public boolean isTrainingRequired() {
		return trainingRequired;
	}

	public boolean isPip() {
		return pip;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isRatingSet() {
		return ratingSet;
	}

	public void setRatingSet(boolean ratingSet) {
		this.ratingSet = ratingSet;
	}

	@Override
	public String toString() {
		
		return "Appraisal [cycle=" + cycle + ", rating=" + rating + ", ratingSet=" + ratingSet + ", hike=" + hike
				+ ", hikeApplicable=" + hikeApplicable + ", comment=" + comment + ", bonusApplicable=" + bonusApplicable
				+ ", bonus=" + bonus + ", trainingRequired=" + trainingRequired + ", pip=" + pip
				+ ", appraisalEffectiveDate=" + appraisalEffectiveDate + "]";
	}

	
}
