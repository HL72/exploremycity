package com.kioube.tourapp.android.client.domain;

import java.util.Collection;
import java.util.Date;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 
 * TourItem type definition
 * This model is configured to be persistent and XML serializable using Simple-Xml
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
@DatabaseTable(tableName = "TourItem")
public class TourItem extends SynchronizableEntityBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = TourItem.class.getSimpleName();

	private static final long serialVersionUID = 7906792549087652822L;
	public final static String IS_BOOKMARKED_FIELD_NAME = "isBookmarked";
	public final static String GEOGRAPHICAL_AREA_ID_FIELD_NAME = "geographicalAreaId";
	public final static String THEME_ID_FIELD_NAME = "themeId";
	public final static String NAME_FIELD_NAME = "Name";
	
	/* --- Fields --- */
	
	@Attribute
	@DatabaseField(id = true, generatedId = false, canBeNull = false)
	private int id;
	
	@Element(name = "GeographicalArea", required = false)
	@DatabaseField(foreign = true, columnName = GEOGRAPHICAL_AREA_ID_FIELD_NAME, columnDefinition = "integer references GeographicalArea(id) on delete cascade")
	private GeographicalArea geographicalArea;

	@Element(name = "Theme", required = false)
	@DatabaseField(foreign = true, columnName = THEME_ID_FIELD_NAME, columnDefinition = "integer references Theme(id) on delete cascade")
	private Theme theme;

	@Element(required = false)
	@DatabaseField(columnName = NAME_FIELD_NAME)
	private String name;

	@Element(required = false)
	@DatabaseField
	private String description;

	@Element(required = false)
	@DatabaseField
	private String summary;
	
	@ElementList(name = "ImageList", type = TourItemImage.class, required = false, entry = "Image")
	@ForeignCollectionField
	private Collection<TourItemImage> imageList;
	
	@DatabaseField(columnName = IS_BOOKMARKED_FIELD_NAME)
	private boolean isBookmarked;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the TourItem object's id value
	 * 
	 * @return The TourItem object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Gets the TourItem object's isBookmarked value
	 *
	 * @return The TourItem object's isBookmarked value
	 */
	public boolean isBookmarked() {
		return this.isBookmarked;
	}

	/**
	 * Sets the TourItem object's isBookmarked value
	 * @param isBookmarked The TourItem object's isBookmarked value to set
	 */
	public void setBookmarked(boolean isBookmarked) {
		this.isBookmarked = isBookmarked;
	}

	/**
	 * Sets the TourItem object's id value
	 * 
	 * @param id The TourItem object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the TourItem object's geographicalArea value
	 * 
	 * @return The TourItem object's geographicalArea value
	 */
	public GeographicalArea getGeographicalArea() {
		return this.geographicalArea;
	}
	
	/**
	 * Sets the TourItem object's geographicalArea value
	 * 
	 * @param geographicalArea The TourItem object's geographicalArea value to set
	 */
	public void setGeographicalArea(GeographicalArea geographicalArea) {
		this.geographicalArea = geographicalArea;
	}
	
	/**
	 * Gets the TourItem object's theme value
	 * 
	 * @return The TourItem object's theme value
	 */
	public Theme getTheme() {
		return this.theme;
	}
	
	/**
	 * Sets the TourItem object's theme value
	 * 
	 * @param theme The TourItem object's theme value to set
	 */
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	
	/**
	 * Gets the TourItem object's name value
	 * 
	 * @return The TourItem object's name value
	 */
	public String getName() {
		return this.name;
	}
	
	/**
	 * Sets the TourItem object's name value
	 * 
	 * @param name The TourItem object's name value to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the TourItem object's description value
	 * 
	 * @return The TourItem object's description value
	 */
	public String getDescription() {
		return this.description;
	}
	
	/**
	 * Sets the TourItem object's description value
	 * 
	 * @param description The TourItem object's description value to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the TourItem object's summary value
	 * 
	 * @return The TourItem object's summary value
	 */
	public String getSummary() {
		return this.summary;
	}
	
	/**
	 * Sets the TourItem object's summary value
	 * 
	 * @param summary The TourItem object's summary value to set
	 */
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	/**
	 * Gets the TourItem object's imageList value
	 * 
	 * @return The TourItem object's imageList value
	 */
	public Collection<TourItemImage> getImageList() {
		return this.imageList;
	}
	
	/**
	 * Sets the TourItem object's imageList value
	 * 
	 * @param imageList The TourItem object's imageList value to set
	 */
	public void setImageList(Collection<TourItemImage> imageList) {
		this.imageList = imageList;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItem object.
	 */
	public TourItem() {
		super();
	}
	
	/**
	 * Constructs a new TourItem object.
	 * 
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public TourItem(Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
	}
	
	/**
	 * Constructs a new TourItem object.
	 * 
	 * @param id
	 * @param geographicalArea
	 * @param theme
	 * @param name
	 * @param description
	 * @param summary
	 * @param imageList
	 * @param createdOn
	 * @param updatedOn
	 * @param deletedOn
	 */
	public TourItem(int id, GeographicalArea geographicalArea, Theme theme, String name, String description, String summary, Collection<TourItemImage> imageList, Date createdOn, Date updatedOn, Date deletedOn) {
		super(createdOn, updatedOn, deletedOn);
		
		this.id = id;
		this.geographicalArea = geographicalArea;
		this.theme = theme;
		this.name = name;
		this.description = description;
		this.summary = summary;
		this.imageList = imageList;
	}	
}
