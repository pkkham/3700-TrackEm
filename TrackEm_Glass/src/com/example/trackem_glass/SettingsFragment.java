//This class was adapted from http://developer.android.com/guide/topics/ui/settings.html
/*
 * 	Title
 * 		SettingsFragment.java
 * 
 *	Description
 *		SettingsFragment is the modular UI 'fragment' that
 *		is added to the SettingsActivity. This is a PreferenceFragment
 *		that takes care of saving preferences to a file after the user
 *		modifies them. See the documentation for PreferenceFragment for
 *		more information.
 *
 *		Also see res/xml/preferences.xml to see how to create/modify
 *		the preferences for this project.
 *
*/

package com.example.trackem_glass;

import android.os.Bundle;
import android.preference.PreferenceFragment;

public class SettingsFragment extends PreferenceFragment {
	
// ============================================================================
//Life Cycle Methods
	
	//All the onCreate method for this Fragment does is call the
	//inherited 'addPreferencesFromResource'. This inflates all of
	//the necessary preferences into a cookie-cutter type preferences
	//screen that should be sufficient for our project.
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }	
// ============================================================================

} //SettingsFragment
