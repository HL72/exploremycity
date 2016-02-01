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
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;

/**
 * 
 * ThemeItemAdapter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class ThemeItemAdapter extends ArrayAdapter<Theme> {
	
	/* --- Constants --- */

	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_theme_list_view;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */

	private final Context context;
	private final int itemLayoutId;
	private final TourItemRepository tourItemRepository;
	
	private GeographicalArea geographicalArea;
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */

	public ThemeItemAdapter(Context context, List<Theme> objects, GeographicalArea geographicalArea) {

		this(context, DEFAULT_ITEM_LAYOUT_ID, objects, geographicalArea);
	}

	public ThemeItemAdapter(Context context, int resource, List<Theme> objects, GeographicalArea geographicalArea) {
		super(context, resource, objects);

		this.context = context;
		this.itemLayoutId = resource;
		this.tourItemRepository = new TourItemRepository(context);
		this.geographicalArea = geographicalArea;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;

		Theme item = this.getItem(position);

		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView nameTextView = (TextView) result.findViewById(R.id.name);
		ImageView imageView = (ImageView) result.findViewById(R.id.image);
		TextView counterTextView = (TextView) result.findViewById(R.id.counter);

		// Sets item name
		nameTextView.setText(item.getName());
		
		// Sets item image
		if (item.getImage() != null && item.getImage().length() > 0) {
			
			String imageBase64 = item.getImage();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}

		// Sets item counter
		long tourItemsCount = this.tourItemRepository.getCountByFilter(this.geographicalArea, item);
		counterTextView.setText(
			String.valueOf(tourItemsCount)
		);

		return result;
	}
	
}
