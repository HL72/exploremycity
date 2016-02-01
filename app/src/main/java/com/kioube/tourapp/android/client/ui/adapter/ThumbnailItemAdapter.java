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

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.TourItemImage;

/**
 * 
 * @author Julien Mellerin
 * 
 */
public class ThumbnailItemAdapter extends ArrayAdapter<TourItemImage> {
	
	/* --- Constants --- */
	
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_gallery_grid_view;
	
	/* --- Fields --- */
	
	private final Context context;
	private final int itemLayoutId;
	
	/* --- Getters and setters --- */
	
	/* --- Constructors --- */
	
	public ThumbnailItemAdapter(Context context, List<TourItemImage> objects) {
		
		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
	}
	
	public ThumbnailItemAdapter(Context context, int resource, List<TourItemImage> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.itemLayoutId = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;
		
		TourItemImage item = this.getItem(position);
		
		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		ImageView thumbnailView = (ImageView) result.findViewById(R.id.thumbnail);

		// Sets item thumbnail
		if (item.getByteCode() != null && item.getByteCode().length() > 0) {
			
			String imageBase64 = item.getByteCode();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			thumbnailView.setImageBitmap(bitmap);
		}
		
		return result;
	}
}
