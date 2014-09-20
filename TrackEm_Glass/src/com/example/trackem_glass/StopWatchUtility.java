package com.example.trackem_glass;

import android.app.Activity;

import com.example.trackem_glass.TrackEmActivity.PlaceholderFragment.OnTimeUpdateListener;


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
	
	//interfaces
	private OnTimeUpdateListener m_time_update_listener;

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


	private String updateTimer(float time) {
		float currentTime = startTime - time;
		secs = (long) (currentTime / 1000);
		mins = (long) ((currentTime / 1000) / 60);
		hrs = (long) (((currentTime / 1000) / 60) / 60);
		secs = secs % 60;
		seconds = String.valueOf(secs);
		if (secs == 0) {
			seconds = "00";
		}
		if (secs < 10 && secs > 0) {
			seconds = "0" + seconds;
		}
		mins = mins % 60;
		minutes = String.valueOf(mins);

		if (mins == 0) {
			minutes = "00";
		}

		if (mins < 10 && mins > 0) {
			minutes = "0" + minutes;
		}
		hours = String.valueOf(hrs);
		if (hrs == 0) {
			hours = "00";
		}
		if (hrs < 10 && hrs > 0) {
			hours = "0" + hours;
		}
		milliseconds = String.valueOf((long) currentTime);
		if (milliseconds.length() == 2) {
			milliseconds = "0" + milliseconds;
		}
		if (milliseconds.length() <= 1) {
			milliseconds = "00";
		}
		milliseconds = milliseconds.substring(milliseconds.length() - 3,
				milliseconds.length() - 2);
		
		return hours + ":" + minutes + ":" + seconds;

	}
	
	private class UpdateThread extends LoopThread {
		private Integer i = 0;

		public UpdateThread(int delay) {
			super(refreshRate);
			
		}

		@Override
		protected void loopProcess() {
			i++;
			m_activity.runOnUiThread(new Runnable() 
			{
			     @Override
			     public void run() 
			     {
						//m_time_update_listener.onTimeUpdate(updateTimer(System.currentTimeMillis()));
						m_time_update_listener.onTimeUpdate(i.toString());
			     }
			});
		}
		
	}

}
