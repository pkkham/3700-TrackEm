package com.example.trackem_glass;

import android.content.Context;
import android.widget.TextView;

import com.example.trackem_glass.StopWatchUtility.OnTimeUpdateListener;

public class TimeUpdateTextView extends TextView implements OnTimeUpdateListener {

	public TimeUpdateTextView(Context context) {
		super(context);
		
	}

	@Override
	public void onTimeUpdate(String nextTime) {
		this.setText(nextTime);
	}
}
