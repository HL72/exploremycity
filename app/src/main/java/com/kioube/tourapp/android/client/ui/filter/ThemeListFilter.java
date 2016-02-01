package com.kioube.tourapp.android.client.ui.filter;

import com.kioube.tourapp.android.client.domain.GeographicalArea;

/**
 * 
 * ThemeListFilter type definition. Used as ThemeListFragment input to filter ThemeList results.
 * 
 * @author Julien Mellerin
 *
 */
public class ThemeListFilter extends FilterBase {
	
	/* --- Constants --- */
	
	private static final long serialVersionUID = -7965755187773290827L;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private GeographicalArea geographicalArea;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the GeographicalAreaListFilter object's geographicalArea value
	 * 
	 * @return The GeographicalAreaListFilter object's geographicalArea value
	 */
	public GeographicalArea getGeographicalArea() {
		return this.geographicalArea;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new ThemeListFilter object.
	 * 
	 * @param geographicalArea GeographicalArea used to filter
	 */
	public ThemeListFilter(GeographicalArea geographicalArea) {
		super();
		
		this.geographicalArea = geographicalArea;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
