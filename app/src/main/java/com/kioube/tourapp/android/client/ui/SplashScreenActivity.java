package com.kioube.tourapp.android.client.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.TextView;

import com.kioube.tourapp.android.client.R;
import com.kioube.tourapp.android.client.helper.InternetConnectionHelper;
import com.kioube.tourapp.android.client.helper.SessionManager;
import com.kioube.tourapp.android.client.helper.SynchronizationPreference;
import com.kioube.tourapp.android.client.service.SynchronizationService;
import com.kioube.tourapp.android.client.service.listener.ServiceListener;

/**
 * 
 * SplashScreenActivity type definition
 * 
 * @author Julien Mellerin
 * 
 */
public class SplashScreenActivity extends Activity {
	
	/* --- CONSTANTS --- */
	
	private static final String LOG_TAG = SplashScreenActivity.class.getSimpleName();
	
	/* --- Statics --- */
	
	private static Class<?> NEXT_ACTIVITY_CLASS = MainActivity.class;
	
	/* --- Fields --- */
	
	private Exception exception;
	private SessionManager sessionManager;
	private InternetConnectionHelper internetConnectionHelper;
	
	/* --- Getters and setters --- */
	
	/**
	 * Gets the SplashScreenActivity object's exception value
	 * 
	 * @return The SplashScreenActivity object's exception value
	 */
	public Exception getException() {
		return this.exception;
	}
	
	/**
	 * Sets the SplashScreenActivity object's exception value
	 * 
	 * @param exception The SplashScreenActivity object's exception value to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}
	
	/* --- Constructors --- */
	
	/* --- Static operations --- */
	
	/**
	 * PRovides a short representation of an exception stack
	 * 
	 * @param exception To exception to summarize
	 * @return The exception summary
	 */
	protected static String getExceptionSummary(Throwable exception) {
		String result = exception.getMessage();
		
		if (exception.getStackTrace().length > 0) {
			StackTraceElement trace = exception.getStackTrace()[0];
			result += String.format(
				"\n\tin %s::%s line %d",
				trace.getClassName(),
				trace.getMethodName(),
				trace.getLineNumber()
				);
		}
		
		if (exception.getCause() != null) {
			result += "\n" + getExceptionSummary(exception.getCause());
		}
		
		return result;
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
		
		// Hides the action bar
		this.getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		
		setContentView(R.layout.activity_splashscreen);
		
		Log.d(LOG_TAG, "pref = " + this.sessionManager.getSynchronizationPreference().getIdentifier());
		
		// Check for sync based on preference or synchronization request from app
		if (this.sessionManager.isEnforcingSync() || this.sessionManager.getSynchronizationPreference() == SynchronizationPreference.AUTOMATIC) {
			this.runSynchronization();
		}
		
		// else based on manual request
		else if (this.sessionManager.getSynchronizationPreference() == SynchronizationPreference.ASK) {

			new AlertDialog.Builder(this)
				.setMessage(this.getResources().getString(R.string.sync_alert))
				.setIcon(android.R.drawable.ic_dialog_info)
				.setPositiveButton(android.R.string.yes, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SplashScreenActivity.this.runSynchronization();
					}
				})
				.setNegativeButton(android.R.string.no, new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						SplashScreenActivity.this.openNextActivity();
					}
				})
				.show();
		}
		
		// else bypass sync
		else {
			this.openNextActivity();
		}
	}
	
	/**
	 * Runs a synchronization process
	 */
	protected void runSynchronization() {
		// TODO [2014-03-xx, JMEL] Remove this fake load time when the data loading is implemented
		
		// Check for internet connection
		if (!this.internetConnectionHelper.isInternetConnectionAvailable()) {
		new AlertDialog.Builder(this)
			.setTitle(this.getResources().getString(R.string.no_network))
			.setMessage(this.getResources().getString(R.string.no_network_details))
			.setIcon(android.R.drawable.ic_dialog_alert)
			.setPositiveButton(android.R.string.ok, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					SplashScreenActivity.this.openNextActivity();
				}
			})
			.show();			
		}
		
		// Runs synchronization
		else {
			SynchronizationService service = new SynchronizationService(this, new ServiceListener() {
				
				// Occurs when the sync is done
				@Override
				public void onCompleted() {
					SplashScreenActivity.this.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							
							// Runs the next activity if there is no error
							if (SplashScreenActivity.this.getException() == null) {
								SplashScreenActivity.this.openNextActivity();
							}
						}
					});
					
				}
				
				// Occurs on synchronization error
				@Override
				public void onError(Exception exception) {
					SplashScreenActivity.this.setException(exception);
					
					SplashScreenActivity.this.runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							
							// Builds the error message
							Exception exception = SplashScreenActivity.this.getException();
							String message = SplashScreenActivity.getExceptionSummary(exception);
							
							// Shows the error message dialog
							AlertDialog dialog = new AlertDialog.Builder(SplashScreenActivity.this)
								.setTitle("Synchronize error !")
								.setMessage(message)
								.setIcon(android.R.drawable.ic_dialog_alert)
								.setPositiveButton(android.R.string.ok, new OnClickListener() {
									
									@Override
									public void onClick(DialogInterface dialog, int which) {
										SplashScreenActivity.this.setException(null);
										// SplashScreenActivity.this.openNextActivity();
										
										// Kills app
										System.exit(0);
									}
								})
								.show();
							
							// Dirty text size change
							// TODO [2014-03-18, JMEL] Find a better way to set text size with styles (if I care later)
							TextView textView = (TextView) dialog.findViewById(android.R.id.message);
						    textView.setTextSize(14);
							
							Log.e(LOG_TAG, exception.getMessage(), exception);
						}
					});
				}
			});
			
			service.run();
		}
	}
	
	/**
	 * Opens the next activity
	 */
	protected void openNextActivity() {
		this.sessionManager.setEnforcingSync(false);
		
		Intent intent = new Intent(SplashScreenActivity.this, NEXT_ACTIVITY_CLASS);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
	}
	
}
