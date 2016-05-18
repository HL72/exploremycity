package com.kioube.tourapp.android.client.ui;

import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.persistence.repository.ConfigurationRepository;
import com.kioube.tourapp.android.client.persistence.repository.GeographicalAreaRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemImageRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;

/**
 * 
 * HomeFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class HomeFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = HomeFragment.class.getSimpleName();
	
	/* --- Fields --- */
	
	private EditText quickSearchEditText;
	private ImageButton quickSearchButton;
	private ImageView headerImageView;
	private ImageView navigationHeaderImageView;
	private View view;
	private GeographicalAreaRepository geographicalAreaRepository = new GeographicalAreaRepository(this.getActivity());
	private List<GeographicalArea> geographicalAreaList;
	private TourItemRepository tourItemRepository = new TourItemRepository(this.getActivity());
	private List<TourItem> tourItemList;
	private TourItemImageRepository tourItemImageRepository = new TourItemImageRepository(this.getActivity());
	private List<TourItemImage> tourItemImagesList;
	private TextView headlineTextView;
	
	private int currentAreaPosition = 0;
	private ImageView areaSelectorRightButton;
	private ImageView areaSelectorLeftButton;
	private ImageView areaSelectorImage;
	private TextView areaSelectorTitleTextView;
	private TextView areaSelectorPageTextView;
	private ConfigurationRepository configurationRepository = new ConfigurationRepository(this.getActivity());
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the HomeFragment object's areaSelectorRightButton value
	 * 
	 * @return The HomeFragment object's areaSelectorRightButton value
	 */
	public ImageView getAreaSelectorRightButton() {
		if (this.areaSelectorRightButton == null) {
			this.areaSelectorRightButton = (ImageView) this.getView().findViewById(R.id.area_selector_right_button);
		}
		
		return this.areaSelectorRightButton;
	}
	
	/**
	 * Gets the HomeFragment object's areaSelectorLeftButton value
	 * 
	 * @return The HomeFragment object's areaSelectorLeftButton value
	 */
	public ImageView getAreaSelectorLeftButton() {
		if (this.areaSelectorLeftButton == null) {
			this.areaSelectorLeftButton = (ImageView) this.getView().findViewById(R.id.area_selector_left_button);
		}
		
		return this.areaSelectorLeftButton;
	}
	
	/**
	 * Gets the HomeFragment object's areaSelectorImage value
	 * 
	 * @return The HomeFragment object's areaSelectorImage value
	 */
	public ImageView getAreaSelectorImage() {
		if (this.areaSelectorImage == null) {
			this.areaSelectorImage = (ImageView) this.getView().findViewById(R.id.area_selector_image);
		}
		
		return this.areaSelectorImage;
	}
	
	/**
	 * Gets the HomeFragment object's areaSelectorPageTextView value
	 * 
	 * @return The HomeFragment object's areaSelectorPageTextView value
	 */
	public TextView getAreaSelectorPageTextView() {
		if (this.areaSelectorPageTextView == null) {
			this.areaSelectorPageTextView = (TextView) this.getView().findViewById(R.id.area_selector_page_text_view);
		}
		
		return this.areaSelectorPageTextView;
	}
	
	/**
	 * Gets the HomeFragment object's areaSelectorTitleTextView value
	 *
	 * @return The HomeFragment object's areaSelectorTitleTextView value
	 */
	public TextView getAreaSelectorTitleTextView() {
		if (this.areaSelectorTitleTextView == null) {
			this.areaSelectorTitleTextView = (TextView) this.getView().findViewById(R.id.area_selector_title_text_view);
		}
		
		return this.areaSelectorTitleTextView;
	}

	/**
	 * Gets the HomeFragment object's geographicalAreaList value
	 * 
	 * @return The HomeFragment object's geographicalAreaList value
	 */
	public List<GeographicalArea> getGeographicalAreaList() {
		if (this.geographicalAreaList == null) {
			this.geographicalAreaList = this.geographicalAreaRepository.getAll();
		}
		
		return this.geographicalAreaList;
	}

	//OGT
	public List<TourItem> getTourItemList() {
		if (this.tourItemList == null) {
			this.tourItemList = this.tourItemRepository.getAll();
		}

		return this.tourItemList;
	}

	/**
	 * Gets the HomeFragment object's navigationHeaderImageView value
	 * 
	 * @return The HomeFragment object's navigationHeaderImageView value
	 */
	public ImageView getNavigationHeaderImageView() {
		if (this.navigationHeaderImageView == null) {
			
			// Gets the ImageView from the MainActivity view
			MainActivity parentActivity = (MainActivity) this.getActivity();
			this.navigationHeaderImageView = parentActivity.getNavigationHeaderImageView();
		}
		
		return this.navigationHeaderImageView;
	}
	
	/**
	 * Gets the headline textview
	 * 
	 * @return The headline textview
	 */
	public TextView getHeadlineTextView() {
		if (this.headlineTextView == null) {
			this.headlineTextView = (TextView) this.getView().findViewById(R.id.headline_text_view);
		}
		
		return this.headlineTextView;
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
	 * Gets the HomeFragment object's quickSearchEditText value
	 * 
	 * @return The HomeFragment object's quickSearchEditText value
	 */
	public EditText getQuickSearchEditText() {
		if (this.quickSearchEditText == null) {
			this.quickSearchEditText = (EditText) this.getView().findViewById(R.id.quick_search_edit_text);
		}

		return this.quickSearchEditText;
	}
	
	/**
	 * Gets the HomeFragment object's quickSearchButton value
	 * 
	 * @return The HomeFragment object's quickSearchButton value
	 */
	public ImageButton getQuickSearchButton() {
		if (this.quickSearchButton == null) {
			this.quickSearchButton = (ImageButton) this.getView().findViewById(R.id.quick_search_button);
		}
		
		return this.quickSearchButton;
	}
	
	/**
	 * Gets the HomeFragment object's headerImageView value
	 * 
	 * @return The HomeFragment object's headerImageView value
	 */
	public ImageView getHeaderImageView() {
		if (this.headerImageView == null) {
			this.headerImageView = (ImageView) this.getView().findViewById(R.id.header_image_view);
		}
		
		return this.headerImageView;
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
		this.view = inflater.inflate(R.layout.fragment_home, container, false);
		
		this.loadHeaderImages();
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.app_name)
			);
		
		// Search button click event
		this.getQuickSearchButton().setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// OGT - Suppression de l'action du bouton de recherche de la page home
				//HomeFragment.this.getMainActivity().browseToSearch(
				//	HomeFragment.this.getQuickSearchEditText().getText().toString()
				//);
                HomeFragment.this.getMainActivity().browseToTourItemList(HomeFragment.this.getQuickSearchEditText().getText().toString());
			}
		});
		
		this.setUpAreaSelector();
		
		// Sets the headline
		String headline = this.configurationRepository.getHeadline();
		this.getHeadlineTextView().setText(headline != null ? headline : this.getString(R.string.app_name));
		
		return view;
	}
	
	/**
	 * Setups the area selector
	 */
	public void setUpAreaSelector() {
		//OGT
		if (this.getTourItemList() != null && this.getTourItemList().size() > 0)
		{
			this.displayArea(0);

			// Sets the left button click event
			this.getAreaSelectorLeftButton().setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (HomeFragment.this.currentAreaPosition <= 0) {
						HomeFragment.this.displayArea(HomeFragment.this.getTourItemList().size() - 1);
					}
					else {
						HomeFragment.this.displayArea(HomeFragment.this.currentAreaPosition - 1);
					}
				}
			});

			// Sets the right button click event
			this.getAreaSelectorRightButton().setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					if (HomeFragment.this.currentAreaPosition >= HomeFragment.this.getTourItemList().size() - 1) {
						HomeFragment.this.displayArea(0);
					}
					else {
						HomeFragment.this.displayArea(HomeFragment.this.currentAreaPosition + 1);
					}
				}
			});

			// Sets the image click event
			this.getAreaSelectorImage().setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					HomeFragment.this.getMainActivity().browseToTourItem(
							HomeFragment.this.getTourItemList().get(
									HomeFragment.this.currentAreaPosition
							)
					);
				}
			});
		}
		else {
			Log.e(LOG_TAG, "No GeographicalArea objects to render in the area selector.");
		}
	}

	
	/**
	 * Display the specified area mamtching the position in the area selector
	 * @param position Position of the area to render in the area list
	 */
	public void displayArea(int position) {
		this.currentAreaPosition = position;

		//OGT
		if (position <= this.getTourItemList().size() - 1) {
			TourItem item = this.getTourItemList().get(position);
			List<TourItemImage> imageList = this.tourItemImageRepository.getByTourItem(item);

			this.getAreaSelectorTitleTextView().setText(item.getName());
			this.getAreaSelectorPageTextView().setText(
					String.format("%d / %d", position + 1, this.getTourItemList().size())
			);

			// Sets area image
			if (imageList != null && imageList.size() > 0) {

				ImageView imageView = (ImageView) this.getView().findViewById(R.id.picture);

				String imageBase64 = imageList.get(0).getByteCode();

				byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
				Bitmap bitmap = BitmapFactory.decodeByteArray(imageData, 0, imageData.length);

				this.getAreaSelectorImage().setImageBitmap(bitmap);
			}
		}
		else {
			Log.e(LOG_TAG, "Out of bound position.");
		}
	}
	
	/**
	 * Loads, resize and replace header and navigation header images
	 */
	public void loadHeaderImages() {
		
		// Loads the custom header image if provided
		String imageBase64 = configurationRepository.getMainPicture();
		if (imageBase64 != null) {
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			this.getHeaderImageView().setImageBitmap(bitmap);
		}
		
		// Gets window width in DP
		DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
		float windowDpWidth = displayMetrics.widthPixels / displayMetrics.density;
		
		// Gets image width in DP
		Drawable headerDrawable = this.getHeaderImageView().getDrawable();
		Bitmap headerBitmap = ((BitmapDrawable) headerDrawable).getBitmap();
		
		// Scales the header bitmap to match the exact window width
		float ratio = windowDpWidth / (headerBitmap.getWidth() / displayMetrics.density);
		Bitmap scaledBitmap = Bitmap.createScaledBitmap(
			headerBitmap,
			Math.round(headerBitmap.getWidth() * ratio),
			Math.round(headerBitmap.getHeight() * ratio),
			false
			);
		
		// And crops the image into a new one to fit the navigation drawer width
		Bitmap croppedBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth() / Math.round(windowDpWidth) * 240, scaledBitmap.getHeight());
		
		// Sets the new navigation header image drawable
		this.getNavigationHeaderImageView().setImageBitmap(croppedBitmap);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
		// Clears the inner views accessors
		this.quickSearchButton = null;
		this.quickSearchEditText = null;
		
		this.areaSelectorRightButton = null;
		this.areaSelectorLeftButton = null;
		this.areaSelectorImage = null;
		this.areaSelectorTitleTextView = null;
		this.areaSelectorPageTextView = null;
		this.headlineTextView = null;
	}
}