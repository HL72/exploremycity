package com.kioube.tourapp.android.client.service;

import java.io.InputStream;
import java.net.URL;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import android.content.Context;
import android.util.Log;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.service.listener.TakeMeATourServiceListener;
import com.kioube.tourapp.android.client.service.response.TakeMeATourResponse;

public class TakeMeATourService {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = TakeMeATourService.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private Context context;
	private TakeMeATourServiceListener listener;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the TakeMeATourService object's context value
	 * 
	 * @return The TakeMeATourService object's context value
	 */
	public Context getContext() {
		return this.context;
	}
	
	/**
	 * Gets the TakeMeATourService object's listener value
	 * 
	 * @return The TakeMeATourService object's listener value
	 */
	public TakeMeATourServiceListener getListener() {
		return this.listener;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TakeMeATourService object.
	 * 
	 * @param context Application context
	 * @param listener Service listener used to handle complete and error events
	 */
	public TakeMeATourService(Context context, TakeMeATourServiceListener listener) {
		super();
		
		this.context = context;
		this.listener = listener;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	/**
	 * Gets the remote service URL to read data from
	 * 
	 * @return The remote service to read data from
	 */
	private String getServiceUrl() {
		String result = null;
		
		// TODO [2014-03-18, JMEL] For developement purposes, remove this when services are developed
		boolean fakeServices = this.getContext().getResources().getBoolean(R.bool.useFakeServices);
		
		if (fakeServices) {
			Log.d(LOG_TAG, "Using fake TMT service because of lazy server side developer.");
			
			result = this.getContext().getString(R.string.fakeTakeMeATourService);
		}
		else {
			result = this.getContext().getString(R.string.serviceRoot) + "tmt.xml";
		}
		
		return result;
	}
	
	/**
	 * Runs the messages service
	 */
	public void run() {
		
		// Creates the service thread
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				String urlString = null;
				
				// Deserializes from URL
				Serializer serializer = new Persister(new AnnotationStrategy());
				
				TakeMeATourResponse response = null;
				
				try {

                    boolean localServices = TakeMeATourService.this.getContext().getResources().getBoolean(R.bool.useLocalFiles);
                    if(localServices){
                        String fileName = TakeMeATourService.this.getContext().getString(R.string.localTakeMeATourService);
                        InputStream stream = TakeMeATourService.this.getContext().getAssets().open(fileName);
                        response = serializer.read(TakeMeATourResponse.class, stream);
                    } else {
                        urlString = TakeMeATourService.this.getServiceUrl();

                        Log.d(LOG_TAG, "Loading take me a tour info from '" + urlString + "'.");

                        if (urlString != null) {
                            URL url = new URL(urlString);
                            InputStream stream = url.openStream();

                            response = serializer.read(TakeMeATourResponse.class, stream);
                        }
                    }


					
					// Runs the onCompleted event of the listener
					if (TakeMeATourService.this.getListener() != null) {
						TakeMeATourService.this.getListener().onCompleted(response);
					}
				}
				catch (Exception e) {
					
					// Sends back the exception
					if (TakeMeATourService.this.getListener() != null) {
						TakeMeATourService.this.getListener().onError(new Exception(
							"Failed to get tour infos from '" + urlString + "'.",
							e
						));
					}
				}
			}
		});
		
		// Starts the import process
		thread.start();
	}
	
}
