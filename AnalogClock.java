package com.mathworks.clock.dec26;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Calendar;


public class AnalogClock extends Clock
{
	  private static final long serialVersionUID = 1L;
	  
	  /** The coordinates used to paint the clock s. */
	  private int xSec, ySec, xMin, yMin, xHour, yHour,xNumOffset,numOffset;

	  private  int clockDiameter ;

	  /** The length of the clock s relative to the clock size. */
	  private int secondLength ; 	
	  private int minuteLength;	
	  private int hourLength ;	

	  /** The distance of the dots from the origin (center of the clock). */
	  private int dotDistance;	

	  private int bigDot;;
	  private int smallDot;
	  private int numSize;
	  
	  private int hour,minute,second;
	  
	  /** constant distance from boundary of clock **/
	  private final int secondOffset=60;
	  private final int minuteOffset=100;
	  private final int hourOffset=170;
	  private final int dotDistanceOffset=40;
	  
	  private Graphics2D g2 ;
	  
	  private static Color hColor;
	  private static Color mColor;
	  private static Color sColor;
	  private static Color bgColor;
	  
	  private BufferedImage clockImage;
		
	 private static AnalogClock instance = null;
	 
	 
	 private AnalogClock()
	 {
		 setAnalogColor();
	 }
	 
	 static AnalogClock getInstance()
	 { 
		 if(instance == null)
		 {
			 instance = new AnalogClock();
		 }
		 return instance;
	 }
	 
	 static void setAnalogColor()
	 {
		 bgColor = new Color(204,255,255);
		 sColor = Color.ORANGE ;
		 mColor = Color.MAGENTA;
		 hColor = Color.GREEN;
	 }
	 static void setHourColor(Color hourColor)
	 {
		 hColor = hourColor;
	 }
	 static void setMinColor(Color minColor)
	 {
		 mColor = minColor;
	 }
	 static void setSecColor(Color secColor)
	 {
		 sColor = secColor;
	 }
	 static void setBgColor(Color backgroundColor)
	 {
		 bgColor = backgroundColor;
	 }
	 protected Integer getHourDisp(int hour)
	 { 
		 return hour;
	 }
	 
	 protected Integer getMinDisp(int min)
	 { 
		 return min;
	 }
	 
	 protected Integer getSecDisp(int sec)
	 {
		 return sec;
	 }
	  	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		 
		g2 = (Graphics2D)g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.clearRect(0, 0, getWidth(), getHeight());
	    
		int w = getWidth();
		int h = getHeight();
		clockDiameter = getClockDimensions(w,h);
		g2.setColor(bgColor);
		// Create the clock face background image if this is the first time,
		// or if the size of the panel has changed
		if (clockImage == null|| clockImage.getWidth() != w|| clockImage.getHeight() != h) 
		{
			System.out.println("analog numbers drawn");
			clockImage = (BufferedImage)(this.createImage(w, h));
			// now get a graphics context from this image
			Graphics2D gc = clockImage.createGraphics();
			gc.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			RenderingHints.VALUE_ANTIALIAS_ON);
			drawNumbers(gc);
		}
		
		
	    
	    //g2.fillOval(0, 0,clockDiameter, clockDiameter);   
		
		// Draw the clock face from the precomputed image
		g2.drawImage(clockImage, null, 0, 0);
	    
	    drawClockHands(g2, hColor, mColor, sColor);

	    
	}
	
	
	private void drawClockHands(Graphics2D g,Color hColor,Color mColor,Color sColor)
	{
		
		secondLength = clockDiameter / 2 - secondOffset;
		minuteLength = clockDiameter / 2 - minuteOffset;
		hourLength = clockDiameter / 2 - hourOffset;	
		
		
		 hour = getHourDisp(getHour());
		 minute = getMinDisp(getMinute());
		 second = getSecDisp(getSecond());
		
		/** Getting coordinates for clock hands **/
		xSec = getCoordinates(second, secondLength).x;
        ySec = getCoordinates(second, secondLength).y;
        xMin = getCoordinates(minute, minuteLength).x;
        yMin = getCoordinates(minute, minuteLength).y;
        xHour = getCoordinates(hour * 5 + getRelativeHour(minute), hourLength).x;
        yHour = getCoordinates(hour * 5 + getRelativeHour(minute), hourLength).y;
        
		/** Draw the clock hands **/
	    g.setColor(sColor);
	    g.setStroke(new BasicStroke(7));
	    g.drawLine(clockDiameter / 2, clockDiameter / 2, xSec, ySec);
	    g.setColor(mColor);
	    g.drawLine(clockDiameter / 2, clockDiameter / 2, xMin, yMin);
	    g.setColor(hColor);
	    g.drawLine(clockDiameter / 2, clockDiameter / 2, xHour, yHour);  
	    
	    
	}
	
	private void drawNumbers(Graphics2D g)
	{
		dotDistance = clockDiameter / 2 - dotDistanceOffset;
		bigDot = clockDiameter/30;
		smallDot = bigDot/3;
		numSize=bigDot+5;
		xNumOffset = 20;
		numOffset = 10;
		
		/** Draw numbers **/
	    for (int i = 0; i < 60; i++) 
	    {
	      Point coordinates = getCoordinates(i, dotDistance);
	      
	      if ((i % 5 == 0) && (i%15!=0)) 
	      {
	    	g.setColor((i <= Calendar.getInstance().get(Calendar.SECOND)) ? Color.RED: Color.PINK);
	        /** big dots **/
	        g.fillOval(coordinates.x - (bigDot / 2),
	              coordinates.y - (bigDot / 2),
	              bigDot,
	              bigDot);
	        
	      } 
	      else if((i%5 != 0) && (i%15!=0))
	      {
	    	g.setColor((i <= Calendar.getInstance().get(Calendar.SECOND)) ? Color.RED: Color.PINK);
	        /** small dots **/
	        g.fillOval(coordinates.x - (smallDot / 2),coordinates.y - (smallDot / 2),
	              smallDot,
	              smallDot);
	      }
	      else
	      {
	    	 
	    	  g.setFont(new Font("Bodoni MT Black", Font.BOLD, numSize));
	    	  g.setColor(Color.BLUE);
	    	  if(i==0)
	    	  {
	    		  g.drawString("12", coordinates.x-xNumOffset ,
			              coordinates.y+numOffset);
	    	  }
	    	  else
	    	  {
	    		  g.drawString(Integer.toString(i/5), coordinates.x -numOffset,
			              coordinates.y+numOffset );
	    	  }
		      
	      }
	    }
	}
  
  


  /**
   * Returns how much the hour  should be ahead
   * according to the minutes value.
   * 04:00, return 0.
   * 04:12, return 1, so that we move the hour le ahead of one dot. 
   * @param min The current minute.
   * @return The relative offset to add to the hour . 
   */
  private int getRelativeHour(int min) 
  {
    return min / 12;
  }

  
  
  /**
   * Converts current second/minute/hour to x and y coordinates.
   * @param min The current minute
   * @param radius The radius length    
   * @return the coordinates point
   */
  private Point getCoordinates(int timeStep, int radius) 
  {
    double t = 2 * Math.PI * (timeStep-15) / 60;
    int x = (int)(clockDiameter / 2 + radius * Math.cos(t));
    int y = (int)(clockDiameter / 2 + radius * Math.sin(t));
    return new Point(x, y);
  }   
  
  
  
  
}
