package com.kioube.tourapp.android.client.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.ui.filter.TourItemImageFilter;

/**
 * 
 * TourItemFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class ImageFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = ImageFragment.class.getSimpleName();
	
	/* --- Fields --- */

	private View view;
	
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
	 * Gets the Fragment TourItem to render
	 */
	public TourItemImage getTourItemImage() {
		return this.getFilter().getTourItemImage();
	}
	
	/**
	 * Gets the fragment filter used to filter theme results
	 */
	public TourItemImageFilter getFilter() {
		return (TourItemImageFilter) super.getFilter();
	}
	
	/**
	 * Sets the fragment filter used to filter results
	 * 
	 * @param filter The filter used to filter results in the view
	 */
	public void setFilter(TourItemImageFilter filter) {
		super.setFilter(filter);
	}	
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_image, container, false);
		
		// Sets the image
		ImageView imageView = (ImageView) this.getView().findViewById(R.id.image);
		
		if (this.getTourItemImage().getByteCode() != null && this.getTourItemImage().getByteCode().length() > 0) {
			
			String imageBase64 = this.getTourItemImage().getByteCode();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}
		
		
		return view;
	}
}