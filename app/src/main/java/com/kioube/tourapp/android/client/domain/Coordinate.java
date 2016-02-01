package com.kioube.tourapp.android.client.domain;

import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Coordinate type definition This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "Coordinate")
public class Coordinate extends SynchronizableEntityBase {
	
	/* --- Constants --- */

	private static final long serialVersionUID = 8890953898396483630L;
	public final static String TOUR_ITEM_ID_FIELD_NAME = "tourItemId";
	
	/* --- Fields --- */
	
	@Attribute(required = true)
	@DatabaseField(id = true, generatedId = false, canBeNull = false)
	private int id;
	
	@Element(name = "TourItem", required = false)
	@DatabaseField(foreign = true, columnName = TOUR_ITEM_ID_FIELD_NAME, columnDefinition = "integer references TourItem(id) on delete cascade")
	private TourItem tourItem;
	
	@Element(required = false)
	@DatabaseField
	private String email;
	
	@Element(required = false)
	@DatabaseField
	private String address;
	
	@Element(required = false)
	@DatabaseField
	private String website;
	
	@Element(required = false)
	@DatabaseField
	private float longitude;
	
	@Element(required = false)
	@DatabaseField
	private float latitude;
	
	@Element(required = false)
	@DatabaseField
	private float phone;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the Coordinate object's id value
	 * 
	 * @return The Coordinate object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the Coordinate object's id value
	 * 
	 * @param id The Coordinate object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the Coordinate object's tourItem value
	 * 
	 * @return The Coordinate object's tourItem value
	 */
	public TourItem getTourItem() {
		return this.tourItem;
	}
	
	/**
	 * Sets the Coordinate object's tourItem value
	 * 
	 * @param tourItem The Coordinate object's tourItem value to set
	 */
	public void setTourItem(TourItem tourItem) {
		this.tourItem = tourItem;
	}
	
	/**
	 * Gets the Coordinate object's email value
	 * 
	 * @return The Coordinate object's email value
	 */
	public String getEmail() {
		return this.email;
	}
	
	/**
	 * Sets the Coordinate object's email value
	 * 
	 * @param email The Coordinate object's email value to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Gets the Coordinate object's address value
	 * 
	 * @return The Coordinate object's address value
	 */
	public String getAddress() {
		return this.address;
	}
	
	/**
	 * Sets the Coordinate object's address value
	 * 
	 * @param address The Coordinate object's address value to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	
	/**
	 * Gets the Coordinate object's website value
	 * 
	 * @return The Coordinate object's website value
	 */
	public String getWebsite() {
		return this.website;
	}
	
	/**
	 * Sets the Coordinate object's website value
	 * 
	 * @param website The Coordinate object's website value to set
	 */
	public void setWebsite(String website) {
		this.website = website;
	}
	
	/**
	 * Gets the Coordinate object's longitude value
	 * 
	 * @return The Coordinate object's longitude value
	 */
	public float getLongitude() {
		return this.longitude;
	}
	
	/**
	 * Sets the Coordinate object's longitude value
	 * 
	 * @param longitude The Coordinate object's longitude value to set
	 */
	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}
	
	/**
	 * Gets the Coordinate object's latitude value
	 * 
	 * @return The Coordinate object's latitude value
	 */
	public float getLatitude() {
		return this.latitude;
	}
	
	/**
	 * Sets the Coordinate object's latitude value
	 * 
	 * @param latitude The Coordinate object's latitude value to set
	 */
	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}
	
	/**
	 * Gets the Coordinate object's phone value
	 * 
	 * @return The Coordinate object's phone value
	 */
	public float getPhone() {
		return this.phone;
	}
	
	/**
	 * Sets the Coordinate object's phone value
	 * 
	 * @param phone The Coordinate object's phone value to set
	 */
	public void setPhone(float phone) {
		this.phone = phone;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new Coordinate object.
	 */
	public Coordinate() {
		super();
	}
	
	/**
	 * Constructs a new Coordinate object.
	 * 
	 * @param id
	 * @param tourItem
	 * @param email
	 * @param address
	 * @param website
	 * @param longitude
	 * @param latitude
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public Coordinate(int id, TourItem tourItem, String email, String address, String website, float longitude, float latitude, Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
		
		this.id = id;
		this.tourItem = tourItem;
		this.email = email;
		this.address = address;
		this.website = website;
		this.longitude = longitude;
		this.latitude = latitude;
	}
	
}