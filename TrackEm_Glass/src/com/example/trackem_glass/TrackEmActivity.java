/*
 * 	Title
 * 		TrackEmActivity.java
 * 
 *	Description
 *		TrackEmActivity is the base controlling unit for our application.
 *		It controls what Fragment (piece of UI) is currently shown, their
 *		respective callback routines, and other life cycle routines that
 *		drive the underlying management for our application.
 *
*/
package com.example.trackem_glass;

import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

public class TrackEmActivity extends FragmentActivity {

	
	
// ============================================================================
//Life Cycle Methods
	
    @Override
    //The onCreate(...) method is a life cycle method of the Activity.
    //Basically, this is the method that gets called first (there are others
    //that are called first, be we aren't implementing these yet). What we will
    //do is 'overlay' a fragment on top of this by using the FragmentManager
    //We will also set up any action bar related items here.
    //
    //When user hits the home button, the app is in a 'stopped' state. This
    //state is held in memory and if the user navigates back to the app, the
    //information of that state can be retrieved again from 'savedInstanceState'.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //call FragmentActivity's onCreate
        
        //Set the view to be our layout file found in res/layout.
        //'R' is a generated file from ADT that just holds constants
        //that pertain to external XML or other resources
        setContentView(R.layout.activity_track_em);
        
        /*
         * First we ask the FragmentManager for the fragment located in
         * the R.id.container. If this is already created, it will return
         * that fragment, otherwise it'll return null.
         * 
         * Why would the fragment already be in the list? This activity could
         * be recreated after being destroyed on rotation or to reclaim memory.
         * When an activity is destroyed, it saves out its list of fragments.
         * When recreated, the FragmentManager retrieves the list and recreates
         * the listed fragments to make everything as it was before...
         */
        FragmentManager fm = getFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.container);
        if (fragment == null) 
        {
        	fragment = new TrackEmFragment();
            fm.beginTransaction().add(R.id.container, fragment).commit();
        }
    }

} //TrackEmActivity
