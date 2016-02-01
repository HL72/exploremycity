package com.kioube.tourapp.android.client.ui;

import android.app.FragmentManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.persistence.repository.CoordinateRepository;
import com.kioube.tourapp.android.client.ui.filter.TourItemFilter;

/**
 * 
 * MapFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class MapFragment extends FragmentBase {
	
	/* --- Constants --- */

	@SuppressWarnings("unused")
	private static final String LOG_TAG = MapFragment.class.getName();
	private static final int DEFAULT_ZOOM = 16;
	
	/* --- Fields --- */
	
	private GoogleMap googleMap = null;
	private com.google.android.gms.maps.MapFragment googleMapFragment;
	private View view;
	private Coordinate coordinate;
	private CoordinateRepository coordinateRepository = new CoordinateRepository(this.getActivity());
	
	/* --- Getters and setters --- */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#getView()
	 */
	@Override
	public View getView() {
		return super.getView() != null ? super.getView() : this.view;
	}
	
	/**
	 * Gets the current TourITem coordinate
	 * 
	 * @return The current TourITem coordinate
	 */
	public Coordinate getCoordinate() {
		if (this.coordinate == null) {
			this.coordinate = this.coordinateRepository.getByTourItem(this.getTourItem());
		}
		
		return this.coordinate;
	}
	
	/**
	 * Gets the coordinate object as as string for snippet presentation
	 * @return The coordinate object as as string for snippet presentation
	 */
	public String getCoordinateAsSnippetContent() {
		String result = null;
		
		// TODO [2014-04-02, JMEL] Implement getCoordinateAsSnippetContent
		
		return result;
	}
	
	/**
	 * Gets the Fragment TourItem to render
	 */
	public TourItem getTourItem() {
		return this.getFilter().getTourItem();
	}
	
	/**
	 * Gets the fragment filter used to filter theme results
	 */
	public TourItemFilter getFilter() {
		return (TourItemFilter) super.getFilter();
	}
	
	/**
	 * Sets the fragment filter used to filter results
	 * 
	 * @param filter The filter used to filter results in the view
	 */
	public void setFilter(TourItemFilter filter) {
		super.setFilter(filter);
	}
	
	public com.google.android.gms.maps.MapFragment getGoogleMapFragment() {
		if (this.googleMapFragment == null) {
			this.googleMapFragment = (com.google.android.gms.maps.MapFragment) this.getFragmentManager()
				.findFragmentById(R.id.google_map_fragment);
		}
		
		return this.googleMapFragment;
	}
	
	/**
	 * Gets the Google Map Fragment in the activity
	 * 
	 * @return The Google Map Fragment in the activity
	 */
	public GoogleMap getGoogleMap() {	
		
		if (this.googleMap == null) {
			this.googleMap = this.getGoogleMapFragment().getMap();
		}
		
		return this.googleMap;
	}
	
	/* --- Operations --- */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_map, container, false);

		GoogleMap map = this.getGoogleMap();

		// User position detection and button activation
		map.setMyLocationEnabled(false);

		// Zooms to location
		LatLng latLng = new LatLng(this.getCoordinate().getLatitude(), this.getCoordinate().getLongitude());
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, DEFAULT_ZOOM));
		
		// Adds the POI marker
		map.addMarker(new MarkerOptions()
			.position(latLng)
			.title(this.getTourItem().getName())
			.snippet(this.getCoordinateAsSnippetContent())
		);
		
		return this.view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
		// Cleans references to inner views to avoid unreferences views on fragment reload
		
		// Kills map fragment
        FragmentManager fragmentManager = this.getFragmentManager();
        fragmentManager.beginTransaction().remove(this.getGoogleMapFragment()).commit();
		this.googleMap = null;
		this.googleMapFragment = null;
	}
}