package com.kioube.tourapp.android.client.domain;

import java.io.Serializable;
import java.util.Date;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.j256.ormlite.field.DatabaseField;

/**
 * 
 * EntityBase type definition
 * This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
public abstract class SynchronizableEntityBase implements Serializable {

	/* --- Constants --- */
	
	private static final long serialVersionUID = 1356943971990656114L;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	@Element(required = false)
	@DatabaseField
	private Date createdOn;
	
	@Element(required = false)
	@DatabaseField
	private Date updatedOn;
	
	@Element(required = false)
	@DatabaseField
	private Date deletedOn;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the EntityBase object's createdOn value
	 * 
	 * @return The EntityBase object's createdOn value
	 */
	public Date getCreatedOn() {
		return this.createdOn;
	}
	
	/**
	 * Sets the EntityBase object's createdOn value
	 * 
	 * @param createdOn The EntityBase object's createdOn value to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	/**
	 * Gets the EntityBase object's updatedOn value
	 * 
	 * @return The EntityBase object's updatedOn value
	 */
	public Date getUpdatedOn() {
		return this.updatedOn;
	}
	
	/**
	 * Sets the EntityBase object's updatedOn value
	 * 
	 * @param updatedOn The EntityBase object's updatedOn value to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}
	
	/**
	 * Gets the EntityBase object's deletedOn value
	 * 
	 * @return The EntityBase object's deletedOn value
	 */
	public Date getDeletedOn() {
		return this.deletedOn;
	}
	
	/**
	 * Sets the EntityBase object's deletedOn value
	 * 
	 * @param deletedOn The EntityBase object's deletedOn value to set
	 */
	public void setDeletedOn(Date deletedOn) {
		this.deletedOn = deletedOn;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new SynchronizableEntityBase object.
	 */
	public SynchronizableEntityBase() {
		super();
	}
	
	/**
	 * Constructs a new SynchronizableEntityBase object.
	 * 
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public SynchronizableEntityBase(Date createdOn, Date updatedOn, Date deletedOn) {
		super();
		this.createdOn = createdOn;
		this.updatedOn = updatedOn;
		this.deletedOn = deletedOn;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
