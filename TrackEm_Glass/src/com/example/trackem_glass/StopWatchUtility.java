package com.example.trackem_glass;

import android.view.View;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;
import android.widget.TextView;


/*
 * Handles stop watch functionality of TrackEm.
 * 
 * @author Tyler Hoover
 *
 */
public class StopWatchUtility extends FragmentActivity {
	
	private TextView tempTextView; //Temporary 
	private Button tempBtn; //Temporary 
	private Handler mHandler = new Handler();
	private long startTime; 
	private long elapsedTime; 
	private final int REFRESH_RATE = 100; 
	private String hours,minutes,seconds,milliseconds; 
	private long secs,mins,hrs,msecs; 
	private boolean stopped = false;
	
	public StopWatchUtility() {
		
	}
	
	public void startWatch(View view) {
		showStopButton();
		 showStopButton(); 
		 if(stopped){
			 startTime = System.currentTimeMillis() - elapsedTime; 
			 } else { 
				 startTime = System.currentTimeMillis(); 
	     } 
		 mHandler.removeCallbacks(watchThread);
		 mHandler.postDelayed(watchThread, 0);
	}
	
	public void stopWatch(View view) {
		hideStopButton();;
		 hideStopButton(); 
		 mHandler.removeCallbacks(watchThread); 
		 stopped = true;
	}
	
	
	
	private void showStopButton(){ 
		((Button)findViewById(R.id.startButton)).setVisibility(View.GONE);
		((Button)findViewById(R.id.stopButton)).setVisibility(View.VISIBLE); 
	
	} 
	
	private void hideStopButton(){
		((Button)findViewById(R.id.startButton)).setVisibility(View.VISIBLE);
		((Button)findViewById(R.id.stopButton)).setVisibility(View.GONE); 
	}
	
	private void updateTimer (float time){
		secs = (long)(time/1000); 
		mins = (long)((time/1000)/60); 
		hrs = (long)(((time/1000)/60)/60); 
		secs = secs % 60;
		seconds=String.valueOf(secs); if(secs == 0){
			seconds = "00";
			} 
		if(secs <10 && secs > 0){ 
			seconds = "0"+seconds; 
			} 
		mins = mins % 60; 
		minutes=String.valueOf(mins);
		
		if(mins == 0){
			minutes = "00"; 
		} 
		
		if(mins <10 && mins > 0){
			minutes = "0"+minutes; 
		} 
		hours=String.valueOf(hrs); if(hrs == 0){
			hours = "00"; 
			} 
		if(hrs <10 && hrs > 0){
			hours = "0"+hours; 
			} 
		milliseconds = String.valueOf((long)time);
		if(milliseconds.length()==2){ 
			milliseconds = "0"+milliseconds; 
			} 
		if(milliseconds.length()<=1){ 
			milliseconds = "00"; 
			} 
		milliseconds = milliseconds.substring(milliseconds.length()-3, milliseconds.length()-2); 
		((TextView)findViewById(R.id.timer)).setText(hours + ":" + minutes + ":" + seconds);
		((TextView)findViewById(R.id.timerMs)).setText("." + milliseconds); 
	}
	
	
	private Runnable watchThread = new Runnable() {

		public void run() {
			elapsedTime = System.currentTimeMillis() - startTime;
			updateTimer(elapsedTime);
			mHandler.postDelayed(this,REFRESH_RATE);
		}
		
	};
	
}
