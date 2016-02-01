package com.kioube.tourapp.android.client.ui.filter;

import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;

/**
 * 
 * TourItemListFilter type definition. Used as TourItemListFragment input to filter TourItemList results.
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemListFilter extends FilterBase {
	
	/* --- Constants --- */
	
	private static final long serialVersionUID = -7362642254805870677L;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private GeographicalArea geographicalArea;
	private Theme theme;
	private Boolean bookmarksOnly = false;
	private String keyword;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the TourItemListFilter object's keyword value
	 *
	 * @return The TourItemListFilter object's keyword value
	 */
	public String getKeyword() {
		return this.keyword;
	}

	/**
	 * Sets the TourItemListFilter object's keyword value
	 * @param keyword The TourItemListFilter object's keyword value to set
	 */
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * Gets the TourItemListFilter object's bookmarksOnly value
	 *
	 * @return The TourItemListFilter object's bookmarksOnly value
	 */
	public Boolean getBookmarksOnly() {
		return this.bookmarksOnly;
	}

	/**
	 * Gets the TourItemListFilter object's geographicalArea value
	 * 
	 * @return The TourItemListFilter object's geographicalArea value
	 */
	public GeographicalArea getGeographicalArea() {
		return this.geographicalArea;
	}
	
	/**
	 * Gets the TourItemListFilter object's theme value
	 * 
	 * @return The TourItemListFilter object's theme value
	 */
	public Theme getTheme() {
		return this.theme;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemListFilter object.
	 * 
	 * @param geographicalArea
	 * @param theme
	 */
	public TourItemListFilter(GeographicalArea geographicalArea, Theme theme) {
		this(geographicalArea, theme, null);
	}
	
	/**
	 * Constructs a new TourItemListFilter object.
	 * 
	 * @param geographicalArea
	 * @param theme
	 */
	public TourItemListFilter(GeographicalArea geographicalArea, Theme theme, String keyword) {
		super();
		
		this.geographicalArea = geographicalArea;
		this.theme = theme;
		this.keyword = keyword;
	}
	
	/**
	 * Constructs a new TourItemListFilter object.
	 * 
	 * @param geographicalArea
	 * @param theme
	 */
	public TourItemListFilter(Boolean bookmarksOnly) {
		super();
		
		this.bookmarksOnly = bookmarksOnly;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
