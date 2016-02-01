package com.kioube.tourapp.android.client.service.listener;

/**
 * 
 * ServiceListener type definition
 * 
 * @author Julien Mellerin
 * 
 */
public abstract class ServiceListener {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	public abstract void onCompleted();
	
	public abstract void onError(Exception exception);
	
}
