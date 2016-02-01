package com.kioube.tourapp.android.client.ui.filter;

import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * TourItemFilter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemFilter extends FilterBase {
	
	/* --- Constants --- */
	
	private static final long serialVersionUID = -8772842855578166286L;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private TourItem tourItem;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the TourItemFilter object's tourItem value
	 * 
	 * @return The TourItemFilter object's tourItem value
	 */
	public TourItem getTourItem() {
		return this.tourItem;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemFilter object.
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public TourItemFilter(TourItem tourItem) {
		super();
		
		this.tourItem = tourItem;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
