package com.kioube.tourapp.android.client.ui.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.Message;

/**
 * 
 * MessageItemAdapter type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class MessageItemAdapter extends ArrayAdapter<Message> {
	
	/* --- Constants --- */

	@SuppressWarnings("unused")
	private static final String LOG_TAG = MessageItemAdapter.class.getSimpleName();
	private final static int DEFAULT_ITEM_LAYOUT_ID = R.layout.item_message_list_view;
	
	/* --- Static fields --- */
	
	/* --- Fields --- */
	
	private final Context context;
	private final int itemLayoutId;
	
	/* --- Getters & setters --- */
	
	/* --- .ctors --- */
	
	/**
	 * Constructs a new TourItemItemAdapter object.
	 * 
	 * @param context
	 * @param objects
	 */
	public MessageItemAdapter(Context context, List<Message> objects) {
		
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
	public MessageItemAdapter(Context context, int resource, List<Message> objects) {
		super(context, resource, objects);
		
		this.context = context;
		this.itemLayoutId = resource;
	}
	
	/* --- Class operations --- */
	
	/* --- Object operations --- */
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View result = null;
		
		Message item = this.getItem(position);
		
		// Inflates item layout
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		result = inflater.inflate(this.itemLayoutId, parent, false);
		
		// Get required views
		TextView subjectTextView = (TextView) result.findViewById(R.id.subject);
		TextView bodyTextView = (TextView) result.findViewById(R.id.body);
		
		// Sets item subject
		subjectTextView.setText(item.getSubject());
		
		// Sets item body
		bodyTextView.setText(item.getText());
		
		return result;
	}
	
}
