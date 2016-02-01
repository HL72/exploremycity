package com.kioube.tourapp.android.client.domain;

import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Image type definition
 * This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "TourItemImage")
public class TourItemImage {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = TourItemImage.class.getSimpleName();	
	public static final String TOUR_ITEM_ID_FIELD_NAME = "tourItemId";
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	@DatabaseField(generatedId = true)
	private int id;
	
	@Text
	@DatabaseField(dataType = DataType.LONG_STRING)
	private String byteCode;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = TOUR_ITEM_ID_FIELD_NAME, columnDefinition = "integer references TourItem(id) on delete cascade")
	private TourItem tourItem;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the Image object's id value
	 * 
	 * @return The Image object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the Image object's id value
	 * 
	 * @param id The Image object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the Image object's byteCode value
	 * 
	 * @return The Image object's byteCode value
	 */
	public String getByteCode() {
		return this.byteCode;
	}
	
	/**
	 * Sets the Image object's byteCode value
	 * 
	 * @param byteCode The Image object's byteCode value to set
	 */
	public void setByteCode(String byteCode) {
		this.byteCode = byteCode;
	}
	
	/**
	 * Gets the Image object's tourItem value
	 * 
	 * @return The Image object's tourItem value
	 */
	public TourItem getTourItem() {
		return this.tourItem;
	}
	
	/**
	 * Sets the Image object's tourItem value
	 * 
	 * @param tourItem The Image object's tourItem value to set
	 */
	public void setTourItem(TourItem tourItem) {
		this.tourItem = tourItem;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new Image object.
	 */
	public TourItemImage() {
		super();
	}
	
	/**
	 * Constructs a new Image object.
	 * 
	 * @param id
	 * @param byteCode
	 */
	public TourItemImage(int id, String byteCode) {
		super();
		this.id = id;
		this.byteCode = byteCode;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
