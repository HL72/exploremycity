package com.kioube.tourapp.android.client.helper;

import java.security.InvalidParameterException;

/**
 * Synchronization preference enumeration
 */
public enum SynchronizationPreference {
	NEVER(0x0000),
	ASK(0x0001),
	AUTOMATIC(0x0002);
	
	private int identifier;
	
	/**
	 * Gets the SynchronizationPreference object's identifier
	 * 
	 * @return The SynchronizationPreference object's identifier
	 */
	public int getIdentifier() {
		return this.identifier;
	}
	
	/**
	 * Constructs a SynchronizationPreference object
	 * 
	 * @param identifier
	 */
	private SynchronizationPreference(int identifier) {
		this.identifier = identifier;
	}
	
	/**
	 * Parses an identifier to return the corresponding SynchronizationPreference
	 * 
	 * @param identifier
	 * @return The matching SynchronizationPreference
	 */
	public static SynchronizationPreference parseIdentifier(int identifier) {
		SynchronizationPreference result = null;
		
		for (SynchronizationPreference type : SynchronizationPreference.values())
		{
			if (identifier == type.getIdentifier()) {
				result = type;
				break;
			}
		}
		
		if (result == null) {
			throw new InvalidParameterException("Can not find a UserRoleType with the identifier '" + identifier + "'.");
		}
		
		return result;
	}
}