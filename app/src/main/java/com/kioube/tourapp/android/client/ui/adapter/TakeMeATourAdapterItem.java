package com.kioube.tourapp.android.client.ui.adapter;

import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * TakeMeATourAdapterItem type definition
 * @author Julien Mellerin
 *
 */
public class TakeMeATourAdapterItem {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private TourItem tourItem;
	private String period;
	
	/* --- Getters & setters --- */

	/**
	 * Gets the TakeMeATourAdapterItem object's tourITem value
	 *
	 * @return The TakeMeATourAdapterItem object's tourITem value
	 */
	public TourItem getTourItem() {
		return this.tourItem;
	}
	/**
	 * Gets the TakeMeATourAdapterItem object's period value
	 *
	 * @return The TakeMeATourAdapterItem object's period value
	 */
	public String getPeriod() {
		return this.period;
	}
	
	/* --- .ctors --- */

	/**
	 * Constructs a new TakeMeATourAdapterItem object.
	 * @param tourItem
	 * @param period
	 */
	public TakeMeATourAdapterItem(TourItem tourItem, String period) {
		super();
		
		this.tourItem = tourItem;
		this.period = period;
	}	
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
