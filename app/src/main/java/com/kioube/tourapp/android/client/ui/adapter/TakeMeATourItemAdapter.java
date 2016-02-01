package com.kioube.tourapp.android.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.persistence.repository.TourItemImageRepository;

/**
 * 
 * TakeMeATourItemAdapter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TakeMeATourItemAdapter extends ArrayAdapter<TakeMeATourAdapterItem> {
	
	/* --- Constants --- */

	@SuppressWarnings("unused")
	private static final String LOG_TAG = TakeMeATourItemAdapter.class.getSimpleName();
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_take_me_a_tour_list_view;
	
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
	public TakeMeATourItemAdapter(Context context, List<TakeMeATourAdapterItem> objects) {
		
		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
	}
	
	/**
	 * 
	 * Constructs a new TourItemItemAdapter object.
	 * 
	 * @param context
	 * @param resource
	 * @param objects
	 */
	public TakeMeATourItemAdapter(Context context, int resource, List<TakeMeATourAdapterItem> objects) {
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
		
		TakeMeATourAdapterItem item = this.getItem(position);
		
		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView periodTextView = (TextView) result.findViewById(R.id.period);
		TextView nameTextView = (TextView) result.findViewById(R.id.name);
		TextView descriptionTextView = (TextView) result.findViewById(R.id.description);
		ImageView imageView = (ImageView) result.findViewById(R.id.image);
		
		// Sets item period
		periodTextView.setText(item.getPeriod());
		
		// Sets item name
		nameTextView.setText(item.getTourItem().getName());
		
		// Sets item description
		descriptionTextView.setText(item.getTourItem().getSummary());
		
		// Sets item image
		List<TourItemImage> imageList = this.tourItemImageRepository.getByTourItem(item.getTourItem());
		
		if (imageList != null && imageList.size() > 0) {
			
			String imageBase64 = imageList.get(0).getByteCode();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}
		
		return result;
	}
	
}
