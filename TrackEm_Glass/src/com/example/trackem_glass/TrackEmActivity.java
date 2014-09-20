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

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.trackem_glass.TrackEmActivity.PlaceholderFragment.OnTimeUpdateListener;

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
        
        //If we are starting out fresh from startup, we want
        //to create a fragment and add it to the view.
        //R.id.container is the UI spot for this fragment.
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }
// ============================================================================


// ============================================================================
// Options Menu Call Backs
  	@Override
  	public boolean onCreateOptionsMenu(Menu menu) {
  		//When the options menu is created, we want to 'inflate'
  		//the XML file that we have set up in res/menu. This creates
  		//the UI for our options menu...
  		getMenuInflater().inflate(R.menu.track_em, menu);
  		return super.onCreateOptionsMenu(menu);
  	}
  	
  	@Override
  	public boolean onOptionsItemSelected(MenuItem item)
  	{
  		//Switch around the id's that are declared in the
  		//XML file res/menu/track_em.xml (the layout we inflated)
  		switch (item.getItemId())
  		{
  		
  		//If it is the 'preferences' menu item, start a new Activity that
  		//will lay on top of this one. It is started with what is called
  		//and 'Intent' and sent to the underlying Android runtime system
  		//to be processed and created.
  		case R.id.ab_preferences:
  			Intent intent = new Intent(this, SettingsActivity.class);
  			startActivity(intent);
  			break;
  		default:
  			return false;
  		}
  		return true;
  	}
// ============================================================================

  	
// ============================================================================
// This will be deleted eventually...just a 'Hello World' for now...
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

    	private LinearLayout m_main;
    	private TimeUpdateView m_updateTextView;
    	private Button m_start_stop;
    	private boolean m_started;
    	private StopWatchUtility m_stopwatch;
    	
    	
    	public interface OnTimeUpdateListener
    	{
    		public void onTimeUpdate(String nextTime);
    	}
    	
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
        	
        	LinearLayout.LayoutParams wrapContent = new LinearLayout.LayoutParams(
    				LinearLayout.LayoutParams.WRAP_CONTENT, 
    				LinearLayout.LayoutParams.WRAP_CONTENT);
        	
        	m_stopwatch = new StopWatchUtility(this.getActivity());        	
            View rootView = inflater.inflate(R.layout.fragment_track_em, container, false);
            
            m_main = (LinearLayout)rootView.findViewById(R.id.main_layout_fragment_track_em);
            m_updateTextView = new TimeUpdateView(getActivity());
            m_updateTextView.setText("TEST");
            m_stopwatch.setOnTimeUpdateListener((OnTimeUpdateListener) m_updateTextView);
            m_main.setLayoutParams(wrapContent);
            m_main.addView(m_updateTextView);
            
            m_start_stop = (Button)rootView.findViewById(R.id.start_stop_button);
            m_start_stop.setText("Start");
            m_started = false;
            
            m_start_stop.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					if (m_started)
					{
						m_started = false;
						m_start_stop.setText("Start");
						m_stopwatch.stopWatch();
					}
					else
					{
						m_started = true;
						m_start_stop.setText("Stop");
						m_stopwatch.startWatch();
					}
				}
			});
            
            return rootView;
        }
        
        @Override
        public void onStop()
        {
        	super.onStop();
        	if (m_started)
        	{
        		m_stopwatch.stopWatch();
        		m_started = false;
        	}
        }
    }
    
    public static class TimeUpdateView extends TextView implements OnTimeUpdateListener
    {

		public TimeUpdateView(Context context) {
			super(context);
			
		}

		@Override
		public void onTimeUpdate(String nextTime) {
			this.setText(nextTime);
		}
		
		
    	
    }
 //============================================================================

} //TrackEmActivity
