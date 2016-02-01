package com.kioube.tourapp.android.client.service.listener;

import com.kioube.tourapp.android.client.service.response.TakeMeATourResponse;

/**
 * 
 * TakeMeATourServiceListener type definition
 * 
 * @author Julien Mellerin
 * 
 */
public abstract class TakeMeATourServiceListener {
	
	/* --- Constants --- */
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	public abstract void onCompleted(TakeMeATourResponse response);
	
	public abstract void onError(Exception exception);
	
}
