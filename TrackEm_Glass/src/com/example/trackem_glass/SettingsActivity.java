//This class was adapted from http://developer.android.com/guide/topics/ui/settings.html
/*
 * 	Title
 * 		SettingsActivity.java
 * 
 *	Description
 *		SettingsActivity is the controlling unit for when our
 *		main activity calls upon the 'Settings' menu button and
 *		an Intent is created to start this one. All it does is
 *		overlay a Fragment on top of this one. (I know it isn't
 *		much, but for sake of modularity and consistency, we do this)
 *
*/
package com.example.trackem_glass;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {

	//Public constants 
	//These constants correspond to the ones defined in the preferences file.
	//See res/xml/preferences.xml
	public static final String PREFS_BUFFER_SIZE = "prefs_buffer_size";
	public static final String PREFS_FILTER_COUNT = "prefs_filter_passes_count";
	public static final String PREFS_CONCERT_PITCH = "prefs_center_pitch";
	public static final String PREFS_SHOW_RAW_BUFFER = "prefs_show_raw_buffer";
	public static final String PREFS_SHOW_FFT_BUFFER = "prefs_show_fft_buffer";
	public static final String PREFS_SHOW_FREQUENCY_GRAPH = "prefs_show_frequency_graph";
	public static final String PREFS_SAMPLE_RATE = "prefs_sample_rate";

// ============================================================================
//Life Cycle Methods

	//onCreate is the first life cycle method that is called for this
	//object. All it does is create a SettingsFragment to overlay on top
	//of the Activity.
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//Display the fragment as the main content...
		getFragmentManager().beginTransaction()
				.replace(android.R.id.content, new SettingsFragment())
				.commit();
	}
//============================================================================

} //SettingsActivity


