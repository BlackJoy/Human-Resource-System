package com.sys.hr.wageitem;

/**
 * WageTypeRelation entity. @author MyEclipse Persistence Tools
 */

public class WageTypeRelation implements java.io.Serializable {

	// Fields

	private WageTypeRelationId id;

	// Constructors

	/** default constructor */
	public WageTypeRelation() {
	}

	/** full constructor */
	public WageTypeRelation(WageTypeRelationId id) {
		this.id = id;
	}

	// Property accessors

	public WageTypeRelationId getId() {
		return this.id;
	}

	public void setId(WageTypeRelationId id) {
		this.id = id;
	}

}