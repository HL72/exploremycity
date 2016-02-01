package com.kioube.tourapp.android.client.helper;

import java.sql.Date;
import java.sql.Timestamp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SessionManager {
	
	/* --- Constants --- */
	private static final String PREFERENCE_FILE = "TourAppPreferences";
	private static final int PRIVATE_MODE = 0;
	private static final String LAST_SYNC_DATE_KEY = "LAST_SYNC_DATE";
	private static final String CONNECTED_MODE_KEY = "CONNECTED_MODE";
	private static final String ENFORCE_SYNC_KEY = "ENFORCE_SYNC";
	private static final String SYNCHRONIZATION_PREF_KEY = "SYNCHRONIZATION_PREF";
	
	/* --- Fields --- */
	SharedPreferences sharedPreferences;
	Editor editor;
	Context _context;
	
	/* --- .ctor --- */
	
	/*
	 * Constructs a new SessionManager instance
	 */
	public SessionManager(Context context) {
		this._context = context;
		sharedPreferences = this._context.getSharedPreferences(PREFERENCE_FILE, PRIVATE_MODE);
		editor = sharedPreferences.edit();
	}
	
	/* --- Object operations --- */
	
	/**
	 * Gets the last sync date
	 */
	public Date getLastSynchronizationDate() {
		Timestamp timestamp = new Timestamp(this.sharedPreferences.getLong(LAST_SYNC_DATE_KEY, 0));
		
		return new Date(timestamp.getTime());
	}
	
	/**
	 * Sets the last sync date
	 */
	public void setLastSynchronizationDate() {
		this.editor.putLong(LAST_SYNC_DATE_KEY, System.currentTimeMillis());
		
		this.editor.commit();
	}
	
	/**
	 * Gets the connection mode
	 * 
	 * @return True if in connected mode
	 */
	public boolean isConnectedMode() {
		return this.sharedPreferences.getBoolean(CONNECTED_MODE_KEY, true);
	}
	
	/**
	 * Sets the connexion mode
	 * 
	 * @param connectedMode true = connected, false = not connected
	 */
	public void setConnectedMode(boolean connectedMode) {
		this.editor.putBoolean(CONNECTED_MODE_KEY, connectedMode);
		
		this.editor.commit();
	}
	
	/**
	 * Returns true if the sync must be enforced
	 * 
	 * @return True if the sync must be enforced
	 */
	public boolean isEnforcingSync() {
		return this.sharedPreferences.getBoolean(ENFORCE_SYNC_KEY, false);
	}
	
	/**
	 * Sets the synchronization enforcement
	 * 
	 * @param enforce true to enforce
	 */
	public void setEnforcingSync(boolean enforce) {
		this.editor.putBoolean(ENFORCE_SYNC_KEY, enforce);
		
		this.editor.commit();
	}
	
	/**
	 * Gets the synchronization preference
	 * 
	 * @return The synchronization preference
	 */
	public SynchronizationPreference getSynchronizationPreference() {
		int identifier = this.sharedPreferences.getInt(SYNCHRONIZATION_PREF_KEY, SynchronizationPreference.AUTOMATIC.getIdentifier());
		
		return SynchronizationPreference.parseIdentifier(identifier);
	}
	
	/**
	 * Sets the synchronization preference
	 * 
	 * @param preference The synchronization preference
	 */
	public void setSynchronizationPreference(SynchronizationPreference preference) {
		this.editor.putInt(SYNCHRONIZATION_PREF_KEY, preference.getIdentifier());
		
		this.editor.commit();
	}
}
