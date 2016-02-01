package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * CoordinateRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class CoordinateRepository extends RepositoryBase<Coordinate> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = CoordinateRepository.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new CoordinateRepository object.
	 * 
	 * @param context
	 */
	public CoordinateRepository(Context context) {
		super(context);
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getCoordinateDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the Coordinate DAO.", e);
		}
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */

	/**
	 * Gets the persistent Coordinate object matching the given TourItem object
	 * 
	 * @param tourItem The persistent TourItem object used to filter results
	 * 
	 * @return The persistent Coordinate object matching the given TourItem object
	 */
	public Coordinate getByTourItem(TourItem tourItem) {
		Coordinate result = null;

		try {			
			PreparedQuery<Coordinate> query = this.getDao().queryBuilder()
				.where()
				.eq(Coordinate.TOUR_ITEM_ID_FIELD_NAME, tourItem.getId())		
				.prepare();
			
			List<Coordinate> queryResult = this.getDao().query(query);
			
			if (queryResult.size() > 0) {
				result = queryResult.get(0);
			}
			else {
				Log.e(LOG_TAG, "Failed to get the persistent Coordinate entities matching the TourItem ID = '" + tourItem.getId() + "' : no result.");
			}
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the persistent Coordinate entities matching the TourItem ID = '" + tourItem.getId() + "'", e);
		}

		return result;
	}
	
}
