package com.kioube.tourapp.android.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.ui.domain.ActionItem;

/**
 * 
 * @author Julien Mellerin
 * 
 */
public class ActionItemAdapter extends ArrayAdapter<ActionItem> {
	
	/* --- Constants --- */
	
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_action_grid_view;
	
	/* --- Fields --- */
	
	private final Context context;
	private final int itemLayoutId;
	
	/* --- Getters and setters --- */
	
	/* --- Constructors --- */
	
	public ActionItemAdapter(Context context, List<ActionItem> objects) {
		
		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
	}
	
	public ActionItemAdapter(Context context, int resource, List<ActionItem> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.itemLayoutId = resource;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;
		
		ActionItem item = this.getItem(position);
		
		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView labelTextView = (TextView) result.findViewById(R.id.label);
		ImageView imageView = (ImageView) result.findViewById(R.id.image);
		
		// Sets item label
		labelTextView.setText(item.getLabel());
		
		// Sets item image
		imageView.setImageResource(item.getImageResourceId());
		
		return result;
	}
}
