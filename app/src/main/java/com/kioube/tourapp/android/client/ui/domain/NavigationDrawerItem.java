package com.kioube.tourapp.android.client.ui.domain;

/**
 * 
 * NavigationDrawerItem type definition. Used to build the NavigationDrawer content.
 * 
 * @author Julien Mellerin
 * 
 */
public class NavigationDrawerItem {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private int count;
	private int imageResourceId;
	private String label;
	private String subLabel;
	private Runnable runnable;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the NavigationDrawerItem object's count value
	 * 
	 * @return The NavigationDrawerItem object's count value
	 */
	public int getCount() {
		return this.count;
	}
	
	/**
	 * Gets the NavigationDrawerItem object's runnable value
	 * 
	 * @return The NavigationDrawerItem object's runnable value
	 */
	public Runnable getRunnable() {
		return this.runnable;
	}
	
	/**
	 * Sets the NavigationDrawerItem object's count value
	 * 
	 * @param count The NavigationDrawerItem object's count value to set
	 */
	public void setCount(int count) {
		this.count = count;
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
	
	/**
	 * Gets the NavigationDrawerItem object's subLabel value
	 * 
	 * @return The NavigationDrawerItem object's subLabel value
	 */
	public String getSubLabel() {
		return this.subLabel;
	}
	
	/**
	 * Sets the NavigationDrawerItem object's subLabel value
	 * 
	 * @param subLabel The NavigationDrawerItem object's subLabel value to set
	 */
	public void setSubLabel(String subLabel) {
		this.subLabel = subLabel;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new NavigationDrawerItem object.
	 * 
	 * @param label
	 * @param imageResourceId
	 * @param runnable
	 */
	public NavigationDrawerItem(String label, int imageResourceId, Runnable runnable) {
		this(label, imageResourceId, null, Integer.MIN_VALUE, runnable);
	}
	
	/**
	 * Constructs a new NavigationDrawerItem object.
	 * 
	 * @param label
	 * @param imageResourceId
	 * @param count
	 * @param runnable
	 */
	public NavigationDrawerItem(String label, int imageResourceId, int count, Runnable runnable) {
		this(label, imageResourceId, null, count, runnable);
	}
	
	/**
	 * Constructs a new NavigationDrawerItem object.
	 * 
	 * @param label
	 * @param imageResourceId
	 * @param subLabel
	 * @param runnable
	 */
	public NavigationDrawerItem(String label, int imageResourceId, String subLabel, Runnable runnable) {
		this(label, imageResourceId, subLabel, Integer.MIN_VALUE, runnable);
	}
	
	/**
	 * Constructs a new NavigationDrawerItem object.
	 * 
	 * @param label
	 * @param imageResourceId
	 * @param subLabel
	 * @param count
	 * @param runnable
	 */
	public NavigationDrawerItem(String label, int imageResourceId, String subLabel, int count, Runnable runnable) {
		super();
		
		this.label = label;
		this.imageResourceId = imageResourceId;
		this.subLabel = subLabel;
		this.count = count;
		this.runnable = runnable;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
