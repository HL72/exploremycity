package com.kioube.tourapp.android.client.service.listener;

import com.kioube.tourapp.android.client.service.response.MessageResponse;

/**
 * 
 * MessageServiceListener type definition
 * 
 * @author Julien Mellerin
 * 
 */
public abstract class MessageServiceListener {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	public abstract void onCompleted(MessageResponse response);
	
	public abstract void onError(Exception exception);
	
}
