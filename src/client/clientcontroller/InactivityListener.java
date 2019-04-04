package client.clientcontroller;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//THIS CODE WAS TAKEN FROM THE INTERNET: https://tips4java.wordpress.com/2008/10/24/application-inactivity/

/**
 * A class that monitors inactivity in an application.
 *
 * It does this by using a Swing Timer and by listening for specified
 * AWT events. When an event is received the Timer is restarted.
 * If no event is received during the specified time interval then the
 * timer will fire and invoke the specified Action.
 *  
 * @author Rob Camick
 * @version 1.0
 * @since October 24, 2008
 */
public class InactivityListener implements ActionListener, AWTEventListener
{
	public final static long KEY_EVENTS = AWTEvent.KEY_EVENT_MASK;

	public final static long MOUSE_EVENTS = AWTEvent.MOUSE_MOTION_EVENT_MASK + AWTEvent.MOUSE_EVENT_MASK;

	public final static long USER_EVENTS = KEY_EVENTS + MOUSE_EVENTS;

	/**
	 * Is the window
	 */
	private Window window;
	/**
	 * Is the action to be invoked after the interval of inactivity
	 */
	private Action action;
	/**
	 * Is the inactivity interval
	 */
	private int interval;
	/**
	 * Specifies the events to listen for 
	 */
	private long eventMask;
	/**
	 * Is the timer
	 */
	private Timer timer = new Timer(0, this);

	/**
	 * Constructs an InactivityListener object with the specified window, action to invoke. 
	 * Listens for USER_EVENTS and uses a default inactivity interval of 1 minute. 
	 * @param window is the specified window
	 * @param action is the action to invoke
	 */
	public InactivityListener(Window window, Action action)
	{
		this(window, action, 1);
	}

	/**
	 * Constructs an InactivityListener object with the specified window, action to invoke, inactivity interval. 
	 * Listens for USER_EVENTS. 
	 * @param window is the specified window
	 * @param action is the action to invoke
	 * @param interval is the inactivity interval
	 */
	public InactivityListener(Window window, Action action, int interval)
	{
		this(window, action, interval, USER_EVENTS);
	}

	/**
	 * Constructs an InactivityListener object with the specified window, action to invoke, inactivity interval, and events to check for. 
	 * @param window is the specified window
	 * @param action is the action to invoke
	 * @param minutes is the inactivity interval in minutes
	 * @param eventMask specifies the events to be checked for
	 */
	public InactivityListener(Window window, Action action, int minutes, long eventMask)
	{
		this.window = window;
		setAction( action );
		setInterval( minutes );
		setEventMask( eventMask );
	}

	/**
	 * The Action to be invoked after the specified inactivity period
	 * @param action is the action to be invoked
	 */
	public void setAction(Action action)
	{
		this.action = action;
	}

	/**
	 * The interval before the Action is invoked specified in minutes
	 * @param minutes is the interval in minutes
	 */
	public void setInterval(int minutes)
	{
		setIntervalInMillis(minutes * 60000);
	}

	/**
	 * The interval before the Action is invoked specified in milliseconds
	 * @param interval is the interval in miliseconds
	 */
	public void setIntervalInMillis(int interval)
	{
		this.interval = interval;
		timer.setInitialDelay(interval);
	}

	/**
	 * A mask specifying the events to be passed to the AWTEventListener
	 * @param eventMask is the mask specifying which events to be passed
	 */
	public void setEventMask(long eventMask)
	{
		this.eventMask = eventMask;
	}

	/**
	 * Start listening for events.
	 */
	public void start()
	{
		timer.setInitialDelay(interval);
		timer.setRepeats(false);
		timer.start();
		Toolkit.getDefaultToolkit().addAWTEventListener(this, eventMask);
	}

	/**
	 * Stop listening for events
	 */
	public void stop()
	{
		Toolkit.getDefaultToolkit().removeAWTEventListener(this);
		timer.stop();
	}

	/**
	 * Implement ActionListener for the Timer
	 */
	public void actionPerformed(ActionEvent e)
	{
		ActionEvent ae = new ActionEvent(window, ActionEvent.ACTION_PERFORMED, "");
		action.actionPerformed(ae);
	}

	/**
	 * Implement AWTEventListener
	 */
	public void eventDispatched(AWTEvent e)
	{
		if (timer.isRunning())
			timer.restart();
	}
}