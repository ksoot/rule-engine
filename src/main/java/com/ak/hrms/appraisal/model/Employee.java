package com.ak.hrms.appraisal.model;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.Optional;

public class Employee {

	private String empId;
	private String mgrId;
	private String firstName;
	private String lastName;
	private String email;
	private Optional<Appraisal> appraisal;
	private LocalDate doj;
	private boolean servingNotice;
	private Gender gender;
	private double ctc;

	private Employee(EmployeeBuilder builder) {
		this.empId = builder.empId;
		this.mgrId = builder.mgrId;
		this.doj = builder.doj;
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.email = builder.email;
		this.doj = builder.doj;
		this.gender = builder.gender;
		this.ctc = builder.ctc;
	}

	public void applyRating() {
		double amountIncreased = 0;
		if (appraisal.isPresent()) {
			amountIncreased = ctc * (appraisal.get().getHike()) / 100;
		}
		double newCtc = ctc + amountIncreased;
		ctc = newCtc;
	}

	public long getTenure() {
		return ChronoUnit.DAYS.between(doj, LocalDate.now());
	}

	public boolean tenureGrtOneYear() {
		return (ChronoUnit.DAYS.between(doj, LocalDate.now()) > Year.now().length());
	}

	public Gender getGender() {
		return gender;
	}

	public double getCtc() {
		return ctc;
	}

	public String getEmpId() {
		return empId;
	}

	public String getMgrId() {
		return mgrId;
	}

	// useful when manager changes
	public void setMgrId(String mgrId) {
		this.mgrId = mgrId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getEmail() {
		return email;
	}

	// useful when email changes like after merger etc
	public void setEmail(String email) {
		this.email = email;
	}

	public Optional<Appraisal> getAppraisal() {
		return appraisal;
	}

	public void setAppraisal(Optional<Appraisal> appraisal) {
		this.appraisal = appraisal;
	}

	public LocalDate getDoj() {
		return doj;
	}

	public boolean isServingNotice() {
		return servingNotice;
	}

	// useful when employee resign
	public void setServingNotice(boolean servingNotice) {
		this.servingNotice = servingNotice;
	}

	@Override
	public int hashCode() {
		return Objects.hash(doj, empId, firstName, gender, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(doj, other.doj) && Objects.equals(email, other.email)
				&& Objects.equals(empId, other.empId) && Objects.equals(firstName, other.firstName)
				&& gender == other.gender && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", name=" + firstName + ", doj=" + doj + ", servingNotice=" + servingNotice
				+ ", tenure=" + getTenure() + ", term=" + tenureGrtOneYear() + ", ctc=" + ctc + "\n" + appraisal + "]";
	}

// *********************************The fluent DSL builder for employee with fluent interface**************************************//	

	public static class EmployeeBuilder implements Name, GenderType, Email, CTC, Builder {
		private String empId;
		private String mgrId;
		private String firstName;
		private String lastName;
		private String email;
		private Gender gender;
		private double ctc;
		private LocalDate doj;

		private EmployeeBuilder(String empId, String mgrId, LocalDate doj) {
			this.empId = empId;
			this.mgrId = mgrId;
			this.doj = doj;
		}

		public static Name getBuilder(String empId, String mgrId, LocalDate doj) {
			return new EmployeeBuilder(empId, mgrId, doj);
		}

		@Override
		public Employee build() {
			return new Employee(this);
		}

		@Override
		public Builder ctc(double ctc) {
			this.ctc = ctc;
			return this;
		}

		@Override
		public GenderType email(String email) {
			this.email = email;
			return this;
		}

		@Override
		public CTC gender(Gender gender) {
			this.gender = gender;
			return this;
		}

		@Override
		public Name firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		@Override
		public Email lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

	}
}
