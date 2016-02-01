package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;

/**
 * 
 * ImageRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemImageRepository extends RepositoryBase<TourItemImage> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = TourItemImageRepository.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new ImageRepository object.
	 * 
	 * @param context
	 */
	public TourItemImageRepository(Context context) {
		super(context);
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getImageDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the Image DAO.", e);
		}
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */

	/**
	 * Gets the list of persistent TourItemImage objects matching the given TourItem object
	 * 
	 * @param tourItem The persistent TourItem object used to filter results
	 * 
	 * @return The list of persistent TourItemImage objects matching the given TourItem object
	 */
	public List<TourItemImage> getByTourItem(TourItem tourItem) {
		List<TourItemImage> result = null;

		try {			
			PreparedQuery<TourItemImage> query = this.getDao().queryBuilder()
				.where()
				.eq(TourItemImage.TOUR_ITEM_ID_FIELD_NAME, tourItem.getId())		
				.prepare();
			
			result = this.getDao().query(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the list of persistent TourItemImage entities matching the TourItem ID = '" + tourItem.getId() + "'", e);
		}

		return result;
	}
	
}
