package com.kioube.tourapp.android.client.service.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 
 * TakeMeATourItem type definition
 * 
 * @author Julien Mellerin
 * 
 */
@Root(strict = false)
public class TakeMeATourResponseItem {
	
	@Element(name="id")
	private int id;
	
	/**
	 * Gets the TakeMeATourResponse.TakeMeATourItem object's id value
	 * 
	 * @return The TakeMeATourResponse.TakeMeATourItem object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the TakeMeATourResponse.TakeMeATourItem object's id value
	 * 
	 * @param id The TakeMeATourResponse.TakeMeATourItem object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	public TakeMeATourResponseItem() {
		
	}
}
