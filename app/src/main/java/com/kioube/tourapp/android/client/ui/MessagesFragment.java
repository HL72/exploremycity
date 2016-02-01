package com.kioube.tourapp.android.client.ui;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.Message;
import com.kioube.tourapp.android.client.service.MessageService;
import com.kioube.tourapp.android.client.service.listener.MessageServiceListener;
import com.kioube.tourapp.android.client.service.response.MessageResponse;
import com.kioube.tourapp.android.client.ui.adapter.MessageItemAdapter;

/**
 * 
 * MessagesFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class MessagesFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = MessagesFragment.class.getSimpleName();
	
	/* --- Fields --- */

	private View view;
	private ListView messageListView;
	private TextView infoTextView;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the MessageListView
	 * 
	 * @return The MessageListView
	 */
	public ListView getMessageListView() {
		if (this.messageListView == null) {
			this.messageListView = (ListView) this.getView().findViewById(R.id.message_list_view);
		}
		
		return this.messageListView;
	}
	
	/**
	 * Gets the information TextView
	 * 
	 * @return The information TextView
	 */
	public TextView getInfoTextView() {
		if (this.infoTextView == null) {
			this.infoTextView = (TextView) this.getView().findViewById(R.id.info_text_view);
		}
		
		return this.infoTextView;
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
	
	/*
	 * (non-Javadoc)
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_messages, container, false);
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.messages)
		);
		
		// Loads messages
		this.loadMessages();
		
		return view;
	}

	/**
	 * Loads and render messages
	 */
	private void loadMessages() {
		this.getInfoTextView().setText(this.getView().getContext().getString(R.string.loading));
		
		MessageService messageService = new MessageService(this.getActivity(), new MessageServiceListener() {
			
			@Override
			public void onError(Exception exception) {
				Log.e(LOG_TAG, "Failed to load messages.", exception);
				
				// Shows an error dialog on error
				new AlertDialog.Builder(MessagesFragment.this.getActivity())
					.setMessage("Failed to load messages : " + exception.getMessage())
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton(android.R.string.ok, new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Go back
							MessagesFragment.this.getMainActivity().onBackPressed();							
						}
					})
					.show();
			}
			
			@Override
			public void onCompleted(final MessageResponse response) {
				MessagesFragment.this.getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						MessagesFragment.this.renderMessages(response.getList());
					}
				});
			}
		});
		
		messageService.run();
	}

	/**
	 * Render messages in the view
	 * 
	 * @param list
	 */
	protected void renderMessages(List<Message> messageList) {
		this.getInfoTextView().setText(this.getView().getContext().getString(R.string.no_result));
		
		if (messageList.size() == 0) {
			this.getInfoTextView().setVisibility(View.VISIBLE);
		}
		else {
			this.getInfoTextView().setVisibility(View.GONE);
			
			// Put items in the theme items ListView
			this.getMessageListView().setAdapter(
				new MessageItemAdapter(
					this.getActivity(),
					R.layout.item_message_list_view,
					messageList
				)
			);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
		// Cleans references to inner views to avoid unreferences views on fragment reload
		this.messageListView = null;
		this.infoTextView = null;
	}
}