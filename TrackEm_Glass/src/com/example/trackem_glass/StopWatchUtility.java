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
public class StopWatchUtility {

	private long startTime;
	private long elapsedTime;
	private int refreshRate = 100;
	private String hours, minutes, seconds, milliseconds;
	private long secs, mins, hrs, msecs;
	private boolean stopped = false;

	public StopWatchUtility() {

	}

	public void startWatch(View view) {
		stopped = false;
		startTime = System.currentTimeMillis();
	}

	public void stopWatch(View view) {
		stopped = true;
	}


	private String updateTimer(float time) {
		secs = (long) (time / 1000);
		mins = (long) ((time / 1000) / 60);
		hrs = (long) (((time / 1000) / 60) / 60);
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
		milliseconds = String.valueOf((long) time);
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

		public UpdateThread(int delay) {
			super(refreshRate);
			
		}

		@Override
		protected void loopProcess() {
			// TODO Auto-generated method stub
			
			updateTimer(System.currentTimeMillis());
			
		}
		
	}

}
