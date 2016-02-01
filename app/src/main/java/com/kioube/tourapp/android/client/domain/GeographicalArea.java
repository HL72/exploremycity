package com.kioube.tourapp.android.client.domain;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.core.Commit;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * GeographicalArea type definition. This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "GeographicalArea")
public class GeographicalArea extends SynchronizableEntityBase {
	
	/* --- Constants --- */

	@SuppressWarnings("unused")
	private static final String LOG_TAG = GeographicalArea.class.getName();	
	private static final long serialVersionUID = -3779004443490421159L;
	
	/* --- Fields --- */	

	@Attribute
	@DatabaseField(id = true, generatedId = false, canBeNull = false)
	private int id;
	
	@Element(required = false)
	@DatabaseField
	private String name;
	
	@Element(required = false)
	@DatabaseField
	private String description;
	
	@Element(required = false)
	@DatabaseField
	private float longitude;
	
	@Element(required = false)
	@DatabaseField
	private float latitude;
	
	@Element(required = false, data = true)
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String image;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the GeographicalArea object's image value
	 * 
	 * @return The GeographicalArea object's image value
	 */
	public String getImage() {
		return this.image;
	}
	
	/**
	 * Sets the GeographicalArea object's image value
	 * 
	 * @param image The GeographicalArea object's image value to set
	 */
	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Gets the GeographicalArea object's id value
	 * 
	 * @return The GeographicalArea object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the GeographicalArea object's id value
	 * 
	 * @param id The GeographicalArea object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the GeographicalArea object's name value
	 * 
	 * @return The GeographicalArea object's name value
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the GeographicalArea object's name value
	 * 
	 * @param name The GeographicalArea object's name value to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the GeographicalArea object's description value
	 * 
	 * @return The GeographicalArea object's description value
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the GeographicalArea object's description value
	 * 
	 * @param description The GeographicalArea object's description value to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the GeographicalArea object's longitude value
	 * 
	 * @return The GeographicalArea object's longitude value
	 */
	public float getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Sets the GeographicalArea object's longitude value
	 * 
	 * @param longitude The GeographicalArea object's longitude value to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Gets the GeographicalArea object's latitude value
	 * 
	 * @return The GeographicalArea object's latitude value
	 */
	public float getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Sets the GeographicalArea object's latitude value
	 * 
	 * @param latitude The GeographicalArea object's latitude value to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new GeographicalArea object.
	 */
	public GeographicalArea() {
		super();
	}
	
	/**
	 * Constructs a new GeographicalArea object.
	 * 
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public GeographicalArea(Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
	}
	
	/**
	 * Constructs a new GeographicalArea object.
	 * 
	 * @param id
	 * @param name
	 * @param description
	 * @param longitude
	 * @param latitude
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public GeographicalArea(int id, String name, String description, float longitude, float latitude, Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
		
		this.id = id;
		this.name = name;
		this.description = description;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
	@Commit
	public void commit() {
		
		// TODO [2014-04-03, JMEL] Remove this test
		/*
		if (this.getImage() != null) {
			Log.d(LOG_TAG, "Import image length = " + this.getImage().length());
			Log.d(LOG_TAG, "Import image start = '" + this.getImage().substring(0, 10) + "'");
			Log.d(LOG_TAG, "Import image end = '" + this.getImage().substring(this.getImage().length() - 11, this.getImage().length() - 1) + "'");
		}
		*/
	}
	
}
