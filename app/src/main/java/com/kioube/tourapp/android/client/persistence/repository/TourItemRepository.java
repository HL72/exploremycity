package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.stmt.PreparedQuery;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * TourItemRepository type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemRepository extends RepositoryBase<TourItem> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = TourItemRepository.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemRepository object.
	 * 
	 * @param context
	 */
	public TourItemRepository(Context context) {
		super(context);
		
		// Sets the Configuration persistent type DAO
		try {
			this.setDao(this.getDatabaseHelper().getTourItemDao());
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the TourItem DAO.", e);
		}
	}
	
	/* --- Object operations --- */
	
	/**
	 * Gets the count of bookmarked persistent TourItem entities
	 * 
	 * @return The count of bookmarked persistent TourItem entities
	 */
	public long getBookmarkedCount() {
		long result = 0;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.setCountOf(true)
				.where()
				.eq(TourItem.IS_BOOKMARKED_FIELD_NAME, true)
				.prepare();
			
			result = this.getDao().countOf(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the count of bookmarked persistent TourItem entities.", e);
		}

		return result;		
	}
	
	/**
	 * Gets the count of persistent TourItem objects matching the specified GeographicalArea
	 * 
	 * @param area The persistent GeographicalArea object used to filter results
	 * @return The count of persistent TourItem objects matching the specified GeographicalArea
	 */
	public long getCountByGeographicalArea(GeographicalArea area) {
		long result = 0;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.setCountOf(true)
				.where()
				.eq(TourItem.GEOGRAPHICAL_AREA_ID_FIELD_NAME, area.getId())
				.prepare();
			
			result = this.getDao().countOf(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the count of persistent TourItem entities matching the GeographicalArea '" + area.getId() + "'.", e);
		}

		return result;		
	}
	
	/**
	 * Gets the count of persistent TourItem objects matching the specified GeographicalArea and Theme
	 * 
	 * @param geographicalArea The persistent GeographicalArea object used to filter results
	 * @param theme The persistent Theme object used to filter results
	 * 
	 * @return The count of persistent TourItem objects matching the specified GeographicalArea and Theme
	 */
	public long getCountByFilter(GeographicalArea geographicalArea, Theme theme) {
		long result = 0;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.setCountOf(true)
				.where()
				.eq(TourItem.GEOGRAPHICAL_AREA_ID_FIELD_NAME, geographicalArea.getId())
				.and()
				.eq(TourItem.THEME_ID_FIELD_NAME, theme.getId())				
				.prepare();
			
			result = this.getDao().countOf(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the count of persistent TourItem entities matching the GeographicalArea ID = '" + geographicalArea.getId() + "' and Theme ID = '" + theme.getId() + "'.", e);
		}

		return result;		
	}

	/**
	 * Gets the persistent TourItem objects matching the specified GeographicalArea and Theme
	 * 
	 * @param geographicalArea The persistent GeographicalArea object used to filter results
	 * @param theme The persistent Theme object used to filter results
	 * 
	 * @return The persistent TourItem objects matching the specified GeographicalArea and Theme
	 */
	public List<TourItem> getByFilter(GeographicalArea geographicalArea, Theme theme) {
		List<TourItem> result = null;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.where()
				.eq(TourItem.GEOGRAPHICAL_AREA_ID_FIELD_NAME, geographicalArea.getId())
				.and()
				.eq(TourItem.THEME_ID_FIELD_NAME, theme.getId())				
				.prepare();
			
			result = this.getDao().query(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the persistent TourItem entities matching the GeographicalArea ID = '" + geographicalArea.getId() + "' and Theme ID = '" + theme.getId() + "'.", e);
		}

		return result;
	}
	
	public List<TourItem> getByFilter(GeographicalArea geographicalArea, Theme theme, String keyword) {
		List<TourItem> result = null;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.where()
				.eq(TourItem.GEOGRAPHICAL_AREA_ID_FIELD_NAME, geographicalArea.getId())
				.and()
				.eq(TourItem.THEME_ID_FIELD_NAME, theme.getId())
				.and()
				.like(TourItem.NAME_FIELD_NAME, "%" + keyword + "%")
				.prepare();
			
			result = this.getDao().query(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the persistent TourItem entities matching the GeographicalArea ID = '" + geographicalArea.getId() + "' and Theme ID = '" + theme.getId() + "'.", e);
		}

		return result;
	}

    /**
     * OGT - Gets the persistent TourItem objects matching the keyword - Recherche rapide
     *
     * @param keyword The persistent keyword object used to filter results 
     *
     * @return The persistent TourItem objects matching the specified keyword
     */
    public List<TourItem> getByFilter(String keyword) {
        List<TourItem> result = null;

        try {
            PreparedQuery<TourItem> query = this.getDao().queryBuilder()
                    .where()
                    .like(TourItem.NAME_FIELD_NAME, "%" + keyword + "%")
                    .prepare();

            result = this.getDao().query(query);
        }
        catch (SQLException e) {
            Log.e(LOG_TAG, "Failed to get the persistent TourItem entities");
        }

        return result;
    }

	/**
	 * Gets the bookmarked persistent TourItem objects
	 * 
	 * @return The bookmarked persistent TourItem objects
	 */
	public List<TourItem> getBookmarked() {
		List<TourItem> result = null;

		try {			
			PreparedQuery<TourItem> query = this.getDao().queryBuilder()
				.where()
				.eq(TourItem.IS_BOOKMARKED_FIELD_NAME, true)			
				.prepare();
			
			result = this.getDao().query(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to get the bookmarked persistent TourItem entities.", e);
		}

		return result;
	}
	
}
