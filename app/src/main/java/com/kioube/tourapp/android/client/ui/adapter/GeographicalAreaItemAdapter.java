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
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;

/**
 * 
 * GeographicalAreaItemAdapter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class GeographicalAreaItemAdapter extends ArrayAdapter<GeographicalArea> {
	
	/* --- Constants --- */

	@SuppressWarnings("unused")
	private static final String LOG_TAG = GeographicalAreaItemAdapter.class.getName();
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_geographical_area_list_view;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */

	private final Context context;
	private final int itemLayoutId;
	private final TourItemRepository tourItemRepository;
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */

	public GeographicalAreaItemAdapter(Context context, List<GeographicalArea> objects) {

		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
	}

	public GeographicalAreaItemAdapter(Context context, int resource, List<GeographicalArea> objects) {
		super(context, resource, objects);

		this.context = context;
		this.itemLayoutId = resource;
		this.tourItemRepository = new TourItemRepository(context);
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;

		GeographicalArea item = this.getItem(position);

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
			descriptionTextView.setText(item.getDescription());			
		}
		else {
			descriptionTextView.setVisibility(View.GONE);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			layoutParams.addRule(RelativeLayout.RIGHT_OF, imageView.getId());
			nameTextView.setLayoutParams(layoutParams);
		}

		// Sets item image
		if (item.getImage() != null && item.getImage().length() > 0) {
			
			String imageBase64 = item.getImage();
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			imageView.setImageBitmap(bitmap);
		}

		// Sets item counter
		long tourItemsCount = this.tourItemRepository.getCountByGeographicalArea(item);
		counterTextView.setText(
			String.valueOf(tourItemsCount)
		);

		return result;
	}
	
}
