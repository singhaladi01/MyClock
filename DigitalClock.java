package com.mathworks.clock.dec26;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class DigitalClock extends Clock 
{
	private String dispTime;
	private String dispHour,dispMinute,dispSecond;
	private static DigitalClock instance = null;
	
	 private DigitalClock()
	 {
		 
	 }
	 
	 static DigitalClock getInstance()
	 { 
		 if(instance == null)
		 {
			 instance = new DigitalClock();
		 }
		 return instance;
	 }
    
	 protected String getHourDisp(int hour)
	 {
		 dispHour  = Integer.toString(hour);
		 
		 if(getHour()<10)
			 dispHour = "0"+ dispHour;
		 
		 return dispHour;
	 }
	 
	 protected String getMinDisp(int min)
	 {
		 dispMinute = Integer.toString(min);
		 
		 if(getMinute()<10)
			 dispMinute = "0"+ dispMinute;
		 
		 return dispMinute;
	 }
	 
	 protected String getSecDisp(int sec)
	 {
		 dispSecond = Integer.toString(sec);
		 
		 if(getSecond()<10)
			 dispSecond = "0"+ dispSecond;
		 
		 return dispSecond;
	 }
		
    public void paintComponent(Graphics g) 
    {
    	super.paintComponent(g);
    	Graphics2D g2 = (Graphics2D)g;
	    g2.clearRect(0, 0, getWidth(), getHeight());
	    
	    getHourDisp(getHour());
	    getMinDisp(getMinute());
	    getSecDisp(getSecond());
	    
        int length = getClockDimensions(getWidth(),getHeight());
        dispTime= dispHour+ ":" +dispMinute+ ":" + dispSecond;
        
        Font myFont = new Font("SansSerif", Font.PLAIN, length / 5);
        g.setFont(myFont);
        g.drawString(dispTime, (int) length/6, length/2);

    }
    
    
    
 
    
    

    


}
