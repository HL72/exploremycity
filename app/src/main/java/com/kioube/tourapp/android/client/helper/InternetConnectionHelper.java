package com.kioube.tourapp.android.client.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class InternetConnectionHelper {
	
	/* --- Fields --- */
	private Context context;
	
	/* --- .ctors --- */
	
	/**
	 * 
	 * Constructs a new InternetConnectionHelper object.
	 * 
	 * @param context
	 */
	public InternetConnectionHelper(Context context) {
		this.context = context;
	}
	
	/* --- Object operations --- */
	
	/**
	 * Checks if the internet connection is available
	 * 
	 * @return true if the internet connection is available
	 */
	public boolean isInternetConnectionAvailable() {
		boolean result = false;
		
		// Checks connection mode first
		SessionManager sessionManager = new SessionManager(this.context);
		
		if (sessionManager.isConnectedMode()) {
			
			// Check internet connection through network info
			ConnectivityManager connectivity = (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null)
			{
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null) {
					for (int i = 0; i < info.length; i++) {
						if (info[i].getState() == NetworkInfo.State.CONNECTED)
						{
							result = true;
							break;
						}
					}
				}
			}
		}
		
		return result;
	}
}
