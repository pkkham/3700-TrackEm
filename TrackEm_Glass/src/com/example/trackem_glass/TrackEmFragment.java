package com.example.trackem_glass;

import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.trackem_glass.StopWatchUtility.OnTimeUpdateListener;

public class TrackEmFragment extends Fragment 
{
	private LinearLayout m_main;
	private TimeUpdateTextView m_updateTextView;
	private Button m_start_stop;
	private boolean m_started;
	private StopWatchUtility m_stopwatch;
	
	private SharedPreferences m_prefs;
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) 
    {
    	
    	setHasOptionsMenu(true);
    	
    	//Instantiate base members
    	m_prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
    	m_stopwatch = new StopWatchUtility(this.getActivity());        	
        View rootView = inflater.inflate(R.layout.fragment_track_em, container, false);
        
        //We want to add to the LinearLayout that was inflated from XML
        m_main = (LinearLayout)rootView.findViewById(R.id.main_layout_fragment_track_em);
        m_updateTextView = new TimeUpdateTextView(getActivity());
        m_stopwatch.setOnTimeUpdateListener((OnTimeUpdateListener) m_updateTextView);
        m_main.setLayoutParams(Constants.WRAP_CONTENT);
        m_main.addView(m_updateTextView);
        
        //Find the inflated button
        m_start_stop = (Button)rootView.findViewById(R.id.start_stop_button);
        m_start_stop.setText("Start");
        m_started = false;
        
        //Add a callback for what happens when you click it 
        m_start_stop.setOnClickListener(new View.OnClickListener() 
        {
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
    
    public void onResume()
    {
    	super.onResume();
    	
    	//Every time this fragment comes back into focus, we want to
    	//check to make sure we are aligned with the preferences in case
    	//the user changed them...
    	int refreshRate = Integer.parseInt(m_prefs.getString(SettingsActivity.PREFS_TIMER_REFRESH_RATE, "100"));
    	m_stopwatch.setRefreshRate(refreshRate);
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
    
  	@Override
  	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) 
  	{
  		//When the options menu is created, we want to 'inflate'
  		//the XML file that we have set up in res/menu. This creates
  		//the UI for our options menu...
  		super.onCreateOptionsMenu(menu, inflater);
  		inflater.inflate(R.menu.fragment_track_em, menu);
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
  		case R.id.menu_item_preferences:
  			Intent intent = new Intent(this.getActivity(), SettingsActivity.class);
  			startActivity(intent);
  			break;
  		default:
  			return false;
  		}
  		return true;
  	}
}
