package com.kioube.tourapp.android.client.persistence;

import com.j256.ormlite.android.apptools.OpenHelperManager;

import android.content.Context;

/**
 * 
 * DatabaseManager type definition.
 * This is defined as a multi-threade singleton so can't be instanciated
 * somewhere else and no need to care about the thread where the manager is used.
 * 
 * See thread safe singleton pattern proposals here :
 * http://stackoverflow.com/questions/11165852/java-singleton-and-synchronization
 * 
 * @author Julien Mellerin
 * 
 */
public class DatabaseManager {
	
	/**
	 * Singleton loader
	 * 
	 * DatabaseManagerLoader type definition
	 *
	 */
	private static class DatabaseManagerLoader {
		static DatabaseManager Instance = new DatabaseManager();
	}
	
	/* --- Fields --- */
	
	private DatabaseHelper databaseHelper = null;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the DatabaseManager object's databaseHelper
	 * 
	 * @return The DatabaseManager object's databaseHelper
	 */
	public DatabaseHelper getDatabaseHelper(Context context) {
		
		// TODO [2014-03-15, JMEL] Je me demande si le lazy loading est judicieux là vu le singleton multi-thread employé... ou alors, on utilise que le context application pour charger le helper (et donc les repositories)
		if (this.databaseHelper == null) {
			this.databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
		}
		
		return this.databaseHelper;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new DatabaseManager object.
	 * Singleton construction so keep it private.
	 */
	private DatabaseManager() {
	}
	
	/* --- Class operations --- */
	
	/**
	 * Gets the DatabaseManager singleton
	 * 
	 * @return The DatabaseManager singleton
	 */
	public static DatabaseManager getInstance() {
        return DatabaseManagerLoader.Instance;
    }	
	
	/* --- Object operations --- */
	
	/**
	 * Releases the database helper
	 * 
	 * @param databaseHelper DatabaseHelper to relase
	 */
	public void releaseHelper()
	{
		if (this.databaseHelper != null) {
			OpenHelperManager.releaseHelper();
			this.databaseHelper = null;
		}
	}
	
}
