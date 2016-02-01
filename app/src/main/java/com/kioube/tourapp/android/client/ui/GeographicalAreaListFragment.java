package com.kioube.tourapp.android.client.ui;

import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.persistence.repository.GeographicalAreaRepository;
import com.kioube.tourapp.android.client.ui.adapter.GeographicalAreaItemAdapter;
import com.kioube.tourapp.android.client.ui.filter.GeographicalAreaListFilter;

/**
 * 
 * CategoryListFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class GeographicalAreaListFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = GeographicalAreaListFragment.class.getSimpleName();
	
	/* --- Fields --- */
	
	private ListView geographicalAreaListView;
	private GeographicalAreaRepository geographicalAreaRepository = new GeographicalAreaRepository(this.getActivity());
	private List<GeographicalArea> geographicalAreaList;
	private View view;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the AreaListFragment object's areaListView value
	 * 
	 * @return The AreaListFragment object's areaListView value
	 */
	public ListView getGeographicalAreaListView() {
		if (this.geographicalAreaListView == null) {
			this.geographicalAreaListView = (ListView) this.getView().findViewById(R.id.geographical_area_list_view);
		}
		
		return this.geographicalAreaListView;
	}
	
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
	 * Gets the geographical area to render depending on the fragment filter
	 * 
	 * @return The Geographical area to render depending on the fragment filter
	 */
	public List<GeographicalArea> getGeographicalAreaList() {
		
		// TODO [2014-03-21, JMEL] Filter results depending on the passed Filter item
		
		if (this.geographicalAreaList == null) {
			this.geographicalAreaList = this.geographicalAreaRepository.getAll();
		}
		
		return this.geographicalAreaList;
	}
	
	/**
	 * Sets the fragment filter
	 * 
	 * @param filter The fragment filter to set
	 */
	public void setFilter(GeographicalAreaListFilter filter) {
		super.setFilter(filter);
	}
	
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
		this.view = inflater.inflate(R.layout.fragment_geographical_area_list, container, false);
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.areas)
		);
		
		// Loads area list view
		this.loadGeographicalAreaListView();
		
		return this.view;
	}
	
	/**
	 * Loads the GeographicalArea ListView
	 */
	private void loadGeographicalAreaListView() {
		
		// Adapts it to the navigation list view
		this.getGeographicalAreaListView().setAdapter(
			new GeographicalAreaItemAdapter(
				this.getActivity(),
				R.layout.item_geographical_area_list_view,
				this.getGeographicalAreaList()
			)
			);
		
		// Defines the item clicked event
		this.getGeographicalAreaListView().setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				GeographicalAreaListFragment.this.onGeographicalAreaItemClicked(position);
			}
		});
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
		this.geographicalAreaListView = null;
		this.view = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		this.loadGeographicalAreaListView();
	}
	
	/**
	 * Occurs on GeographicalArea item is clicked
	 * 
	 * @param position Selected item position in the ListView
	 */
	public void onGeographicalAreaItemClicked(int position) {
		this.getMainActivity().browseToThemeList(
			this.getGeographicalAreaList().get(position)
			);
	}
}