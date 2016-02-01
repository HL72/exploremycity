package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;

import android.content.Context;
import android.util.Log;

import com.kioube.tourapp.android.client.domain.Theme;

/**
 * 
 * ThemeRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class ThemeRepository extends RepositoryBase<Theme> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = ThemeRepository.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new ThemeRepository object.
	 * 
	 * @param context
	 */
	public ThemeRepository(Context context) {
		super(context);
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getThemeDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the Theme DAO.", e);
		}
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
}
