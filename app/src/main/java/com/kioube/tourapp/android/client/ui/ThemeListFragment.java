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
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.persistence.repository.ThemeRepository;
import com.kioube.tourapp.android.client.ui.adapter.ThemeItemAdapter;
import com.kioube.tourapp.android.client.ui.filter.GeographicalAreaListFilter;
import com.kioube.tourapp.android.client.ui.filter.ThemeListFilter;

/**
 * 
 * ThemeListFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class ThemeListFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = ThemeListFragment.class.getSimpleName();
	
	/* --- Fields --- */
	
	private List<Theme> themeList;
	private ListView themeListView;
	private ThemeRepository themeRepository = new ThemeRepository(this.getActivity());
	private View view;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the fragment filter used to filter theme results
	 */
	public ThemeListFilter getFilter() {
		return (ThemeListFilter) super.getFilter();
	}
	
	/**
	 * Sets the fragment filter used to filter results
	 * 
	 * @param filter The filter used to filter results in the view
	 */
	public void setFilter(ThemeListFilter filter) {
		super.setFilter(filter);
		
		// Resets the theme list to reload it on lazy demand
		this.themeList = null;
	}
	
	/**
	 * Gets the ThemeListView object's themeListView value
	 * 
	 * @return The ThemeListView object's themeListView value
	 */
	public ListView getThemeListView() {
		if (this.themeListView == null) {
			this.themeListView = (ListView) this.getView().findViewById(R.id.theme_list_view);
		}
		
		return this.themeListView;
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
	public List<Theme> getThemeList() {
		
		if (this.themeList == null) {
			
			// TODO [2014-03-21, JMEL] Filter Theme objects which have persistent TourItem objects matching the GeograpĥicalArea 
			this.themeList = this.themeRepository.getAll();
		}
		
		return this.themeList;
	}
	
	/**
	 * Sets the fragment filter
	 * 
	 * @param filter The fragment filter to set
	 */
	public void setFilter(GeographicalAreaListFilter filter) {
		super.setFilter(filter);
	}
	
	/* --- .ctors --- */
	
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
		this.view = inflater.inflate(R.layout.fragment_theme_list, container, false);
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.themes)
		);
		
		// Loads area list view
		this.loadThemeListView();
		
		return this.view;
	}
	
	/**
	 * Loads the Themes ListView
	 */
	private void loadThemeListView() {
		
		// Put itms in the theme items ListView
		this.getThemeListView().setAdapter(
			new ThemeItemAdapter(
				this.getActivity(),
				R.layout.item_theme_list_view,
				this.getThemeList(),
				this.getFilter().getGeographicalArea()
			)
			);
		
		// Defines the item clicked event
		this.getThemeListView().setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				ThemeListFragment.this.onThemeItemClicked(position);
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
		this.themeListView = null;
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
		
		this.loadThemeListView();
	}
	
	/**
	 * Occurs on Theme item is clicked
	 * 
	 * @param position Selected item position in the ListView
	 */
	public void onThemeItemClicked(int position) {
		this.getMainActivity().browseToTourItemList(
			this.getFilter().getGeographicalArea(),
			this.getThemeList().get(position)
		);
	}
}