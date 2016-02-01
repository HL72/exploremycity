package com.kioube.tourapp.android.client.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentManager.OnBackStackChangedListener;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.domain.GeographicalArea;
import com.kioube.tourapp.android.client.domain.Theme;
import com.kioube.tourapp.android.client.domain.TourItem;
import com.kioube.tourapp.android.client.domain.TourItemImage;
import com.kioube.tourapp.android.client.helper.InternetConnectionHelper;
import com.kioube.tourapp.android.client.helper.SessionManager;
import com.kioube.tourapp.android.client.helper.UiHelper;
import com.kioube.tourapp.android.client.persistence.repository.ConfigurationRepository;
import com.kioube.tourapp.android.client.persistence.repository.TourItemRepository;
import com.kioube.tourapp.android.client.ui.adapter.NavigationDrawerItemAdapter;
import com.kioube.tourapp.android.client.ui.domain.NavigationDrawerItem;
import com.kioube.tourapp.android.client.ui.filter.FilterBase;
import com.kioube.tourapp.android.client.ui.filter.SearchFilter;
import com.kioube.tourapp.android.client.ui.filter.ThemeListFilter;
import com.kioube.tourapp.android.client.ui.filter.TourItemFilter;
import com.kioube.tourapp.android.client.ui.filter.TourItemImageFilter;
import com.kioube.tourapp.android.client.ui.filter.TourItemListFilter;

