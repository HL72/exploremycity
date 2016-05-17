package com.kioube.tourapp.android.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.persistence.repository.GeographicalAreaRepository;
import com.kioube.tourapp.android.client.persistence.repository.ThemeRepository;
import com.kioube.tourapp.android.client.ui.filter.SearchFilter;

/**
 * 
 * SearchFragment type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class SearchFragment extends FragmentBase {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = SearchFragment.class.getSimpleName();
	
	/* --- Fields --- */
	
	private View view;
	private GeographicalAreaRepository geographicalAreaRepository = new GeographicalAreaRepository(this.getActivity());
	private ThemeRepository themeRepository = new ThemeRepository(this.getActivity());
	private List<GeographicalArea> geographicalAreaList;
	private List<Theme> themeList;
	private GeographicalArea selectedGeographicalArea;
	private Theme selectedTheme;
	
	private Button searchButton;
	private EditText keywordEditText;
	
	/* --- Getters & setters --- */
	
	/**
	 * Gets the SearchFragment object's searchButton value
	 *
	 * @return The SearchFragment object's searchButton value
	 */
	public Button getSearchButton() {
		if (this.searchButton == null) {
			this.searchButton = (Button) this.getView().findViewById(R.id.search_button);
		}
		
		return this.searchButton;
	}

	/**
	 * Gets the SearchFragment object's keywordEditText value
	 *
	 * @return The SearchFragment object's keywordEditText value
	 */
	public EditText getKeywordEditText() {
		if (this.keywordEditText == null) {
			this.keywordEditText = (EditText) this.getView().findViewById(R.id.keyword_edit_text);
		}
		
		return this.keywordEditText;
	}	
	
	/**
	 * Gets the Fragment keyword to use
	 */
	public String getKeyword() {
		String result = null;
		
		if (this.getFilter() != null) {
			result = this.getFilter().getKeyword();
		}
		
		return result;
	}
	
	/**
	 * Gets the fragment filter
	 */
	public SearchFilter getFilter() {
		return (SearchFilter) super.getFilter();
	}
	
	/**
	 * Sets the fragment filter
	 * 
	 * @param filter The filter used to filter
	 */
	public void setFilter(SearchFilter filter) {
		super.setFilter(filter);
	}	
	
	/**
	 * Gets the SearchFragment object's geographicalAreaList value
	 * 
	 * @return The SearchFragment object's geographicalAreaList value
	 */
	public List<GeographicalArea> getGeographicalAreaList() {
		if (this.geographicalAreaList == null) {
			this.geographicalAreaList = geographicalAreaRepository.getAll();
		}
		
		return this.geographicalAreaList;
	}

	/**
	 * Gets the SearchFragment object's themeList value
	 * 
	 * @return The SearchFragment object's themeList value
	 */
	public List<Theme> getThemeList() {
		if (this.themeList == null) {
			this.themeList = themeRepository.getAll();
		}
		
		return this.themeList;
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
	 * 
	 * @see android.support.v4.app.Fragment#onCreateView(android.view.LayoutInflater, android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater,
		ViewGroup container, Bundle savedInstanceState) {
		super.onCreateView(inflater, container, savedInstanceState);
		
		// Inflate the layout for this fragment
		this.view = inflater.inflate(R.layout.fragment_search, container, false);
		
		// Setups spinners
		this.setUpGeographicalAreaSpinner();
		this.setUpThemeSpinner();
		
		// Sets the keyword EditText value
		if (this.getKeyword() != null) {
			this.getKeywordEditText().setText(this.getKeyword());
		}
		
		// Sets search button event
		Button searchButton = (Button) this.getView().findViewById(R.id.search_button);
		searchButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (SearchFragment.this.selectedGeographicalArea == null &&
						SearchFragment.this.selectedTheme == null) {
					SearchFragment.this.getMainActivity().browseToTourItemList(
							SearchFragment.this.getKeywordEditText().getText().toString()
					);
				}
				else if (SearchFragment.this.selectedGeographicalArea == null) {
					SearchFragment.this.getMainActivity().browseToTourItemList(
							SearchFragment.this.selectedTheme,
							SearchFragment.this.getKeywordEditText().getText().toString()
					);
				}
				else if (SearchFragment.this.selectedTheme == null) {
					SearchFragment.this.getMainActivity().browseToTourItemList(
							SearchFragment.this.selectedGeographicalArea,
							SearchFragment.this.getKeywordEditText().getText().toString()
					);
				}
				else {
					SearchFragment.this.getMainActivity().browseToTourItemList(
							SearchFragment.this.selectedGeographicalArea,
							SearchFragment.this.selectedTheme,
							SearchFragment.this.getKeywordEditText().getText().toString()
					);
				}
			}
		});
		
		// Sets fragment title
		this.getMainActivity().setViewTitle(
			this.getResources().getString(R.string.advanced_search)
		);
		
		return this.view;
	}
	
	/* --- Object operations --- */
	
	/**
	 * Setups the GeographicalArea objects spinner
	 */
	public void setUpGeographicalAreaSpinner() {
		Spinner spinner = (Spinner) this.getView().findViewById(R.id.geographical_area_spinner);
		
		// Sets labels
		List<String> labels = new ArrayList<String>();
		labels.add("-");
		
		for (GeographicalArea area : this.getGeographicalAreaList()) {
			labels.add(area.getName());
		}
		
		// Adapts them to the spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, labels);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		
		// Sets item choice event
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				SearchFragment.this.selectedGeographicalArea = SearchFragment.this.getGeographicalAreaList().get(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				SearchFragment.this.selectedGeographicalArea = null;				
			}
		});
		
	}
	
	/**
	 * Setups the Theme objects spinner
	 */
	public void setUpThemeSpinner() {
		Spinner spinner = (Spinner) this.getView().findViewById(R.id.theme_spinner);
		
		// Sets labels
		List<String> labels = new ArrayList<String>();
		labels.add("-");

		for (Theme theme : this.getThemeList()) {
			labels.add(theme.getName());
		}
		
		// Adapts them to the spinner
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, labels);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(dataAdapter);
		
		// Sets item choice event
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				if (position == 0) {
					SearchFragment.this.selectedTheme = null;
				}
				else {
					SearchFragment.this.selectedTheme = SearchFragment.this.getThemeList().get(position-1);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				SearchFragment.this.selectedTheme = null;				
			}
		});
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onDestroyView()
	 */
	@Override
	public void onDestroyView() {
		super.onDestroyView();
		
		// Clears the inner views accessors
		this.searchButton = null;
		this.keywordEditText = null;
	}
}