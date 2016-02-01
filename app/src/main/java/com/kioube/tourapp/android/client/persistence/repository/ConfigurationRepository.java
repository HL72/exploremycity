package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.kioube.tourapp.android.client.domain.Configuration;

/**
 * 
 * ConfigurationRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class ConfigurationRepository extends RepositoryBase<Configuration> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = ConfigurationRepository.class.getSimpleName();
	
	protected static final String DEFAULT_BACKGROUND_COLOR = "#FFFFFF";
	protected static final String DEFAULT_FOREGROUND_COLOR = "#000000";
	
	protected static final String BACKGROUND_COLOR_KEY = "BACKGROUND_COLOR";
	protected static final String TITLE_COLOR_KEY = "TITLE_COLOR";
	protected static final String DESCRIPTION_COLOR_KEY = "DESCRIPTION_COLOR";
	protected static final String HEADER_BACKGROUND_COLOR_KEY = "HEADER_BACKGROUND_COLOR";
	protected static final String HEADER_FOREGROUND_COLOR_KEY = "HEADER_FOREGROUND_COLOR";
	protected static final String HEADLINE_KEY = "HEADLINE";
	protected static final String LOGO_KEY = "LOGO";
	protected static final String MAIN_PICTURE_KEY = "MAIN_PICTURE";	
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	Context context;
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new ConfigurationRepository object.
	 * 
	 * @param context
	 */
	public ConfigurationRepository(Context context) {
		super(context);
		
		this.context = context;
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getConfigurationDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the Configuration DAO.", e);
		}
	}
	
	/* --- Class operations --- */
	
	/**
	 * Gets a color from its configuration key
	 * 
	 * @param configurationKey Configuration key to search for
	 * @param defaultColor Default color to use in case of null result of parse failure
	 * @return A color
	 */
	private int getColor(String configurationKey, String defaultColor) {
		int result = Color.parseColor(defaultColor);
		Configuration configuration = this.getByKey(configurationKey);
		
		if ((configuration != null) && (!configuration.getValue().isEmpty())) {
			try {
				result = Color.parseColor(configuration.getValue());
			}
			catch (Exception e) {
				Log.e(LOG_TAG, "Failed to parse color, returning default color.", e);
			}
		}
		
		return result;		
	}
	
	/**
	 * Gets the configured background color
	 * 
	 * @return The configured background color
	 */
	public int getBackgroundColor() {
		return this.getColor(BACKGROUND_COLOR_KEY, DEFAULT_BACKGROUND_COLOR);
	}
	
	/**
	 * Gets the configured title color
	 * 
	 * @return The configured title color
	 */
	public int getTitleColor() {
		return this.getColor(TITLE_COLOR_KEY, DEFAULT_FOREGROUND_COLOR);
	}
	
	/**
	 * Gets the configured description color
	 * 
	 * @return The configured description color
	 */
	public int getDescriptionColor() {
		return this.getColor(DESCRIPTION_COLOR_KEY, DEFAULT_FOREGROUND_COLOR);
	}
	
	/**
	 * Gets the configured action bar background color
	 * 
	 * @return The configured action bar background color
	 */
	public int getActionBarBackgroundColor() {
		return this.getColor(HEADER_BACKGROUND_COLOR_KEY, DEFAULT_BACKGROUND_COLOR);
	}
	
	/**
	 * Gets the configured action bar foreground color
	 * 
	 * @return The configured action bar foreground color
	 */
	public int getActionBarForegroundColor() {
		return this.getColor(HEADER_FOREGROUND_COLOR_KEY, DEFAULT_FOREGROUND_COLOR);
	}
	
	/**
	 * Gets the configured headline
	 * 
	 * @return The configured headline
	 */
	public String getHeadline() {
		String result = null;
		Configuration configuration = this.getByKey(HEADLINE_KEY);
		
		if ((configuration != null) && (!configuration.getValue().isEmpty())) {
			result = configuration.getValue();
		}
		
		return result;
	}
	
	/**
	 * Gets the configured logo picture
	 * 
	 * @return The configured logo picture
	 */
	public String getLogo() {
		String result = null;
		Configuration configuration = this.getByKey(LOGO_KEY);
		
		if ((configuration != null) && (!configuration.getValue().isEmpty())) {
			result = configuration.getValue();
		}
		
		return result;
	}
	
	/**
	 * Gets the configured main picture
	 * 
	 * @return The configured main picture
	 */
	public String getMainPicture() {
		String result = null;
		Configuration configuration = this.getByKey(MAIN_PICTURE_KEY);
		
		if ((configuration != null) && (!configuration.getValue().isEmpty())) {
			result = configuration.getValue();
		}
		
		return result;
	}
	
	/**
	 * Gets a persistent Configuration entity by its key
	 * 
	 * @param key The key to find
	 * @return A persistent Configuration entity matching the key (or null)
	 */
	public Configuration getByKey(String key) {
		Configuration result = null;

		try {
			PreparedQuery<Configuration> query = this.getDao().queryBuilder()
				.where()
				.eq(Configuration.KEY_FIELD_NAME, key)
				.prepare();
			
			List<Configuration> resultList = this.getDao().query(query);
			
			// As the key is unique, the result size may be 0 or 1 if found
			if (resultList.size() == 1) {
				result = resultList.get(0);
			}
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to query for a persistent Configuration entity by its key.", e);
		}

		return result;
	}
	
	/* --- Object operations --- */
	
}
