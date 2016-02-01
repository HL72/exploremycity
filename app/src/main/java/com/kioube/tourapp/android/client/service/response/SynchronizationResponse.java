package com.kioube.tourapp.android.client.service.response;

import java.util.Date;
import java.util.List;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import com.kioube.tourapp.android.client.domain.Configuration;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.domain.TourItem;

/**
 * 
 * SynchronizationResponse type definition. Handles synchronization service response.
 * 
 * @author Julien Mellerin
 * 
 */
@Root
public class SynchronizationResponse {
	
	/* --- Fields --- */
	
	@ElementList(name = "GeographicalAreaList", type = GeographicalArea.class, required = false)
	private List<GeographicalArea> geographicalAreaList;
	
	@ElementList(name = "ThemeList", type = Theme.class, required = false)
	private List<Theme> themeList;
	
	@ElementList(name = "TourItemList", type = TourItem.class, required = false)
	private List<TourItem> tourItemList;
	
	@ElementList(name = "CoordinateList", type = Coordinate.class, required = false)
	private List<Coordinate> coordinateList;
	
	@ElementList(name = "ConfigurationList", type = Configuration.class, required = false)
	private List<Configuration> configurationList;

    @ElementList(name = "UpdateDate", type = Date.class, required = false)
    private List<Configuration> updateDate;

	/* --- Accessors --- */
	
	/**
	 * Gets the SynchronizationResponse object's geographicalAreaList value
	 * 
	 * @return The SynchronizationResponse object's geographicalAreaList value
	 */
	public List<GeographicalArea> getGeographicalAreaList() {
		return this.geographicalAreaList;
	}
	
	/**
	 * Sets the SynchronizationResponse object's geographicalAreaList value
	 * 
	 * @param geographicalAreaList The SynchronizationResponse object's geographicalAreaList value to set
	 */
	public void setGeographicalAreaList(List<GeographicalArea> geographicalAreaList) {
		this.geographicalAreaList = geographicalAreaList;
	}
	
	/**
	 * Gets the SynchronizationResponse object's themeList value
	 * 
	 * @return The SynchronizationResponse object's themeList value
	 */
	public List<Theme> getThemeList() {
		return this.themeList;
	}
	
	/**
	 * Sets the SynchronizationResponse object's themeList value
	 * 
	 * @param themeList The SynchronizationResponse object's themeList value to set
	 */
	public void setThemeList(List<Theme> themeList) {
		this.themeList = themeList;
	}
	
	/**
	 * Gets the SynchronizationResponse object's tourItemList value
	 * 
	 * @return The SynchronizationResponse object's tourItemList value
	 */
	public List<TourItem> getTourItemList() {
		return this.tourItemList;
	}
	
	/**
	 * Sets the SynchronizationResponse object's tourItemList value
	 * 
	 * @param tourItemList The SynchronizationResponse object's tourItemList value to set
	 */
	public void setTourItemList(List<TourItem> tourItemList) {
		this.tourItemList = tourItemList;
	}
	
	/**
	 * Gets the SynchronizationResponse object's coordinateList value
	 * 
	 * @return The SynchronizationResponse object's coordinateList value
	 */
	public List<Coordinate> getCoordinateList() {
		return this.coordinateList;
	}
	
	/**
	 * Sets the SynchronizationResponse object's coordinateList value
	 * 
	 * @param coordinateList The SynchronizationResponse object's coordinateList value to set
	 */
	public void setCoordinateList(List<Coordinate> coordinateList) {
		this.coordinateList = coordinateList;
	}
	
	/**
	 * Gets the SynchronizationResponse object's configurationList value
	 * 
	 * @return The SynchronizationResponse object's configurationList value
	 */
	public List<Configuration> getConfigurationList() {
		return this.configurationList;
	}
	
	/**
	 * Sets the SynchronizationResponse object's configurationList value
	 * 
	 * @param configurationList The SynchronizationResponse object's configurationList value to set
	 */
	public void setConfigurationList(List<Configuration> configurationList) {
		this.configurationList = configurationList;
	}
	
	/* --- Constructors --- */
	
}
