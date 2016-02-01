package com.kioube.tourapp.android.client.ui.filter;


/**
 * 
 * TourItemFilter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class SearchFilter extends FilterBase {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private static final long serialVersionUID = 941351118640373696L;
	private String keyword;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the SearchFilter object's keyword value
	 * 
	 * @return The SearchFilter object's keyword value
	 */
	public String getKeyword() {
		return this.keyword;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemFilter object.
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public SearchFilter(String keyword) {
		super();
		
		this.keyword = keyword;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
