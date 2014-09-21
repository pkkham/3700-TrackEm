package com.example.trackem_glass;

import android.app.Activity;


/*
 * Handles stop watch functionality of TrackEm.
 * 
 * @author Tyler Hoover
 *
 */
public class StopWatchUtility {

	private long startTime;
	private long elapsedTime;
	private int refreshRate = 100;
	private String hours, minutes, seconds, milliseconds;
	private long secs, mins, hrs, msecs;
	private boolean stopped = false;
	private UpdateThread m_thread;
	private Activity m_activity;	//Hosting activity
	
	
	//=========================================================================
	// Interfaces
	
	// Relays time information to listeners
	public interface OnTimeUpdateListener
	{
		public void onTimeUpdate(String nextTime);
	}
	private OnTimeUpdateListener m_time_update_listener;
	
	//=========================================================================
	
	public StopWatchUtility(Activity hostingActivity) {
		m_activity = hostingActivity;
	}
	
	public void setOnTimeUpdateListener(OnTimeUpdateListener l)
	{ m_time_update_listener = l; }
	
	public void setRefreshRate(int rate)
	{
		refreshRate = rate;
	}

	public void startWatch() {
		stopped = false;
		startTime = System.currentTimeMillis();
		m_thread = new UpdateThread(refreshRate);
		m_thread.start();
	}

	public void stopWatch() {
		if (m_thread != null)
		{
			m_thread.stopLoopThread();
			m_thread = null;
		}
		stopped = true;
	}


    private String updateTimer(long time) {
        float currentTime = time - startTime; //get how much time has passed
        float miliTime = time - startTime;
        currentTime /= 1000; //get time down to seconds
        
        int hours = (int)(currentTime / 3600);
        int minutes = (int)((currentTime % 3600)/60);
        int seconds = (int)((currentTime % 3600) % 60);
        int miliseconds = (int)(miliTime - ((hours * 3600 * 1000) + (minutes * 60 * 1000) + (seconds * 1000)));
        String hrs = "";
        String mins = "";
        String secs = "";
        String milis = "";
        
        
        if (hours < 1) {
            hrs = "00";
        } else if (hours < 10) {
            hrs = "0" + hours;
        } else {
            hrs += hours;
        }
        
        if (minutes < 1) {
            mins = "00";
        } else if (minutes < 10) {
            mins = "0" + minutes;
        } else {
            mins += minutes;
        }
        
        if (seconds < 1) {
            secs = "00";
        } else if (seconds < 10) {
            secs = "0" + seconds;
        } else {
            secs += seconds;
        }
        
        if (miliseconds < 1) {
            milis = "00";
        } else if (miliseconds < 10) {
            milis = "0" + miliseconds;
        } else {
            milis += (int)(miliseconds/10);
        }
        
        
        
        
        return (hours + ":" + mins + ":" + secs + ":" + milis);
        
    }
	
	private class UpdateThread extends LoopThread {

		public UpdateThread(int delay) {
			super(refreshRate);
		}

		@Override
		protected void loopProcess() {
			m_activity.runOnUiThread(new Runnable() 
			{
			     @Override
			     public void run() 
			     {
						m_time_update_listener.onTimeUpdate(updateTimer(System.currentTimeMillis()));
			     }
			});
		}
		
	}

}
