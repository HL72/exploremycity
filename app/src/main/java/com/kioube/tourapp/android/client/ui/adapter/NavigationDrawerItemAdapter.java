package com.kioube.tourapp.android.client.ui.adapter;

import java.util.List;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.ui.domain.NavigationDrawerItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

/**
 * 
 * @author Julien Mellerin
 * 
 */
public class NavigationDrawerItemAdapter extends ArrayAdapter<NavigationDrawerItem> {

	/* --- Constants --- */

	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_navigation_list_view;

	/* --- Fields --- */

	private final Context context;
	private final int itemLayoutId;

	/* --- Getters and setters --- */

	/* --- Constructors --- */

	public NavigationDrawerItemAdapter(Context context, List<NavigationDrawerItem> objects) {

		this(context, DEFAULT_ITEM_LAYOUT_ID, objects);
	}

	public NavigationDrawerItemAdapter(Context context, int resource, List<NavigationDrawerItem> objects) {
		super(context, resource, objects);

		this.context = context;
		this.itemLayoutId = resource;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;

		NavigationDrawerItem item = this.getItem(position);

		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView labelTextView = (TextView) result.findViewById(R.id.label);
		TextView subLabelTextView = (TextView) result.findViewById(R.id.sub_label);
		ImageView iconView = (ImageView) result.findViewById(R.id.icon);
		TextView counterTextView = (TextView) result.findViewById(R.id.counter);

		// Sets item label
		labelTextView.setText(item.getLabel());

		// Sets item subLabel
		
		if (item.getSubLabel() != null && !item.getSubLabel().isEmpty()) {
			subLabelTextView.setText(item.getSubLabel());			
		}
		else {
			subLabelTextView.setVisibility(View.GONE);
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			layoutParams.addRule(RelativeLayout.RIGHT_OF, iconView.getId());
			labelTextView.setLayoutParams(layoutParams);
		}

		// Sets item icon
		
		if (item.getImageResourceId() > 0) {
			iconView.setImageResource(item.getImageResourceId());
		}
		else {
			iconView.setVisibility(View.GONE);
		}

		// Sets item counter
		
		if (item.getCount() != Integer.MIN_VALUE) {
			counterTextView.setText(
				String.valueOf(item.getCount())
			);			
		}
		else {
			counterTextView.setVisibility(View.GONE);
		}

		return result;
	}
}
