package com.kioube.tourapp.android.client.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Message type definition
 * 
 * @author Julien Mellerin
 */
@Root(name="models.Message")
public class Message {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	@Element(required = true)
	private int id;
	
	@Element(required = true)
	private String subject;
	
	@Element(required = true)
	private String text;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the Message object's id value
	 * 
	 * @return The Message object's id value
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Sets the Message object's id value
	 * 
	 * @param id The Message object's id value to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Gets the Message object's subject value
	 * 
	 * @return The Message object's subject value
	 */
	public String getSubject() {
		return this.subject;
	}
	
	/**
	 * Sets the Message object's subject value
	 * 
	 * @param subject The Message object's subject value to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	/**
	 * Gets the Message object's text value
	 * 
	 * @return The Message object's text value
	 */
	public String getText() {
		return this.text;
	}
	
	/**
	 * Sets the Message object's text value
	 * 
	 * @param text The Message object's text value to set
	 */
	public void setText(String text) {
		this.text = text;
	}
	
	/* --- .ctors --- */
	
	/**
	 * 
	 * Constructs a new Message object.
	 */
	public Message() {
		
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
