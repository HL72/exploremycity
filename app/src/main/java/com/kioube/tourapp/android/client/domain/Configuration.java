package com.kioube.tourapp.android.client.domain;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * Configuration type definition
 * This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "Configuration")
public class Configuration {
	
	/* --- Constants --- */
	
	public final static String KEY_FIELD_NAME = "key";
	
	/* --- Fields --- */

	@DatabaseField(generatedId = true)
	private int id;
	
	@Attribute
	@DatabaseField(unique = true, columnName = KEY_FIELD_NAME)
	private String key;

	@Text
	@DatabaseField
	private String value;
	
	/* --- Accessors --- */
	
	/**
	 * Gets the Configuration object's id value
	 *
	 * @return The Configuration object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the Configuration object's key value
	 * 
	 * @return The Configuration object's key value
	 */
	public String getKey() {
		return this.key;
	}
	
	/**
	 * Sets the Configuration object's key value
	 * 
	 * @param key The Configuration object's key value to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * Gets the Configuration object's value value
	 * 
	 * @return The Configuration object's value value
	 */
	public String getValue() {
		return this.value;
	}
	
	/**
	 * Sets the Configuration object's value value
	 * 
	 * @param value The Configuration object's value value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new Configuration object.
	 */
	public Configuration() {
		super();
	}
	
	/**
	 * Constructs a new Configuration object.
	 * 
	 * @param key
	 * @param value
	 */
	public Configuration(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}
	
}
