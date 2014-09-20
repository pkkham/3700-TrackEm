package com.example.trackem_glass.tests;

import com.example.trackem_glass.TrackEmActivity;
import com.example.trackem_glass.R;

import android.test.ActivityInstrumentationTestCase2;
import android.test.ViewAsserts;
import android.view.View;
import android.widget.Button;

/*
 * Tests the activity in TrackEm Activity.
 * 
 * @author Jared Brown
 *
 */

public class TrackEmActivityTests extends ActivityInstrumentationTestCase2<TrackEmActivity> {

	private TrackEmActivity m_testActivity;  // the activity under test
	private Button m_testButton;

    public TrackEmActivityTests() {
    	super(TrackEmActivity.class);
    }

	protected void setUp() throws Exception {
		super.setUp();
		m_testActivity = this.getActivity();
		m_testButton = (Button)m_testActivity.findViewById(R.id.start_stop_button);
		
		setActivityInitialTouchMode(true);
	}
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testPreconditions() {
		assertNotNull("m_testActivity is null", m_testActivity);
		assertNotNull("m_testButton is null", m_testButton);
	}
	
	public void testStartStopButton_initialText() {
		final View decorView = m_testActivity.getWindow().getDecorView();
		
		ViewAsserts.assertOnScreen(decorView, m_testButton);
		
		final String expectedText = "Start";
		final String actualText = m_testButton.getText().toString();
		
		assertEquals(expectedText, actualText);
	}
}
