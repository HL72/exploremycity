package com.kioube.tourapp.android.client.service.response;

import java.util.List;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.kioube.tourapp.android.client.domain.Message;

/**
 * 
 * MessageResponse type definition
 * 
 * @author Julien Mellerin
 * 
 */
@Root(name = "list")
public class MessageResponse {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	@ElementList(inline = true)
	private List<Message> list;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the MessageResponse object's list value
	 * 
	 * @return The MessageResponse object's list value
	 */
	public List<Message> getList() {
		return this.list;
	}
	
	/**
	 * Sets the MessageResponse object's list value
	 * 
	 * @param list The MessageResponse object's list value to set
	 */
	public void setList(List<Message> list) {
		this.list = list;
	}
	
	/* --- .ctors --- */
	
	/**
	 * 
	 * Constructs a new MessageResponse object.
	 */
	public MessageResponse() {
		
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
