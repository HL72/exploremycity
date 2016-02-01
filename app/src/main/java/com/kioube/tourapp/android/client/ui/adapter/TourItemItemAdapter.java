package com.kioube.tourapp.android.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.persistence.repository.TourItemImageRepository;

/**
 * 
 * GeographicalAreaItemAdapter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TourItemItemAdapter extends ArrayAdapter<TourItem> {
	
	/* --- Constants --- */

	private static final String LOG_TAG = TourItemItemAdapter.class.getSimpleName();
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_touritem_list_view;
	
	/* --- Static fields --- */
	
	
	/* --- Fields --- */
	
	private final Context context;
	private final int itemLayoutId;
	private TourItemImageRepository tourItemImageRepository;
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemItemAdapter object.
	 * 
	 * @param context
	 * @param objects
	 */
	public TourItemItemAdapter(Context context, List<TourItem> objects) {
		
		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
		this.tourItemImageRepository = new TourItemImageRepository(context);
	}
	
	/**
	 * 
	 * Constructs a new TourItemItemAdapter object.
	 * 
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public TourItemItemAdapter(Context context, int resource, List<TourItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.itemLayoutId = resource;
		this.tourItemImageRepository = new TourItemImageRepository(context);
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;
		
		TourItem item = this.getItem(position);
		
		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView nameTextView = (TextView) result.findViewById(R.id.name);
		TextView descriptionTextView = (TextView) result.findViewById(R.id.description);
		ImageView imageView = (ImageView) result.findViewById(R.id.image);
		TextView counterTextView = (TextView) result.findViewById(R.id.counter);
		
		// Sets item name
		nameTextView.setText(item.getName());
		
		// Sets item description
		if (item.getDescription() != null && !item.getDescription().isEmpty()) {
			descriptionTextView.setText(item.getSummary());
		}
		else {
			descriptionTextView.setVisibility(View.GONE);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			layoutParams.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
			nameTextView.setLayoutParams(layoutParams);
		}
		
		// Sets item image
		List<TourItemImage> imageList = this.tourItemImageRepository.getByTourItem(item);
		
		if (imageList != null && imageList.size() > 0) {
			
			String imageBase64 = imageList.get(0).getByteCode();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}
		else {
			Log.d(LOG_TAG, "No images for touritem id = " + item.getId());
		}
		
		// No need for counter yet, so hides it
		counterTextView.setVisibility(View.GONE);
		
		return result;
	}
	
}
