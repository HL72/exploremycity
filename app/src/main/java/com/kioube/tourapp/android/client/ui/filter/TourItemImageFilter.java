package com.kioube.tourapp.android.client.ui.filter;

import com.kioube.tourapp.android.client.domain.TourItemImage;

/**
 * 
 * TourItemFilter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemImageFilter extends FilterBase {
	
	/* --- Constants --- */

	private static final long serialVersionUID = 109156183711530538L;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private TourItemImage tourItemImage;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the TourItemFilter object's tourItemImage value
	 * 
	 * @return The TourItemFilter object's tourItemImage value
	 */
	public TourItemImage getTourItemImage() {
		return this.tourItemImage;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemFilter object.
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public TourItemImageFilter(TourItemImage tourItemImage) {
		super();
		
		this.tourItemImage = tourItemImage;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
