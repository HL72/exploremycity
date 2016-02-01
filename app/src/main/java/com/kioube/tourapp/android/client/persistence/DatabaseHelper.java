package com.kioube.tourapp.android.client.persistence;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.kioube.tourapp.android.client.domain.Configuration;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * DatabaseHelper type definition. Should only be accessed through DatabaseManager
 * @author Julien Mellerin
 *
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
	
	// TODO [2014-03-15, JMEL] Make this only available through the DatabaseManager singleton
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = DatabaseHelper.class.getSimpleName();
	
	private static final String DATABASE_NAME = "tourApp.db";
	private static final int DATABASE_VERSION = 1;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private Dao<Configuration, Integer> configurationDao = null;
	private RuntimeExceptionDao<Configuration, Integer> configurationRuntimeDao = null;
	
	private Dao<Coordinate, Integer> coordinateDao = null;
	private RuntimeExceptionDao<Coordinate, Integer> coordinateRuntimeDao = null;
	
	private Dao<GeographicalArea, Integer> geographicalAreaDao = null;
	private RuntimeExceptionDao<GeographicalArea, Integer> geographicalAreaRuntimeDao = null;
	
	private Dao<TourItemImage, Integer> imageDao = null;
	private RuntimeExceptionDao<TourItemImage, Integer> imageRuntimeDao = null;
	
	private Dao<Theme, Integer> themeDao = null;
	private RuntimeExceptionDao<Theme, Integer> themeRuntimeDao = null;
	
	private Dao<TourItem, Integer> tourItemDao = null;
	private RuntimeExceptionDao<TourItem, Integer> tourItemRuntimeDao = null;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the DatabaseHelper object's configurationDao value
	 * 
	 * @return The DatabaseHelper object's configurationDao value
	 * @throws java.sql.SQLException
	 */
	public Dao<Configuration, Integer> getConfigurationDao() throws SQLException {
		if (this.configurationDao == null) {
			this.configurationDao = this.getDao(Configuration.class);
		}

		return this.configurationDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's configurationDataDao value
	 * 
	 * @return The DatabaseHelper object's configurationDataDao value
	 */
	public RuntimeExceptionDao<Configuration, Integer> getConfigurationDataDao() {
		if (this.configurationRuntimeDao == null) {
			this.configurationRuntimeDao = this.getRuntimeExceptionDao(Configuration.class);
		}
		
		return this.configurationRuntimeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's coordinateDao value
	 * 
	 * @return The DatabaseHelper object's coordinateDao value
	 */
	public Dao<Coordinate, Integer> getCoordinateDao() throws SQLException {
		if (this.coordinateDao == null) {
			this.coordinateDao = this.getDao(Coordinate.class);
		}

		return this.coordinateDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's coordinateDataDao value
	 * 
	 * @return The DatabaseHelper object's coordinateDataDao value
	 */
	public RuntimeExceptionDao<Coordinate, Integer> getCoordinateDataDao() {
		if (this.coordinateRuntimeDao == null) {
			this.coordinateRuntimeDao = this.getRuntimeExceptionDao(Coordinate.class);
		}
		
		return this.coordinateRuntimeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's geographicalAreaDao value
	 * 
	 * @return The DatabaseHelper object's geographicalAreaDao value
	 */
	public Dao<GeographicalArea, Integer> getGeographicalAreaDao() throws SQLException {
		if (this.geographicalAreaDao == null) {
			this.geographicalAreaDao = this.getDao(GeographicalArea.class);
		}

		return this.geographicalAreaDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's geographicalAreaDataDao value
	 * 
	 * @return The DatabaseHelper object's geographicalAreaDataDao value
	 */
	public RuntimeExceptionDao<GeographicalArea, Integer> getGeographicalAreaDataDao() {
		if (this.geographicalAreaRuntimeDao == null) {
			this.geographicalAreaRuntimeDao = this.getRuntimeExceptionDao(GeographicalArea.class);
		}
		
		return this.geographicalAreaRuntimeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's imageDao value
	 * 
	 * @return The DatabaseHelper object's imageDao value
	 */
	public Dao<TourItemImage, Integer> getImageDao() throws SQLException {
		if (this.imageDao == null) {
			this.imageDao = this.getDao(TourItemImage.class);
		}

		return this.imageDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's imageDataDao value
	 * 
	 * @return The DatabaseHelper object's imageDataDao value
	 */
	public RuntimeExceptionDao<TourItemImage, Integer> getImageDataDao() {
		if (this.imageRuntimeDao == null) {
			this.imageRuntimeDao = this.getRuntimeExceptionDao(TourItemImage.class);
		}
		
		return this.imageRuntimeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's themeDao value
	 * 
	 * @return The DatabaseHelper object's themeDao value
	 */
	public Dao<Theme, Integer> getThemeDao() throws SQLException {
		if (this.themeDao == null) {
			this.themeDao = this.getDao(Theme.class);
		}

		return this.themeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's themeDataDao value
	 * 
	 * @return The DatabaseHelper object's themeDataDao value
	 */
	public RuntimeExceptionDao<Theme, Integer> getThemeDataDao() {
		if (this.themeRuntimeDao == null) {
			this.themeRuntimeDao = this.getRuntimeExceptionDao(Theme.class);
		}
		
		return this.themeRuntimeDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's tourItemDao value
	 * 
	 * @return The DatabaseHelper object's tourItemDao value
	 */
	public Dao<TourItem, Integer> getTourItemDao() throws SQLException {
		if (this.tourItemDao == null) {
			this.tourItemDao = this.getDao(TourItem.class);
		}

		return this.tourItemDao;
	}
	
	/**
	 * Gets the DatabaseHelper object's tourItemDataDao value
	 * 
	 * @return The DatabaseHelper object's tourItemDataDao value
	 */
	public RuntimeExceptionDao<TourItem, Integer> getTourItemDataDao() {
		if (this.tourItemRuntimeDao == null) {
			this.tourItemRuntimeDao = this.getRuntimeExceptionDao(TourItem.class);
		}
		
		return this.tourItemRuntimeDao;
	}
	
	/* --- .ctors --- */

	/**
	 * Constructs a new DatabaseHelper object
	 */
	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		
		// Enables foreign keys for cascading and relations consistancy
		this.getWritableDatabase().execSQL("PRAGMA foreign_keys = ON");
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */

	/**
	 * Occurs on database creation
	 */
	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

		// Creates tables
		try {
			TableUtils.createTable(connectionSource, Configuration.class);
			TableUtils.createTable(connectionSource, GeographicalArea.class);
			TableUtils.createTable(connectionSource, Theme.class);
			TableUtils.createTable(connectionSource, TourItem.class);
			TableUtils.createTable(connectionSource, Coordinate.class);
			TableUtils.createTable(connectionSource, TourItemImage.class);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to create the database.", e);
			throw new RuntimeException(e);
		}

	}

	/**
	 * Occurs on database upgrade.
	 */
	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		// Nothing to do yet
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		
		this.configurationDao = null;
		this.configurationRuntimeDao = null;

		this.coordinateDao = null;
		this.coordinateRuntimeDao = null;
		
		this.geographicalAreaDao = null;
		this.geographicalAreaRuntimeDao = null;
		
		this.imageDao = null;
		this.imageRuntimeDao = null;
		
		this.themeDao = null;
		this.themeRuntimeDao = null;
		
		this.tourItemDao = null;
		this.tourItemRuntimeDao = null;
	}
	
}
