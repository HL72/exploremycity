package com.kioube.tourapp.android.client.domain;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Theme type definition
 * This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "Theme")
public class Theme extends SynchronizableEntityBase {
	
	/* --- Constants --- */
	private static final long serialVersionUID = 1519117979517673741L;
	
	/* --- Fields --- */
	
	@Attribute
	@DatabaseField(id = true, generatedId = false, canBeNull = false)
	private int id;
	
	@Element(required = false)
	@DatabaseField
	private String name;

	@Element(required = false, data = true)
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String image;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the Theme object's id value
	 * 
	 * @return The Theme object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the Theme object's id value
	 * 
	 * @param id The Theme object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the Theme object's name value
	 * 
	 * @return The Theme object's name value
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the Theme object's name value
	 * 
	 * @param name The Theme object's name value to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the Theme object's image value
	 * 
	 * @return The Theme object's image value
	 */
	public String getImage() {
		return this.image;
	}
	
	/**
	 * Sets the Theme object's image value
	 * 
	 * @param image The Theme object's image value to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new Theme object.
	 */
	public Theme() {
		super();
	}
	
	/**
	 * Constructs a new Theme object.
	 * 
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public Theme(Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
	}
	
	/**
	 * Constructs a new Theme object.
	 * 
	 * @param id
	 * @param name
	 * @param image
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public Theme(int id, String name, String image, Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
		this.id = id;
		this.name = name;
		this.image = image;
	}
	
}
