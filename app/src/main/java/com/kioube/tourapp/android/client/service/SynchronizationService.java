package com.kioube.tourapp.android.client.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.Configuration;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.helper.SessionManager;
import com.kioube.tourapp.android.client.persistence.repository.ConfigurationRepository;
import com.kioube.tourapp.android.client.persistence.repository.CoordinateRepository;
import com.kioube.tourapp.android.client.persistence.repository.GeographicalAreaRepository;
import com.kioube.tourapp.android.client.persistence.repository.ThemeRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemImageRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;
import com.kioube.tourapp.android.client.service.listener.ServiceListener;
import com.kioube.tourapp.android.client.service.response.SynchronizationResponse;

public class SynchronizationService {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = SynchronizationService.class.getSimpleName();
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private Context context;
	
	private ServiceListener listener;
	
	private ConfigurationRepository configurationRepository;
	private CoordinateRepository coordinateRepository;
	private GeographicalAreaRepository geographicalAreaRepository;
	private TourItemImageRepository imageRepository;
	private ThemeRepository themeRepository;
	private TourItemRepository tourItemRepository;
	private TourItemImageRepository tourItemImageRepository;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the SynchronizationService object's context value
	 * 
	 * @return The SynchronizationService object's context value
	 */
	public Context getContext() {
		return this.context;
	}
	
