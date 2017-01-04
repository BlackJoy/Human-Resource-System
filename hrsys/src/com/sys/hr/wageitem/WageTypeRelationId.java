package com.sys.hr.wageitem;

/**
 * WageTypeRelationId entity. @author MyEclipse Persistence Tools
 */

public class WageTypeRelationId implements java.io.Serializable {

	// Fields

	private String wageId;
	private String wageTypeId;

	// Constructors

	/** default constructor */
	public WageTypeRelationId() {
	}

	/** full constructor */
	public WageTypeRelationId(String wageId, String wageTypeId) {
		this.wageId = wageId;
		this.wageTypeId = wageTypeId;
	}

	// Property accessors

	public String getWageId() {
		return this.wageId;
	}

	public void setWageId(String wageId) {
		this.wageId = wageId;
	}

	public String getWageTypeId() {
		return this.wageTypeId;
	}

	public void setWageTypeId(String wageTypeId) {
		this.wageTypeId = wageTypeId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof WageTypeRelationId))
			return false;
		WageTypeRelationId castOther = (WageTypeRelationId) other;

		return ((this.getWageId() == castOther.getWageId()) || (this
				.getWageId() != null && castOther.getWageId() != null && this
				.getWageId().equals(castOther.getWageId())))
				&& ((this.getWageTypeId() == castOther.getWageTypeId()) || (this
						.getWageTypeId() != null
						&& castOther.getWageTypeId() != null && this
						.getWageTypeId().equals(castOther.getWageTypeId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getWageId() == null ? 0 : this.getWageId().hashCode());
		result = 37
				* result
				+ (getWageTypeId() == null ? 0 : this.getWageTypeId()
						.hashCode());
		return result;
	}

}