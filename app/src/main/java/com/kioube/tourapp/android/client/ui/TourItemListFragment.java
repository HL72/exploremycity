package com.kioube.tourapp.android.client.ui;

import java.util.List;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;
import com.kioube.tourapp.android.client.ui.adapter.TourItemItemAdapter;
import com.kioube.tourapp.android.client.ui.filter.TourItemListFilter;

/**
 * 
 * TourItemListFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemListFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = TourItemListFragment.class.getSimpleName();
	
	/* --- Fields --- */
	
	private ListView tourItemListView;
	private TourItemRepository tourItemRepository = new TourItemRepository(this.getActivity());
	private List<TourItem> tourItemList;
	private View view;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the fragment filter used to filter theme results
	 */
	public TourItemListFilter getFilter() {
		return (TourItemListFilter) super.getFilter();
	}
	
	/**
	 * Sets the fragment filter used to filter results
	 * 
	 * @param filter The filter used to filter results in the view
	 */
	public void setFilter(TourItemListFilter filter) {
		super.setFilter(filter);
		
		// Resets the TourItem objects list to reload it on lazy demand
		this.tourItemList = null;
	}
	
	/**
	 * Gets the tourItemListView object's areaListView value
	 * 
	 * @return The tourItemListView object's areaListView value
	 */
	public ListView getTourItemListView() {
		if (this.tourItemListView == null) {
			this.tourItemListView = (ListView) this.getView().findViewById(R.id.touritem_list_view);
		}
		
		return this.tourItemListView;
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
	 * Gets the TourItem objects to render depending on the fragment filter
	 * 
	 * @return The TourItem objects to render depending on the fragment filter
	 */
	public List<TourItem> getTourItemList() {
		
		if (this.tourItemList == null) {
			TourItemListFilter filter = this.getFilter();
			
			if (filter.getBookmarksOnly() == true) {
				this.tourItemList = this.tourItemRepository.getBookmarked();
			}
			else if (filter.getGeographicalArea() != null && filter.getTheme() != null) {
				if (filter.getKeyword() != null) {
					this.tourItemList = this.tourItemRepository.getByFilter(
						this.getFilter().getGeographicalArea(),
						this.getFilter().getTheme(),
						this.getFilter().getKeyword()
					);					
				}
				else {
					this.tourItemList = this.tourItemRepository.getByFilter(
						this.getFilter().getGeographicalArea(),
						this.getFilter().getTheme()
					);
				}
			}
			else {
				Log.e(LOG_TAG, "Invalid TourItemListFilter used to render TourItem objects.");
			}
		}
		
		return this.tourItemList;
	}
	
	/* --- Object operations --- */	
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_touritem_list, container, false);
		
		// Sets fragment title
		if (this.getFilter().getBookmarksOnly()) {
			this.getMainActivity().setViewTitle(
				this.getResources().getString(R.string.bookmarks)
			);
		}
		else {
			this.getMainActivity().setViewTitle(
				this.getResources().getString(R.string.poi_list)
			);
		}
		
		// Loads area list view
		this.loadTourItemListView();
		
		return this.view;
	}

	/**
	 * Loads the TourItem objects ListView
	 */
	private void loadTourItemListView() {
		
		List<TourItem> itemList = this.getTourItemList();
		TextView noResultTextView = (TextView) this.getView().findViewById(R.id.no_result_text_view);
		
		if (itemList.size() > 0) {
			noResultTextView.setVisibility(View.GONE);
			
			// Adapts it to the TourItem objects list view
			this.getTourItemListView().setAdapter(
				new TourItemItemAdapter(
					this.getActivity(),
					R.layout.item_touritem_list_view,
					itemList
				)
			);
			
			// Defines the item clicked event
			this.getTourItemListView().setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					TourItemListFragment.this.onTourItemItemClicked(position);
				}
			});
		}
		else {
			noResultTextView.setVisibility(View.VISIBLE);
		}
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
		this.tourItemListView = null;
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
		
		this.loadTourItemListView();
	}
	
	/**
	 * Occurs on TourItem object item is clicked
	 * 
	 * @param position Selected item position in the ListView
	 */
	public void onTourItemItemClicked(int position) {
		this.getMainActivity().browseToTourItem(
			this.getTourItemList().get(position)
		);
	}
}