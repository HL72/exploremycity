package com.kioube.tourapp.android.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.helper.SessionManager;
import com.kioube.tourapp.android.client.helper.SynchronizationPreference;

/**
 * 
 * SettingsFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class SettingsFragment extends FragmentBase {
	
	/* --- Fields --- */

	private List<SynchronizationModeItem> synchronizationModeItemList;
	private View view;
	private SessionManager sessionManager;
	private Spinner synchronizationModeSpinner;
	
	/* --- Accessors --- */
	
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
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		this.sessionManager = new SessionManager(this.getActivity());
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_settings, container, false);
		
		// Inits synchronization mode options
		this.synchronizationModeItemList = new ArrayList<SynchronizationModeItem>();
		this.synchronizationModeItemList.add(
			new SynchronizationModeItem(this.getString(R.string.sync_mode_auto), SynchronizationPreference.AUTOMATIC)
		);
		this.synchronizationModeItemList.add(
			new SynchronizationModeItem(this.getString(R.string.sync_mode_ask), SynchronizationPreference.ASK)
		);
		this.synchronizationModeItemList.add(
			new SynchronizationModeItem(this.getString(R.string.sync_mode_never), SynchronizationPreference.NEVER)
		);
		
		// Gets mode labels
		List<String> labels = new ArrayList<String>();
		for (SynchronizationModeItem item : this.synchronizationModeItemList) {
			labels.add(item.getLabel());
		}
		
		// Adapts them to the sync mode spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, labels);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		
		synchronizationModeSpinner = (Spinner) this.getView().findViewById(R.id.sync_mode_spinner);
		synchronizationModeSpinner.setAdapter(dataAdapter);
		
		// Sets selected item based on preferences		
		for (int i = 0; i < this.synchronizationModeItemList.size(); i++) {
			if (this.synchronizationModeItemList.get(i).getPreference() == this.sessionManager.getSynchronizationPreference()) {
				synchronizationModeSpinner.setSelection(i);
				break;
			}
		}
		
		// Sets the save button action
		Button saveButton = (Button) this.getView().findViewById(R.id.save_button);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				SettingsFragment.this.setSynchronizationMode();
				SettingsFragment.this.getMainActivity().onBackPressed();
			}
		});
		
		return view;
	}
	
	/**
	 * Sets the synchronization mode based on user selection
	 */
	protected void setSynchronizationMode() {
		int position = this.synchronizationModeSpinner.getSelectedItemPosition();
		SynchronizationPreference preference = this.synchronizationModeItemList.get(position).getPreference();
		
		this.sessionManager.setSynchronizationPreference(preference);
	}

	public class SynchronizationModeItem {
		
		/* --- Fields --- */
		
		private String label;
		private SynchronizationPreference preference;
		
		/* --- Getters --- */
		
		/**
		 * Gets the item label
		 * @return The item label
		 */
		public String getLabel() {
			return this.label;
		}
		
		/**
		 * Gets the synchronization preference
		 * @return The synchronization preference
		 */
		public SynchronizationPreference getPreference() {
			return this.preference;
		}
		
		/* --- .ctors --- */
		
		/**
		 * 
		 * Constructs a new SynchronizationModeItem object.
		 * @param label
		 * @param preference
		 */
		public SynchronizationModeItem(String label, SynchronizationPreference preference) {
			this.label = label;
			this.preference = preference;
		}
	}
	
}
