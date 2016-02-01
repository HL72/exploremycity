package com.kioube.tourapp.android.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;
import com.kioube.tourapp.android.client.service.TakeMeATourService;
import com.kioube.tourapp.android.client.service.listener.TakeMeATourServiceListener;
import com.kioube.tourapp.android.client.service.response.TakeMeATourResponse;
import com.kioube.tourapp.android.client.ui.adapter.TakeMeATourAdapterItem;
import com.kioube.tourapp.android.client.ui.adapter.TakeMeATourItemAdapter;

/**
 * 
 * TakeMeATourFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class TakeMeATourFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	private static final String LOG_TAG = TakeMeATourFragment.class.getSimpleName();
	
	/* --- Fields --- */

	private View view;
	private TextView infoTextView;
	private ListView takeMeATourListView;
	private TourItemRepository tourItemRepository;
	private List<TakeMeATourAdapterItem> takeMeATourItemList;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the TakeMeATourFragment object's takeMeATourItemList value
	 *
	 * @return The TakeMeATourFragment object's takeMeATourItemList value
	 */
	public List<TakeMeATourAdapterItem> getTakeMeATourItemList() {
		return this.takeMeATourItemList;
	}

	/**
	 * Sets the TakeMeATourFragment object's takeMeATourItemList value
	 * @param takeMeATourItemList The TakeMeATourFragment object's takeMeATourItemList value to set
	 */
	public void setTakeMeATourItemListFromService(TakeMeATourResponse response) {

		// Loads tour items from repository
		TakeMeATourAdapterItem morningItem = new TakeMeATourAdapterItem(
			tourItemRepository.getById(response.getMorningItem().getId()),
			this.getActivity().getString(R.string.morning)
		);
		TakeMeATourAdapterItem afternoonItem = new TakeMeATourAdapterItem(
			tourItemRepository.getById(response.getAfternoonItem().getId()),
			this.getActivity().getString(R.string.afternoon)
		);
		TakeMeATourAdapterItem nightItem = new TakeMeATourAdapterItem(
			tourItemRepository.getById(response.getNightItem().getId()),
			this.getActivity().getString(R.string.night)
		);
		
		// Builds the list used to feed the list view
		this.takeMeATourItemList = new ArrayList<TakeMeATourAdapterItem>();
		
		if (morningItem.getTourItem() != null) {
			this.takeMeATourItemList.add(morningItem);
		}
		if (afternoonItem.getTourItem() != null) {
			this.takeMeATourItemList.add(afternoonItem);
		}
		if (nightItem.getTourItem() != null) {
			this.takeMeATourItemList.add(nightItem);
		}
	}

	/**
	 * Gets the TakeMeATourListView
	 * 
	 * @return The TakeMeATourListView
	 */
	public ListView getTakeMeATourListView() {
		if (this.takeMeATourListView == null) {
			this.takeMeATourListView = (ListView) this.getView().findViewById(R.id.take_me_a_tour_list_view);
		}
		
		return this.takeMeATourListView;
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
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		this.tourItemRepository = new TourItemRepository(this.getActivity());
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_take_me_a_tour, container, false);

		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.take_me_a_tour)
		);
		
		// Loads messages
		this.loadTourInfo();
		
		return view;
	}

	/**
	 * Loads tour info
	 */
	private void loadTourInfo() {
		this.getInfoTextView().setText(this.getView().getContext().getString(R.string.loading));
		
		TakeMeATourService service = new TakeMeATourService(this.getActivity(), new TakeMeATourServiceListener() {
			
			@Override
			public void onError(Exception exception) {
				Log.e(LOG_TAG, "Failed to load messages.", exception);
				
				// Shows an error dialog on error
				new AlertDialog.Builder(TakeMeATourFragment.this.getActivity())
					.setMessage("Failed to load tour info : " + exception.getMessage())
					.setIcon(android.R.drawable.ic_dialog_alert)
					.setPositiveButton(android.R.string.ok, new OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// Go back
							TakeMeATourFragment.this.getMainActivity().onBackPressed();							
						}
					})
					.show();
			}
			
			@Override
			public void onCompleted(final TakeMeATourResponse response) {
				TakeMeATourFragment.this.getActivity().runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						TakeMeATourFragment.this.renderTourInfo(response);
					}
				});
			}
		});
		
		service.run();
		
	}

	/**
	 * Renders tour info in the view
	 * 
	 * @param response Take me a tour service response
	 */
	protected void renderTourInfo(TakeMeATourResponse response) {
		// Sets items list based on response
		this.setTakeMeATourItemListFromService(response);

		this.getInfoTextView().setText(this.getView().getContext().getString(R.string.no_result));
		
		if (this.getTakeMeATourItemList().size() == 0) {
			this.getInfoTextView().setVisibility(View.VISIBLE);
		}
		else {
			this.getInfoTextView().setVisibility(View.GONE);
			
			// Put items in the theme items ListView
			this.getTakeMeATourListView().setAdapter(
				new TakeMeATourItemAdapter(
					this.getActivity(),
					R.layout.item_take_me_a_tour_list_view,
					this.getTakeMeATourItemList()
				)
			);
			
			// Defines the item clicked event
			this.getTakeMeATourListView().setOnItemClickListener(new OnItemClickListener() {
				
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					TakeMeATourFragment.this.onTakeMeATourItemClicked(position);
				}
			});
		}
	}
	
	/**
	 * Occurs when a tour item is clicked
	 * 
	 * @param position
	 */
	protected void onTakeMeATourItemClicked(int position) {
		this.getMainActivity().browseToTourItem(
			this.getTakeMeATourItemList().get(position).getTourItem()
		);
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
		this.takeMeATourListView = null;
		this.infoTextView = null;
	}
}