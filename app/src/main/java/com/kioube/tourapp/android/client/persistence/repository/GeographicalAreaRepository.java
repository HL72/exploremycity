package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.kioube.tourapp.android.client.domain.GeographicalArea;

/**
 * 
 * GeographicalAreaRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class GeographicalAreaRepository extends RepositoryBase<GeographicalArea> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = GeographicalAreaRepository.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new GeographicalAreaRepository object.
	 * 
	 * @param context
	 */
	public GeographicalAreaRepository(Context context) {
		super(context);
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getGeographicalAreaDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the GeographicalArea DAO.", e);
		}
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