	/**
	 * Gets the SynchronizationService object's configurationRepository value
	 * 
	 * @return The SynchronizationService object's configurationRepository value
	 */
	public ConfigurationRepository getConfigurationRepository() {
		if (this.configurationRepository == null) {
			this.configurationRepository = new ConfigurationRepository(this.context);
		}
		
		return this.configurationRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's coordinateRepository value
	 * 
	 * @return The SynchronizationService object's coordinateRepository value
	 */
	public CoordinateRepository getCoordinateRepository() {
		if (this.coordinateRepository == null) {
			this.coordinateRepository = new CoordinateRepository(this.context);
		}
		
		return this.coordinateRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's geographicalAreaRepository value
	 * 
	 * @return The SynchronizationService object's geographicalAreaRepository value
	 */
	public GeographicalAreaRepository getGeographicalAreaRepository() {
		if (this.geographicalAreaRepository == null) {
			this.geographicalAreaRepository = new GeographicalAreaRepository(this.context);
		}
		
		return this.geographicalAreaRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's imageRepository value
	 * 
	 * @return The SynchronizationService object's imageRepository value
	 */
	public TourItemImageRepository getImageRepository() {
		if (this.imageRepository == null) {
			this.imageRepository = new TourItemImageRepository(this.context);
		}
		
		return this.imageRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's themeRepository value
	 * 
	 * @return The SynchronizationService object's themeRepository value
	 */
	public ThemeRepository getThemeRepository() {
		if (this.themeRepository == null) {
			this.themeRepository = new ThemeRepository(this.context);
		}
		
		return this.themeRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's tourItemRepository value
	 * 
	 * @return The SynchronizationService object's tourItemRepository value
	 */
	public TourItemRepository getTourItemRepository() {
		if (this.tourItemRepository == null) {
			this.tourItemRepository = new TourItemRepository(this.context);
		}
		
		return this.tourItemRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's tourItemImageRepository value
	 * 
	 * @return The SynchronizationService object's tourItemRepository value
	 */
	public TourItemImageRepository getTourItemImageRepository() {
		if (this.tourItemImageRepository == null) {
			this.tourItemImageRepository = new TourItemImageRepository(this.context);
		}
		
		return this.tourItemImageRepository;
	}
	
	/**
	 * Gets the SynchronizationService object's listener value
	 * 
	 * @return The SynchronizationService object's listener value
	 */
	public ServiceListener getListener() {
		return this.listener;
	}
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new SynchronizationService object.
	 * 
	 * @param context
	 * @param listener
	 */
	public SynchronizationService(Context context, ServiceListener listener) {
		super();
		
		this.context = context;
		this.listener = listener;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	/**
	 * Gets the remote service URL to read data from
	 * 
	 * @return The remote service to read data from
	 * @throws java.io.UnsupportedEncodingException
	 */
	@SuppressLint("SimpleDateFormat")
	private String getServiceUrl() throws UnsupportedEncodingException {
		String result = null;
		
		// TODO [2014-03-18, JMEL] For developement purposes, remove this when services are developed
		boolean fakeServices = this.getContext().getResources().getBoolean(R.bool.useFakeServices);

        if (fakeServices) {
			Log.d(LOG_TAG, "Using fake synchronization services because of lazy server side developer.");
			
			result = this.getContext().getString(R.string.fakeFirstSyncService);
			
			/**
			 * The following part will do a full sync on first start 
			 * then an update on second launch
			 * then a delete on third launch
			 */
			/*
			// Simulates a first load
			if (this.getConfigurationRepository().getCountAll() == 0) {
				result = this.getContext().getString(R.string.fakeFirstSyncService);
			}
			
			// Simulates an update on second launch
			else if (this.getThemeRepository().getCountAll() == 3) {
				result = this.getContext().getString(R.string.fakeUpdateSyncService);
			}
			
			// Simulates a delete on third launch
			else {
				result = this.getContext().getString(R.string.fakeDeleteSyncService);
			}
			*/
		}
		else {
			String serviceRoot = this.getContext().getString(R.string.serviceRoot) + "synchronize.xml";
			
			SessionManager sessionManager = new SessionManager(this.getContext());
			
			// First pull
			if (sessionManager.getLastSynchronizationDate().getTime() == 0) {
				result = serviceRoot;
			}
			
			// Update
			else {
				String date = URLEncoder.encode(
					new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(sessionManager.getLastSynchronizationDate()) + " GMT",
					"UTF-8");
				
				result = String.format("%s?date=%s", serviceRoot, date);
			}
			
			sessionManager.setLastSynchronizationDate();			
		}
		
		return result;
	}
	
	/**
	 * Runs the synchronization service
	 */
	public void run() {
		
		// Creates the import thread
		Thread thread = new Thread(new Runnable() {
			
			public void run() {
				String urlString = null;
				
				// Deserializes from URL
				Serializer serializer = new Persister(new AnnotationStrategy());
				
				SynchronizationResponse response = null;
                Date updatedDateRemote = null;

				try {

                    boolean localServices = SynchronizationService.this.getContext().getResources().getBoolean(R.bool.useLocalFiles);
                    if(localServices){
                        urlString = SynchronizationService.this.getServiceUrl();

                        Log.d(LOG_TAG, "Synchronizing from '" + urlString + "'.");
                        InputStream stream = null;
                        if (urlString != null) {
                            URL url = new URL(urlString);
                            long date = url.openConnection().getHeaderFieldDate("Last-Modified", 0);
                            updatedDateRemote = new Date(date);

                            stream = url.openStream();

                        }

                        String fileName = SynchronizationService.this.getContext().getString(R.string.localFirstSyncService);

                        File localFile = new File(getContext().getFilesDir(), fileName);
                        Log.d(LOG_TAG, "local file : '" + localFile.getAbsolutePath() + "'.");

                        if(!localFile.exists() || (localFile.exists() && (new Date(localFile.lastModified())).before(updatedDateRemote))){
                            File outputLocalFile = new File(getContext().getFilesDir(), fileName);
                            Log.d(LOG_TAG, "Updating file '" + outputLocalFile.getAbsolutePath() + "'.");
                            OutputStream outputStream = new FileOutputStream(outputLocalFile);
                            int read = 0;
                            byte[] bytes = new byte[1024];

                            while ((read = stream.read(bytes)) != -1) {
                                outputStream.write(bytes, 0, read);
                            }
                        }

                        stream = new FileInputStream(new File(getContext().getFilesDir(), fileName));
                        response = serializer.read(SynchronizationResponse.class, stream);

                    }

					// Save data
					if (response != null) {
						
						SynchronizationService.this.persistGeographicalAreaList(response.getGeographicalAreaList());
						SynchronizationService.this.persistThemeList(response.getThemeList());
						SynchronizationService.this.persistTourItemList(response.getTourItemList());
						SynchronizationService.this.persistTourItemImageList(response.getTourItemList());
						SynchronizationService.this.persistCoordinateList(response.getCoordinateList());
						SynchronizationService.this.persistConfigurationList(response.getConfigurationList());
					}
					else {
						throw new Exception("Service response is null using '" + urlString + "'.");
					}
					
					// Runs the onCompleted event of the listener
					if (SynchronizationService.this.getListener() != null) {
						SynchronizationService.this.getListener().onCompleted();
					}
				}
				catch (Exception e) {
					
					// Sends back the exception
					if (SynchronizationService.this.getListener() != null) {
						SynchronizationService.this.getListener().onError(new Exception(
							"Failed to synchronize data from '" + urlString + "'.",
							e
							));
					}
				}
			}
		});
		
		// Starts the import process
		thread.start();
	}
	
	/**
	 * Persists the imported GeographicalArea list by managing creation, update and deletion
	 * 
	 * @param geographicalAreaList A list of persistent items to save
	 */
	private void persistGeographicalAreaList(List<GeographicalArea> geographicalAreaList) {
		if (geographicalAreaList != null) {
			for (GeographicalArea geographicalArea : geographicalAreaList) {
				
				// Deletion
				if (geographicalArea.getDeletedOn().after(new GregorianCalendar(1970, 1, 1).getTime()))
				{
					this.getGeographicalAreaRepository().delete(geographicalArea);
				}
				
				// Save
				else {
					this.getGeographicalAreaRepository().createOrUpdate(geographicalArea);
				}
			}
		}
	}
	
	/**
	 * Persists the imported Theme list by managing creation, update and deletion
	 * 
	 * @param themeList A list of persistent items to save
	 */
	private void persistThemeList(List<Theme> themeList) {
		if (themeList != null) {
			for (Theme theme : themeList) {
				
				// Deletion
				if (theme.getDeletedOn().after(new GregorianCalendar(1970, 1, 1).getTime()))
				{
					this.getThemeRepository().delete(theme);
				}
				
				// Save
				else {
					this.getThemeRepository().createOrUpdate(theme);
				}
				
			}
		}
	}
	
	/**
	 * Persists the imported TourItem list by managing creation, update and deletion
	 * 
	 * @param tourItemList A list of persistent items to save
	 */
	private void persistTourItemList(List<TourItem> tourItemList) {
		
		if (tourItemList != null) {
			for (TourItem tourItem : tourItemList) {
				
				// Deletion
				if (tourItem.getDeletedOn().after(new GregorianCalendar(1970, 1, 1).getTime()))
				{
					this.getTourItemRepository().delete(tourItem);
				}
				
				// Save
				else {
					
					// If the tourItem already exists and is bookmarked, mark the update as bookmarked
					TourItem previousTourItem = this.getTourItemRepository().getById(tourItem.getId());
					
					if (previousTourItem != null) {
						tourItem.setBookmarked(previousTourItem.isBookmarked());
					}
					
					this.getTourItemRepository().createOrUpdate(tourItem);
				}
			}
		}
	}
	
	/**
	 * Persists the imported TourItemImage list by managing creation, update and deletion
	 * 
	 * @param tourItemList A list of persistent items to save
	 */
	private void persistTourItemImageList(List<TourItem> tourItemList) {
		if (tourItemList != null) {
			for (TourItem tourItem : tourItemList) {
				
				// Deletion
				if (tourItem.getDeletedOn().after(new GregorianCalendar(1970, 1, 1).getTime()))
				{
					List<TourItemImage> imageList = this.getTourItemImageRepository().getByTourItem(tourItem);
					
					// Deletes all images
					for (TourItemImage image : imageList) {
						this.getTourItemImageRepository().delete(image);
					}
				}
				
				// Save
				else {
					
					if (tourItem.getImageList() != null) {
						for (TourItemImage image : tourItem.getImageList()) {
							image.setTourItem(tourItem);
							this.getTourItemImageRepository().createOrUpdate(image);
							Log.d(LOG_TAG, "Saved image ID = " + image.getId() + " for tourItem ID = " + image.getTourItem().getId() + " (" + image.getTourItem().getName() + ")");
						}
					}
				}
				
			}
		}
	}
	
	/**
	 * Persists the imported Coordinate list by managing creation, update and deletion
	 * 
	 * @param coordinateList A list of persistent items to save
	 */
	private void persistCoordinateList(List<Coordinate> coordinateList) {
		if (coordinateList != null) {
			for (Coordinate coordinate : coordinateList) {
				
				// Deletion
				if (coordinate.getDeletedOn().after(new GregorianCalendar(1970, 1, 1).getTime()))
				{
					this.getCoordinateRepository().delete(coordinate);
				}
				
				// Save
				else {
					this.getCoordinateRepository().createOrUpdate(coordinate);
				}
			}
		}
			
	}
	
	/**
	 * Persists the imported Configuration list by managing creation, update and deletion
	 * 
	 * @param configurationList A list of persistent items to save
	 */
	private void persistConfigurationList(List<Configuration> configurationList) {
		if (configurationList != null) {
			for (Configuration configuration : configurationList) {
				
				// Tries to get the configuration if exists
				Configuration previousConfiguration = this.getConfigurationRepository().getByKey(configuration.getKey());
				
				// If exists, updates it
				if (previousConfiguration != null) {
					previousConfiguration.setValue(configuration.getValue());
					this.getConfigurationRepository().update(previousConfiguration);
				}
				
				// Else create it
				else {
					this.getConfigurationRepository().create(configuration);
					
				}
			}
		}
	}
}
