package com.kioube.tourapp.android.client.service.response;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * 
 * TakeMeATourResponse type definition
 * 
 * @author Julien Mellerin
 * 
 */
@Root
public class TakeMeATourResponse {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */

	@Element(name="id")
	private int id;

	@Element(name="morningItem")
	private TakeMeATourResponseItem morningItem;

	@Element(name="afternoonItem")
	private TakeMeATourResponseItem afternoonItem;

	@Element(name="nightItem")
	private TakeMeATourResponseItem nightItem;
	
	/* --- Getters & setters --- */

	/**
	 * Gets the TakeMeATourResponse object's id value
	 *
	 * @return The TakeMeATourResponse object's id value
	 */
	public int getId() {
		return this.id;
	}

	/**
	 * Sets the TakeMeATourResponse object's id value
	 * @param id The TakeMeATourResponse object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Gets the TakeMeATourResponse object's morningItem value
	 *
	 * @return The TakeMeATourResponse object's morningItem value
	 */
	public TakeMeATourResponseItem getMorningItem() {
		return this.morningItem;
	}

	/**
	 * Sets the TakeMeATourResponse object's morningItem value
	 * @param morningItem The TakeMeATourResponse object's morningItem value to set
	 */
	public void setMorningItem(TakeMeATourResponseItem morningItem) {
		this.morningItem = morningItem;
	}

	/**
	 * Gets the TakeMeATourResponse object's afternoonItem value
	 *
	 * @return The TakeMeATourResponse object's afternoonItem value
	 */
	public TakeMeATourResponseItem getAfternoonItem() {
		return this.afternoonItem;
	}

	/**
	 * Sets the TakeMeATourResponse object's afternoonItem value
	 * @param afternoonItem The TakeMeATourResponse object's afternoonItem value to set
	 */
	public void setAfternoonItem(TakeMeATourResponseItem afternoonItem) {
		this.afternoonItem = afternoonItem;
	}

	/**
	 * Gets the TakeMeATourResponse object's nightItem value
	 *
	 * @return The TakeMeATourResponse object's nightItem value
	 */
	public TakeMeATourResponseItem getNightItem() {
		return this.nightItem;
	}

	/**
	 * Sets the TakeMeATourResponse object's nightItem value
	 * @param nightItem The TakeMeATourResponse object's nightItem value to set
	 */
	public void setNightItem(TakeMeATourResponseItem nightItem) {
		this.nightItem = nightItem;
	}
	
	/* --- .ctors --- */
	
	/**
	 * 
	 * Constructs a new TakeMeATourResponse object.
	 */
	public TakeMeATourResponse() {
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
}
