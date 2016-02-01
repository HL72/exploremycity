package com.kioube.tourapp.android.client.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kioube.tourapp.android.client.persistence.repository.ConfigurationRepository;
import com.kioube.tourapp.android.client.ui.filter.FilterBase;

/**
 * 
 * FragmentBase type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class FragmentBase extends Fragment {
	
	/* --- Constants --- */
	
	public static final String FILTER_BUNDLE_KEY = "filter";
	
	/* --- Fields --- */
	
	private ConfigurationRepository configurationRepository = new ConfigurationRepository(this.getActivity());
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the XFraction used for animations
	 * 
	 * @return The XFraction used for animations
	 */
	public float getXFraction() {
		int width = this.getView().getWidth() > 0 ? this.getView().getWidth() : 1;
		return this.getView().getX() / width;
	}
	
	/**
	 * Sets the XFraction used for animations
	 * 
	 * @param xFraction the XFraction used for animations to set
	 */
	public void setXFraction(float xFraction) {
		final int width = this.getView().getWidth();
		this.getView().setX((width > 0) ? (xFraction * width) : -9999);
	}
	
	/**
	 * Gets the fragment MainActivity (usually its enclosed activity)
	 * @return The fragment MainActivity
	 */
	public MainActivity getMainActivity() {
		return (MainActivity) this.getActivity();
	}
	
	/**
	 * Gets the fragment filter
	 */
	public FilterBase getFilter() {
		FilterBase result = null;
		Bundle bundle = this.getArguments();
		
		if ((bundle != null) && (bundle.containsKey(FILTER_BUNDLE_KEY))) {
			result = (FilterBase) bundle.getSerializable(FILTER_BUNDLE_KEY);
		}
		
		return result;
	}
	
	/**
	 * Sets the fragment filter
	 * 
	 * @param filter The fragment filter
	 */
	public void setFilter(FilterBase filter) {
		Bundle bundle = this.getArguments();
		
		if (bundle == null) {
			bundle = new Bundle();
		}
		
		bundle.putSerializable(FILTER_BUNDLE_KEY, filter);
		
		this.setArguments(bundle);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {		
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	/* (non-Javadoc)
	 * @see android.app.Fragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		
		// Sets global background color
		view.setBackgroundColor(
			configurationRepository.getBackgroundColor()
		);
	}
}
