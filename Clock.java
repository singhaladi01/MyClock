package com.mathworks.clock.dec26;



import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.JPanel;


public abstract class Clock extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private Thread thread; 
	
	/** The size of the clock panel */
    private final int panelWidth = Toolkit.getDefaultToolkit().getScreenSize().width/2;
    private final int panelHeight = Toolkit.getDefaultToolkit().getScreenSize().height/2;
    protected boolean isRunnable;
    private int hour;
    private int minute;
    private int second;
    private Alarm alarmPanel;

	public Clock()
	{
		System.out.println("Clock");
		setPreferredSize(new Dimension(panelWidth, panelHeight));
		thread = new Thread(this);
		thread.start();
		isRunnable = true;
		//alarmPanel = Alarm.getInstance();
		alarmPanel = new Alarm();
		add(alarmPanel);
		
	}
	
	
	
	public int getHour()
	{
		hour= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		return hour;
	}
	
	public int getMinute()
	{
		minute= Calendar.getInstance().get(Calendar.MINUTE);
		return minute;
	}
	
	public int getSecond()
	{
		second=Calendar.getInstance().get(Calendar.SECOND);
		return second;
	}
	public int getClockDimensions(int width, int height)
	{
		  return (width <= height) ? width : height;
	}
	
	protected abstract Object getHourDisp(int hour);
	protected abstract Object getMinDisp(int min);
	protected abstract Object getSecDisp(int sec);
	
	  
	  /** At each iteration we recalculate the coordinates of the clock hands,
       and repaint everything. **/
	  public void run()
	  {
	    while(true)
	    {
	    	TimeZone.setDefault(null);
			System.setProperty("user.timezone", "");
	      try
	      {
	    	  if(isRunnable)
	    	  {
	    		repaint();
	    		
	  	        Thread.sleep(100);
	    	  }
	        
	      } 
	      catch(InterruptedException ie)
	      {
	        ie.printStackTrace();
	      } 
	    }
	  }
	
}
