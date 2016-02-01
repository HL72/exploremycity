package com.kioube.tourapp.android.client.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.Dao.CreateOrUpdateStatus;
import com.j256.ormlite.stmt.PreparedDelete;
import com.j256.ormlite.stmt.PreparedQuery;
import com.kioube.tourapp.android.client.persistence.DatabaseHelper;
import com.kioube.tourapp.android.client.persistence.DatabaseManager;

/**
 * 
 * RepositoryBase type definition used to implement persistent entities repositories.
 * 
 * @param <T> Entity type
 * @param <U> Entity ID type
 * 
 * @author Julien Mellerin
 */
public abstract class RepositoryBase<T> {
	
	/* --- Constants --- */
	
	protected static final String LOG_TAG = RepositoryBase.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private DatabaseHelper databaseHelper;
	private Dao<T, Integer> dao;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the RepositoryBase object's databaseHelper value
	 * 
	 * @return The RepositoryBase object's databaseHelper value
	 */
	public DatabaseHelper getDatabaseHelper() {
		return this.databaseHelper;
	}
	
	/**
	 * Gets the RepositoryBase object's DAO value
	 * 
	 * @return The RepositoryBase object's DAO value
	 */
	public Dao<T, Integer> getDao() {
		return this.dao;
	}
	
	/**
	 * Sets the RepositoryBase object's dao value
	 * 
	 * @param dao The RepositoryBase object's dao value to set
	 */
	public void setDao(Dao<T, Integer> dao) {
		this.dao = dao;
	}
	
	/* --- .ctors --- */
	
	/**
	 * 
	 * Constructs a new RepositoryBase object.
	 * 
	 * @param context
	 */
	public RepositoryBase(Context context)
	{
		this.databaseHelper = DatabaseManager.getInstance().getDatabaseHelper(context);
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	/**
	 * Makes an entity of type T as persistent
	 * 
	 * @param T The T entity to make persistent
	 * @return The count of affected rows
	 */
	public Integer create(T entity)
	{
		Integer result = 0;
		
		try {
			result = this.dao.create(entity);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to create a persistent " + this.dao.getDataClass().getName() + " entity.", e);
		}
		
		return result;
	}
	
	/**
	 * Creates or update an entity of type T as persistent
	 * 
	 * @param T The T entity to make persistent
	 * @return The count of affected rows
	 */
	public Integer createOrUpdate(T entity)
	{
		Integer result = 0;
		
		try {
			CreateOrUpdateStatus status = this.dao.createOrUpdate(entity);
			result = status.getNumLinesChanged();
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to create a persistent " + this.dao.getDataClass().getName() + " entity.", e);
		}
		
		return result;
	}
	
	/**
	 * Updates a persistent entity of type T
	 * 
	 * @param entity The persistent T entity to update
	 * @return The count of affected rows
	 */
	public int update(T entity)
	{
		int result = 0;
		
		try {
			result = this.dao.update(entity);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to update the persistent " + this.dao.getDataClass().getName() + " entity.", e);
		}
		
		return result;
	}
	
	/**
	 * Deletes a persistent entity of type T
	 * 
	 * @param entity The persistent T entity to delete
	 * @return The count of affected rows
	 */
	public int delete(T entity)
	{
		int result = 0;
		
		try {
			result = this.dao.delete(entity);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to delete the persistent " + this.dao.getDataClass().getName() + " entity.", e);
		}
		
		return result;
	}
	
	/**
	 * Deletes all persistent entities of type T
	 * 
	 * @return The count of affected rows
	 */
	public int deleteAll()
	{
		int result = 0;
		
		try {
			PreparedDelete<T> query = this.dao.deleteBuilder().prepare();
			result = this.dao.delete(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to delete all persistent " + this.dao.getDataClass().getName() + " entities.", e);
		}
		
		return result;
	}
	
	/**
	 * Gets all persistent entities of type T
	 * 
	 * @return All persistent entities of type T
	 */
	public List<T> getAll()
	{
		List<T> result = null;
		
		try {
			result = this.dao.queryForAll();
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to query for all persistent " + this.dao.getDataClass().getName() + " entities.", e);
		}
		
		return result;
	}
	
	/**
	 * Gets the count of all persistent entities of type T
	 * 
	 * @return The count of all persistent entities of type T
	 */
	public long getCountAll() {
		long result = 0;
		
		try {
			PreparedQuery<T> query = this.dao.queryBuilder()
				.setCountOf(true)
				.prepare();
			
			result = this.dao.countOf(query);
		}
		catch (SQLException e) {
			Log.e(LOG_TAG, "Failed to query for the count of all persistent " + this.dao.getDataClass().getName() + " entities.", e);
		}
		
		return result;
	}
	
	/**
	 * Gets a persistent entity by its ID
	 * 
	 * @param id The ID to find
	 * @return The persistent entity matching the specified ID
	 */
	public T getById(int id) {
		T result = null;
		
		try {
			result = this.dao.queryForId(id);
		}
		catch (SQLException exception) {
			Log.e(LOG_TAG, "Failed to query for a persistent " + this.dao.getDataClass().getName() + " entity matching the spcified ID '" + id + "'.", exception);
		}
		
		return result;
	}
	
}
