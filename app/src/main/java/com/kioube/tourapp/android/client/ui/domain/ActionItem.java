package com.kioube.tourapp.android.client.ui.domain;

/**
 * 
 * NavigationDrawerItem type definition. Used to build the NavigationDrawer content.
 * 
 * @author Julien Mellerin
 * 
 */
public class ActionItem {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private int imageResourceId;
	private String label;
	private Runnable runnable;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the NavigationDrawerItem object's runnable value
	 * 
	 * @return The NavigationDrawerItem object's runnable value
	 */
	public Runnable getRunnable() {
		return this.runnable;
	}
	
	/**
	 * Gets the NavigationDrawerItem object's imageResourceId value
	 * 
	 * @return The NavigationDrawerItem object's imageResourceId value
	 */
	public int getImageResourceId() {
		return this.imageResourceId;
	}
	
	/**
	 * Sets the NavigationDrawerItem object's imageResourceId value
	 * 
	 * @param imageResourceId The NavigationDrawerItem object's imageResourceId value to set
	 */
	public void setImageResourceId(int imageResourceId) {
		this.imageResourceId = imageResourceId;
	}
	
	/**
	 * Gets the NavigationDrawerItem object's label value
	 * 
	 * @return The NavigationDrawerItem object's label value
	 */
	public String getLabel() {
		return this.label;
	}
	
	/**
	 * Sets the NavigationDrawerItem object's label value
	 * 
	 * @param label The NavigationDrawerItem object's label value to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new NavigationDrawerItem object.
	 * 
	 * @param label
	 * @param imageResourceId
	 * @param runnable
	 */
	public ActionItem(String label, int imageResourceId, Runnable runnable) {
		super();
		
		this.label = label;
		this.imageResourceId = imageResourceId;
		this.runnable = runnable;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
