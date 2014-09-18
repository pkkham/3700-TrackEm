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
 
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;
import com.example.trackem_glass.R;
public class TrackEmActivity extends FragmentActivity {

	
	
// ============================================================================
//Life Cycle Methods
	
    private TextView tempTextView; //Temporary TextView  
    private Button tempBtn; //Temporary Button 
	
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
        

        /*-------Setting the TextView Fonts-----------*/  
        Typeface font = Typeface.createFromAsset(getAssets(), "coolvetica.ttf");  
        tempTextView = (TextView) findViewById(R.id.timer);  
        tempTextView.setTypeface(font);  
        tempTextView = (TextView) findViewById(R.id.backgroundText);  
        tempTextView.setTypeface(font);  
        Button tempBtn = (Button)findViewById(R.id.startButton);  
        tempBtn.setTypeface(font);  
        tempBtn = (Button)findViewById(R.id.stopButton);  
        tempBtn.setTypeface(font);  
        
    }
// ============================================================================



  	

} //TrackEmActivity
