package com.kioube.tourapp.android.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.Coordinate;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.helper.InternetConnectionHelper;
import com.kioube.tourapp.android.client.persistence.repository.ConfigurationRepository;
import com.kioube.tourapp.android.client.persistence.repository.CoordinateRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemImageRepository;
import com.kioube.tourapp.android.client.ui.adapter.ActionItemAdapter;
import com.kioube.tourapp.android.client.ui.domain.ActionItem;
import com.kioube.tourapp.android.client.ui.filter.TourItemFilter;

/**
 * 
 * TourItemFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = TourItemFragment.class.getSimpleName();
	
	/* --- Fields --- */

	private View view;
	private List<ActionItem> actionItemList;
	private Coordinate coordinate;
	private CoordinateRepository coordinateRepository = new CoordinateRepository(this.getActivity());
	private TourItemImageRepository tourItemImageRepository = new TourItemImageRepository(this.getActivity());
	private ConfigurationRepository configurationRepository = new ConfigurationRepository(this.getActivity());
	private InternetConnectionHelper internetConnectionHelper;
	
	/* --- Getters and setters --- */
	
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
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		this.internetConnectionHelper = new InternetConnectionHelper(this.getActivity());
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_touritem, container, false);
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getTourItem().getName()
		);
		
		// Sets the actions grid
		this.setUpActionGrid();
		
		// Sets top image
		List<TourItemImage> imageList = this.tourItemImageRepository.getByTourItem(this.getTourItem());
		
		if ((imageList != null) && (imageList.size() > 0)) {
			ImageView imageView = (ImageView) this.getView().findViewById(R.id.picture);
			
			String imageBase64 = imageList.get(0).getByteCode();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}
		
		// Sets the summary
		TextView summaryView = (TextView) this.getView().findViewById(R.id.summary);
		summaryView.setText(this.getTourItem().getSummary());
		summaryView.setTextColor(this.configurationRepository.getTitleColor());
		
		// Sets the description
		TextView descriptionView = (TextView) this.getView().findViewById(R.id.description);
		descriptionView.setText(this.getTourItem().getDescription());
		descriptionView.setTextColor(this.configurationRepository.getDescriptionColor());
		
		return view;
	}
	
	/**
	 * Sets the actions grid
	 */
	private void setUpActionGrid() {
		this.actionItemList = new ArrayList<ActionItem>();
		
		// Map action
		this.actionItemList.add(new ActionItem(
			this.getResources().getString(R.string.map),
			R.drawable.ic_map,
			new Runnable() {				
				@Override
				public void run() {
					// Checks internet connection
					if (TourItemFragment.this.internetConnectionHelper.isInternetConnectionAvailable()) {
						TourItemFragment.this.getMainActivity().browseToTourItemMap(
							TourItemFragment.this.getTourItem()
						);
					}
					else {
						TourItemFragment.this.getMainActivity().showNoNetworkAlert();
					}
				}
			}
		));
		
		// Gallery action
		this.actionItemList.add(new ActionItem(
			this.getResources().getString(R.string.gallery),
			R.drawable.ic_book,
			new Runnable() {				
				@Override
				public void run() {
					TourItemFragment.this.getMainActivity().browseToTourItemGallery(
						TourItemFragment.this.getTourItem()
					);
				}
			}
		));
		
		// Email action
		this.actionItemList.add(new ActionItem(
			this.getResources().getString(R.string.email),
			R.drawable.ic_mail,
			new Runnable() {				
				@Override
				public void run() {
					TourItemFragment.this.sendEmail();
				}
			}
		));
		
		// Www action
		this.actionItemList.add(new ActionItem(
			this.getResources().getString(R.string.visit_website),
			R.drawable.ic_link,
			new Runnable() {				
				@Override
				public void run() {
					// Checks internet connection
					if (TourItemFragment.this.internetConnectionHelper.isInternetConnectionAvailable()) {
						TourItemFragment.this.openWebsite();
					}
					else {
						TourItemFragment.this.getMainActivity().showNoNetworkAlert();
					}
				}
			}
		));
		
		// Phone action
		this.actionItemList.add(new ActionItem(
			this.getResources().getString(R.string.call),
			R.drawable.ic_phone,
			new Runnable() {				
				@Override
				public void run() {
					TourItemFragment.this.callPhone();
				}
			}
		));
		
		// Adapts the ActionItem objects list to the GridView
		GridView actionGridView = (GridView) this.getView().findViewById(R.id.action_grid_view);
		
		actionGridView.setAdapter(new ActionItemAdapter(
			this.getActivity(),
			this.actionItemList
		));
		
		// Sets the item clicked event
		actionGridView.setOnItemClickListener(new OnItemClickListener() {
			
			@Override
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
				TourItemFragment.this.actionItemList.get(position).getRunnable().run();
	        }
	    });
	}
	
	/**
	 * Opens the mail client to the TourItem email
	 */
	public void sendEmail() {
		Intent intent = new Intent(Intent.ACTION_SEND);
		intent.setType("plain/text");
		intent.putExtra(Intent.EXTRA_EMAIL, new String[] { this.getCoordinate().getEmail() });
		
		this.startActivity(Intent.createChooser(intent, ""));
	}
	
	/**
	 * Opens the web browser to the TourItem website
	 */
	public void openWebsite() {
		Uri url = Uri.parse(this.getCoordinate().getWebsite());
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, url);
		
		this.startActivity(launchBrowser);
	}
	
	public void callPhone() {
		Intent intent = new Intent(Intent.ACTION_CALL);

		intent.setData(Uri.parse("tel:" + this.getCoordinate().getPhone()));
		this.startActivity(intent);
	}
}