/**
 * 
 * MainActivity type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class MainActivity extends Activity {
	
	/* --- Constants --- */
	
	@SuppressWarnings("unused")
	private static final String LOG_TAG = MainActivity.class.getSimpleName();
	
	/* --- Fields --- */
	
	private ActionBarDrawerToggle actionBarDrawerToggle;
	private DrawerLayout drawerLayout;
	private RelativeLayout drawerNavigation;
	private FrameLayout fragmentContainer;
	private String viewTitle;
	
	private HomeFragment homeFragment;
	private SettingsFragment settingsFragment;
	
	private ImageView navigationHeaderImageView;
	private ListView navigationListView;
	private ListView settingsListView;
	
	private List<NavigationDrawerItem> navigationItemList;
	private List<NavigationDrawerItem> settingsItemList;
	
	private Menu menu;
	private TextView profileStatusTextView;
	
	private TourItemRepository tourItemRepository;
	private ConfigurationRepository configurationRepository;
	private MapFragment mapFragment;
	
	private TourItem currentTourItem;
	private SessionManager sessionManager;
	private InternetConnectionHelper internetConnectionHelper;
	
	/* --- Getters and setters --- */

	/**
	 * Gets the MainActivity object's mapFragment value
	 *
	 * @return The MainActivity object's mapFragment value
	 */
	public MapFragment getMapFragment() {
		if (this.mapFragment == null) {
			this.mapFragment = new MapFragment();
		}
		
		return this.mapFragment;
	}

	/**
	 * Gets the MainActivity object's viewTitle value
	 *
	 * @return The MainActivity object's viewTitle value
	 */
	public String getViewTitle() {
		if  (this.viewTitle == null) {
			this.viewTitle = this.getResources().getString(R.string.app_name);
		}
		return this.viewTitle;
	}

	/**
	 * Sets the MainActivity object's viewTitle value
	 * @param viewTitle The MainActivity object's viewTitle value to set
	 */
	public void setViewTitle(String viewTitle) {
		this.viewTitle = viewTitle;
		this.getActionBar().setTitle(this.viewTitle);
	}

	/**
	 * Gets the MainActivity object's actionBarDrawerToggle value
	 * 
	 * @return The MainActivity object's actionBarDrawerToggle value
	 */
	public ActionBarDrawerToggle getActionBarDrawerToggle() {
		if (this.actionBarDrawerToggle == null) {
			
			// Creates the action bar drawer toggle
			this.actionBarDrawerToggle = new ActionBarDrawerToggle(
				this,
				this.getDrawerLayout(),
				R.drawable.ic_drawer,
				R.string.open_menu,
				R.string.close_menu
				) {
					
					// Sets the closed drawer listener
					public void onDrawerClosed(View view) {
						super.onDrawerClosed(view);
						getActionBar().setTitle(
							MainActivity.this.getViewTitle()
							);
						invalidateOptionsMenu();
					}
					
					// Sets the opened drawer listener
					public void onDrawerOpened(View drawerView) {
						super.onDrawerOpened(drawerView);
						getActionBar().setTitle(
							getString(R.string.menu)
							);
						invalidateOptionsMenu();
					}
				};
		}
		
		return this.actionBarDrawerToggle;
	}
	
	/**
	 * Gets the MainActivity object's profileStatusTextView value
	 *
	 * @return The MainActivity object's profileStatusTextView value
	 */
	public TextView getProfileStatusTextView() {
		if (this.profileStatusTextView == null) {
			this.profileStatusTextView = (TextView) this.findViewById(R.id.profile_status_text_view);
		}
		
		return this.profileStatusTextView;
	}

	/**
	 * Gets the MainActivity object's settingsListView value
	 *
	 * @return The MainActivity object's settingsListView value
	 */
	public ListView getSettingsListView() {
		if (this.settingsListView == null) {
			this.settingsListView = (ListView) this.findViewById(R.id.settings_list_view);
		}
		
		return this.settingsListView;
	}

	/**
	 * Gets the MainActivity object's navigationListView value
	 * 
	 * @return The MainActivity object's navigationListView value
	 */
	public ListView getNavigationListView() {
		if (this.navigationListView == null) {
			this.navigationListView = (ListView) this.findViewById(R.id.navigation_list_view);
		}
		
		return this.navigationListView;
	}
	
	/**
	 * Gets the MainActivity object's fragmentContainer value
	 * 
	 * @return The MainActivity object's fragmentContainer value
	 */
	public FrameLayout getFragmentContainer() {
		if (this.fragmentContainer == null) {
			this.fragmentContainer = (FrameLayout) this.findViewById(R.id.fragment_container);
		}
		
		return this.fragmentContainer;
	}
	
	/**
	 * Gets the MainActivity object's drawerLayout value
	 * 
	 * @return The MainActivity object's drawerLayout value
	 */
	public DrawerLayout getDrawerLayout() {
		if (this.drawerLayout == null) {
			this.drawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
		}
		
		return this.drawerLayout;
	}
	
	/**
	 * Gets the MainActivity object's drawerNavigation value
	 * 
	 * @return The MainActivity object's drawerNavigation value
	 */
	public RelativeLayout getDrawerNavigation() {
		if (this.drawerNavigation == null) {
			this.drawerNavigation = (RelativeLayout) this.findViewById(R.id.drawer_navigation);
		}
		
		return this.drawerNavigation;
	}
	
	/**
	 * Gets the MainActivity object's homeFragment value
	 * 
	 * @return The MainActivity object's homeFragment value
	 */
	public HomeFragment getHomeFragment() {
		if (this.homeFragment == null) {
			this.homeFragment = new HomeFragment();
		}
		
		return this.homeFragment;
	}
	
	/**
	 * Gets the MainActivity object's settingsFragment value
	 * 
	 * @return The MainActivity object's settingsFragment value
	 */
	public SettingsFragment getSettingsFragment() {
		if (this.settingsFragment == null) {
			this.settingsFragment = new SettingsFragment();
		}
		
		return this.settingsFragment;
	}
	
	/**
	 * Gets the MainActivity object's navigationHeaderImageView value
	 * 
	 * @return The MainActivity object's navigationHeaderImageView value
	 */
	public ImageView getNavigationHeaderImageView() {
		if (this.navigationHeaderImageView == null) {
			this.navigationHeaderImageView = (ImageView) this.findViewById(R.id.navigation_header_image_view);
		}
		
		return this.navigationHeaderImageView;
	}
	
	/* --- Operations --- */
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.sessionManager = new SessionManager(this);
		this.internetConnectionHelper = new InternetConnectionHelper(this);
		this.tourItemRepository = new TourItemRepository(this);
		this.configurationRepository = new ConfigurationRepository(this);
		
		setContentView(R.layout.activity_main);
		
		this.setupNavigationPanel();
		
		// Set the drawer toggle as the DrawerListener
		this.getDrawerLayout().setDrawerListener(this.getActionBarDrawerToggle());
		
		this.setupActionBar();
		
		// Adds the drop shadow fioriture
		this.getDrawerLayout().setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		
		// Sets the backstack listener to perform actions on step back
		this.getFragmentManager().addOnBackStackChangedListener(new OnBackStackChangedListener() {    
            public void onBackStackChanged() {
            	MainActivity.this.setActionBarActionsVisible(true);
            }
        });
		
		// Adds the home fragment
		this.getFragmentManager().beginTransaction()
			.add(R.id.fragment_container, this.getHomeFragment(), this.getHomeFragment().getClass().getName())
			.commit();
		
		// Sets background colors
		this.getDrawerLayout().setBackgroundColor(
			configurationRepository.getBackgroundColor()
		);
		
		// TODO [2014-05-03, JMEL] Set custom background and foreground colors everywhere
	}
	
	/**
	 * Sets the action bar layout
	 */
	private void setupActionBar() {

		// Activate the application icon as home button
		this.getActionBar().setDisplayHomeAsUpEnabled(true);
		this.getActionBar().setHomeButtonEnabled(true);
		
		// Updates the action bar background color
		this.getActionBar().setBackgroundDrawable(new ColorDrawable(
			this.configurationRepository.getActionBarBackgroundColor()
		));
		
		// Gets the action bar title and sets its color
		int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		if (actionBarTitleId > 0) {
		    TextView title = (TextView) findViewById(actionBarTitleId);
		    if (title != null) {
		        title.setTextColor(this.configurationRepository.getActionBarForegroundColor());
		    }
		}
		
		// Sets the custom logo if provided

		
		// Loads the custom header image if provided
		String imageBase64 = configurationRepository.getLogo();
		if (imageBase64 != null) {
			
			byte[] imageData = Base64.decode(imageBase64, Base64.NO_WRAP | Base64.NO_PADDING | Base64.URL_SAFE);
			Bitmap bitmap = BitmapFactory.decodeByteArray(imageData , 0, imageData.length);
			
			this.getActionBar().setIcon(new BitmapDrawable(this.getResources(), bitmap));
		}
	}

	/**
	 * Configure the navigation menu
	 */
	public void setupNavigationPanel() {
		
		// Creates the navigation items list
		this.navigationItemList = new ArrayList<NavigationDrawerItem>();
		
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_home),
			R.drawable.ic_home,
			new Runnable() {				
				@Override
				public void run() {
					MainActivity.this.switchToFragment(getHomeFragment());
				}
			}
		));
		
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_bookmarks),
			R.drawable.ic_bookmark,
			(int) this.tourItemRepository.getBookmarkedCount(),
			new Runnable() {				
				@Override
				public void run() {
					MainActivity.this.browseToBookmarkList();
				}
			}
			));
		
		long tourItemCount = this.tourItemRepository.getCountAll();
		
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_catalog),
			R.drawable.ic_book,
			"Browse our " + tourItemCount + " references",
			(int) tourItemCount,
			new Runnable() {				
				@Override
				public void run() {
					MainActivity.this.browseToCatalog();
				}
			}
			));
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_advanced_search),
			R.drawable.ic_search,
			new Runnable() {				
				@Override
				public void run() {
					MainActivity.this.browseToSearch();
				}
			}
			));
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_take_me_a_tour),
			R.drawable.ic_directions,
			new Runnable() {				
				@Override
				public void run() {
					// Checks internet connection
					if (!internetConnectionHelper.isInternetConnectionAvailable()) {
						MainActivity.this.showNoNetworkAlert();
					}
					else {
						MainActivity.this.browseToTakeMeATour();
					}
				}
			}
			));
		
		this.navigationItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_messenger),
			R.drawable.ic_messages,
			new Runnable() {				
				@Override
				public void run() {
					// Checks internet connection
					if (!internetConnectionHelper.isInternetConnectionAvailable()) {
						MainActivity.this.showNoNetworkAlert();
					}
					else {
						MainActivity.this.browseToMessages();
					}
				}
			}
		));
		
		// Adapts it to the navigation list view
		this.getNavigationListView().setAdapter(
			new NavigationDrawerItemAdapter(
				this,
				R.layout.item_navigation_list_view,
				MainActivity.this.navigationItemList
			)
		);
		
		// Select the first item (the home item)
		this.getNavigationListView().setItemChecked(0, true);
		
		// Defines the item clicked event
		this.getNavigationListView().setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MainActivity.this.onNavigationItemClicked(position);
			}
		});
		
		// Fixes ListView height which is in a ScrollView
		UiHelper.setListViewHeightBasedOnChildren(this.getNavigationListView());
		
		// Creates the settings menu items
		this.settingsItemList = new ArrayList<NavigationDrawerItem>();

		this.settingsItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_settings),
			R.drawable.ic_cog,
			new Runnable() {				
				@Override
				public void run() {
					MainActivity.this.switchToFragment(getSettingsFragment());
				}
			}
		));

		this.settingsItemList.add(new NavigationDrawerItem(
			this.getResources().getString(R.string.navigation_synchronize),
			R.drawable.ic_synchronize,
			new Runnable() {				
				@Override
				public void run() {

					// Checks internet connection
					if (!internetConnectionHelper.isInternetConnectionAvailable()) {
						MainActivity.this.showNoNetworkAlert();
					}
					else {
						// Tells the app to enforce sync on restart
						MainActivity.this.sessionManager.setEnforcingSync(true);
						
						// Restarts the app
						Intent intent = getBaseContext()
							.getPackageManager()
							.getLaunchIntentForPackage(getBaseContext().getPackageName());
						
						intent.addFlags(
							Intent.FLAG_ACTIVITY_CLEAR_TOP |
							Intent.FLAG_ACTIVITY_NEW_TASK |
							Intent.FLAG_ACTIVITY_CLEAR_TASK
						);
						
						startActivity(intent);
					}
				}
			}
		));
		
		// Adapts it to the settings list view
		this.getSettingsListView().setAdapter(
			new NavigationDrawerItemAdapter(
				this,
				R.layout.item_navigation_list_view,
				MainActivity.this.settingsItemList
			)
			);
		
		// Defines the item clicked event
		this.getSettingsListView().setOnItemClickListener(new OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				MainActivity.this.onSettingsItemClicked(position);
			}
		});
		
		// Fixes ListView height which is in a ScrollView
		UiHelper.setListViewHeightBasedOnChildren(this.getSettingsListView());
		
	}

	/**
	 * Show an alert if there is no network
	 */
	public void showNoNetworkAlert() {
		new AlertDialog.Builder(this)
			.setTitle(this.getResources().getString(R.string.no_network))
			.setMessage(this.getResources().getString(R.string.no_network_details))
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton(android.R.string.ok, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// Nothing to do
				}
			})
			.show();
	}

	/**
	 * Browses to the advanced search view
	 */
	protected void browseToSearch() {
		SearchFragment fragment = new SearchFragment();
		
		this.switchToFragment(fragment);
	}

	/**
	 * Browses to the Take me a tour view
	 */
	protected void browseToTakeMeATour() {
		this.switchToFragment(new TakeMeATourFragment());
	}

	/**
	 * Browses to the messages view
	 */
	protected void browseToMessages() {
		this.switchToFragment(new MessagesFragment());
	}

	/**
	 * Browses to the catalog
	 */
	protected void browseToCatalog() {
		GeographicalAreaListFragment fragment = new GeographicalAreaListFragment();
		
		this.switchToFragment(fragment);
	}

	/**
	 * Browses to the bookmarks fragment
	 */
	protected void browseToBookmarkList() {
		TourItemListFragment fragment = new TourItemListFragment();
		TourItemListFilter filter = new TourItemListFilter(true);
		
		// Opens themes fragment
		this.switchToFragment(fragment, filter);
	}
	
	/**
	 * Browse to the Theme list fragment
	 * 
	 * @param geographicalArea The GeographicalArea object to use for filter
	 */
	public void browseToThemeList(GeographicalArea geographicalArea) {
		
		// Build theme results filter
		ThemeListFilter filter = new ThemeListFilter(geographicalArea);
		ThemeListFragment fragment = new ThemeListFragment();
		
		// Opens themes fragment
		this.switchToFragment(fragment, filter);
	}
	
	/**
	 * Browse to the advanced search fragment
	 * 
	 * @param keyword The keyword to search for
	 */
	public void browseToSearch(String keyword) {
		
		// Build theme results filter
		SearchFilter filter = new SearchFilter(keyword);
		SearchFragment fragment = new SearchFragment();
		
		// Opens themes fragment
		this.switchToFragment(fragment, filter);
	}

	/**
	 * Browse to TourItem object fragment
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public void browseToTourItem(TourItem tourItem) {
		TourItemFilter filter = new TourItemFilter(tourItem);
		TourItemFragment fragment = new TourItemFragment();
		
		// Sets the bookmark action button
		this.currentTourItem = tourItem;
		this.updateBookmarkAction();
		
		this.switchToFragment(fragment, filter);		
	}
	
	/**
	 * Updates the bookmark action button depending on TourItem bookmark status
	 */
	private void updateBookmarkAction() {
		MenuItem menuItem = this.menu.findItem(R.id.action_bookmark);
		
		if (this.currentTourItem.isBookmarked()) {
			menuItem.setIcon(R.drawable.ic_action_bookmark_on);
		}
		else {
			menuItem.setIcon(R.drawable.ic_action_bookmark);			
		}
	}

	/**
	 * Browse to TourItem object map fragment
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public void browseToTourItemMap(TourItem tourItem) {
		TourItemFilter filter = new TourItemFilter(tourItem);
		MapFragment mapFragment = this.getMapFragment();
		
		this.switchToFragment(mapFragment, filter, Transition.CARD_FLIP);		
	}

	/**
	 * Browse to TourItem object gallery fragment
	 * 
	 * @param tourItem The TourItem object to render
	 */
	public void browseToTourItemGallery(TourItem tourItem) {
		TourItemFilter filter = new TourItemFilter(tourItem);
		GalleryFragment fragment = new GalleryFragment();
		
		this.switchToFragment(fragment, filter, Transition.CARD_FLIP);		
	}

	/**
	 * Occurs when an item is clicked in the navigation list view
	 * 
	 * @param position Clicked item position
	 */
	public void onNavigationItemClicked(int position) {
		
		// Deselect all items in the other list view
		for (int i = 0; i < this.settingsItemList.size(); i++) {
			this.getSettingsListView().setItemChecked(i, false);
		}
		
		// Executes the runnable associated to the item
		this.navigationItemList.get(position).getRunnable().run();
		
		// Closes the drawer
		this.getDrawerLayout().closeDrawer(this.getDrawerNavigation());
	}
	
	/**
	 * Occurs when an item is clicked in the settings list view
	 * 
	 * @param position Clicked item position
	 */
	public void onSettingsItemClicked(int position) {
		
		// Deselect all items in the other list view
		for (int i = 0; i < this.navigationItemList.size(); i++) {
			this.getNavigationListView().setItemChecked(i, false);
		}
		
		// Executes the runnable associated to the item
		this.settingsItemList.get(position).getRunnable().run();
		
		// Closes the drawer
		this.getDrawerLayout().closeDrawer(this.getDrawerNavigation());
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPostCreate(android.os.Bundle)
	 */
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		
		// Sync the toggle state after onRestoreInstanceState has occurred.
		this.getActionBarDrawerToggle().syncState();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onConfigurationChanged(android.content.res.Configuration)
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		
		this.getActionBarDrawerToggle().onConfigurationChanged(newConfig);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		this.menu = menu;
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onPrepareOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		
		// Hides some menu actions if drawer is open
		this.setActionBarActionsVisible(!this.getDrawerLayout().isDrawerOpen(this.getDrawerNavigation()));

		int flashIconRes = this.sessionManager.isConnectedMode() ? R.drawable.ic_action_flash_on : R.drawable.ic_action_flash_off;
		this.menu.findItem(R.id.action_connection).setIcon(flashIconRes);
		
		return super.onPrepareOptionsMenu(menu);
	}
	
	/**
	 * Sets the action bar actions visibility
	 * @param visible
	 */
	public void setActionBarActionsVisible(boolean visible) {
		
		// Hides the bookmark item if the fragmetn is not a TourItemFragment instance
		TourItemFragment tourItemFragment = (TourItemFragment) this.getFragmentManager().findFragmentByTag(TourItemFragment.class.getName());
		
		if ((tourItemFragment != null) && (tourItemFragment.isVisible())) {			
			menu.findItem(R.id.action_bookmark).setVisible(visible);
		}
		else {
			menu.findItem(R.id.action_bookmark).setVisible(false);
		}
		
		menu.findItem(R.id.action_connection).setVisible(visible);
		menu.findItem(R.id.action_home).setVisible(visible);
		
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		boolean result = false;
		
		// Pass the event to ActionBarDrawerToggle, if it returns
		// true, then it has handled the app icon touch event
		if (this.getActionBarDrawerToggle().onOptionsItemSelected(item)) {
			return true;
		}
		
		// Other ActionBar item selected
		switch (item.getItemId()) {
			case R.id.action_home:
				this.switchToFragment(this.getHomeFragment());
				result = true;
				break;
				
			case R.id.action_connection:
				this.switchConnectionMode();
				result = true;
				break;
				
			case R.id.action_bookmark:
				
				if (this.currentTourItem.isBookmarked()) {
					this.currentTourItem.setBookmarked(false);
					Toast.makeText(
						this, 
						String.format(this.getResources().getString(R.string.is_no_longer_bookmarked), this.currentTourItem.getName()), 
						Toast.LENGTH_SHORT
					).show();
				}
				else {
					this.currentTourItem.setBookmarked(true);
					Toast.makeText(
						this, 
						String.format(this.getResources().getString(R.string.is_now_bookmarked), this.currentTourItem.getName()), 
						Toast.LENGTH_SHORT
					).show();					
				}
				
				this.tourItemRepository.update(this.currentTourItem);
				this.updateBookmarkAction();
				
				// Refresh the navigation panel to refresh bookmark count
				this.setupNavigationPanel();
				
				result = true;
				break;
				
			default:
				result = super.onOptionsItemSelected(item);
		}
		
		return result;
	}
	
	/**
	 * Changes the connection mode
	 */
	private void switchConnectionMode() {
		
		// Switch to disconnected mode
		if (this.sessionManager.isConnectedMode()) {
			this.sessionManager.setConnectedMode(false);
			this.menu.findItem(R.id.action_connection).setIcon(R.drawable.ic_action_flash_off);
			this.getProfileStatusTextView().setText(this.getResources().getString(R.string.offline_mode));
			
			Toast.makeText(this, R.string.now_disconnected_modetext, Toast.LENGTH_SHORT).show();
		}
		
		// Switch to connected mode
		else {
			this.sessionManager.setConnectedMode(true);
			this.menu.findItem(R.id.action_connection).setIcon(R.drawable.ic_action_flash_on);
			this.getProfileStatusTextView().setText(this.getResources().getString(R.string.online_mode));
			
			Toast.makeText(this, R.string.now_connected_modetext, Toast.LENGTH_SHORT).show();			
		}
	}
	
	/**
	 * Switch to the specified fragment
	 * 
	 * @param fragment The fragment to switch to
	 */
	private void switchToFragment(FragmentBase fragment)
	{
		this.switchToFragment(fragment, null);
	}
	
	/**
	 * Switch to the specified fragment
	 * 
	 * @param fragment The fragment to switch to
	 * @param filter Filter used to render the opened fragment
	 */
	private void switchToFragment(FragmentBase fragment, FilterBase filter)
	{
		this.switchToFragment(fragment, filter, Transition.DEFAULT);
	}
	
	/**
	 * Switch to the specified fragment
	 * 
	 * @param fragment The fragment to switch to
	 * @param filter Filter used to render the opened fragment
	 */
	private void switchToFragment(FragmentBase fragment, FilterBase filter, Transition transition)
	{
		// The specified fragment is not on top
		if (!fragment.isVisible()) {
			String backStateName = fragment.getClass().getName();
			
			FragmentManager manager = getFragmentManager();
			boolean fragmentPopped = manager.popBackStackImmediate(backStateName, 0);
			
			if (!fragmentPopped) {
				FragmentTransaction transaction = manager.beginTransaction();
				
				// Sets the transition to use
				switch (transition) {
					
					// Jacky moumoute card-flip transition
					case CARD_FLIP :
						transaction.setCustomAnimations(
							R.animator.card_flip_right_in,
							R.animator.card_flip_right_out,
							R.animator.card_flip_left_in,
							R.animator.card_flip_left_out
						);
						break;
						
					// Default transition (Slide animation)
					default :
						transaction.setCustomAnimations(
							R.animator.fragment_slide_left_enter,
							R.animator.fragment_slide_left_exit,
							R.animator.fragment_slide_right_enter,
							R.animator.fragment_slide_right_exit
						);
						break;						
				}
				
				// Sets the filter
				if (filter != null) {
					fragment.setFilter(filter);
				}
				
				// Replace the fragment by the new one
				transaction.replace(R.id.fragment_container, fragment, fragment.getClass().getName());
				
				// Manage back stack
				transaction.addToBackStack(backStateName);
				
				transaction.commit();
			}
		}
	}
	
	/**
	 * Browse to TourItem list fragment filtered by GeographicalArea and Theme (usually using the catalog navigation)
	 * 
	 * @param area The GeographicalArea object used to filter results
	 * @param theme The Theme object used to filter results
	 */
	public void browseToTourItemList(GeographicalArea area, Theme theme) {
		
		// Build theme results filter
		TourItemListFilter filter = new TourItemListFilter(area, theme);
		TourItemListFragment fragment = new TourItemListFragment();
		
		// Opens themes fragment
		this.switchToFragment(fragment, filter);
	}
	
	/**
	 * Browse to TourItem list fragment filtered by GeographicalArea and Theme (usually using the catalog navigation)
	 * 
	 * @param area The GeographicalArea object used to filter results
	 * @param theme The Theme object used to filter results
	 */
	public void browseToTourItemList(GeographicalArea area, Theme theme, String keyword) {
		
		// Build theme results filter
		TourItemListFilter filter = new TourItemListFilter(area, theme, keyword);
		TourItemListFragment fragment = new TourItemListFragment();
		
		// Opens themes fragment
		this.switchToFragment(fragment, filter);
	}
	
	/**
	 * Browses to a rendered image
	 * 
	 * @param image
	 */
	public void browseToImage(TourItemImage image) {

		// Build theme results filter
		TourItemImageFilter filter = new TourItemImageFilter(image);
		ImageFragment fragment = new ImageFragment();

		// Opens themes fragment

		this.switchToFragment(fragment, filter, Transition.CARD_FLIP);
	}
	
	private enum Transition {
		DEFAULT,
		CARD_FLIP
	}
}
