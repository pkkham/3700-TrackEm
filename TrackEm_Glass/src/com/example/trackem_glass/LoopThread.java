package com.example.trackem_glass;

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//
//	LoopThread.java
//
//		AUTHOR: Adam Eichelkraut
//		DATE: 3/23/14
//		USAGE: Derive from this class, then implement the loopProcess
//			method.
//		SYSTEM: Java jdk v1.7.0_02 on Dell XPS L501X running
//					Intel Core i5 CPU
//		DESCRIPTION: This class is the base class for objects that
//			require single repetitive function to happen in its own
//			thread many times. Simply derive and override to implement.
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
//Imports
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

public abstract class LoopThread extends Thread
{

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Public Constants
	public static final int DEFAULT_DELAY = 100;
	public static final int NO_DELAY = 0;
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	//Member Variables
	private int m_delay; //millisecond delay
	private boolean m_continue;

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// LoopThread(int)
	//	PURPOSE: Constructor takes an integer that represents
	//		the amount of milliseconds between each thread
	//		process. Mainly for visually seeing logging.
	//	PARAMETERS:
	//		delay: amount to delay
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public LoopThread(int delay) 
	{
		super();
		m_delay = delay;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// loopProcess()
	//	PURPOSE: Abstract method that must be overridden
	//		by derived classes. Defines the processes
	//		that will occur on each repetition.
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	protected abstract void loopProcess();

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// run()
	//	PURPOSE: Method defines the thread's overridden
	//		abstract method. While continue is true,
	//		keep looping the abstract loopProcess method
	//		and sleep if there is a delay.
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void run()
	{
		m_continue = true;

		while (m_continue) 
		{
			loopProcess();
			if (m_delay > 0)
			{
				try 
				{
					this.sleep(m_delay);
				}
				catch (InterruptedException ie) {}
			}
		}
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	// stopLoopThread()
	//	PURPOSE: Method interrupts the current thread by
	//		setting the continuation boolean to false,
	//		causing the loop to exit. Also, if sleeping,
	//		the method calls the interrupt method.
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	public void stopLoopThread()
	{
		m_continue = false;
		this.interrupt();
	}
}