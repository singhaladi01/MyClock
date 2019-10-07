package com.mathworks.clock.dec26;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.IOException;

import java.util.Calendar;
import java.util.TimeZone;

import javax.sound.sampled.LineUnavailableException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Alarm extends JPanel implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	private JButton alarmButton;
	/** Frame for new alarm window, which is asking for setting alarm **/
	private JFrame alarmFrame;
	private JButton offButton;
	private int hour,minute,minuteNext,seconds;
	private boolean timeMatched,runAlarmThread,offAlarm;
	private Thread alarmThread;
	//private static Alarm instance = null;
	
	public Alarm()
	{
		setBackground(Color.WHITE);
		alarmButton = new JButton("Set Alarm");
		add(alarmButton);
		setButtonAction();
	}	
	
	/* static Alarm getInstance()
	 { 
		 System.out.println("Alarm");
		 if(instance == null)
		 {
			 instance = new Alarm();
		 }
		 return instance;
	 }*/
	
	public void setButtonAction()
	{
		alarmFrame = new JFrame();
		alarmFrame.setTitle("Choose the time");
		alarmFrame.setLayout(null);
		offButton = new JButton("Off Alarm");			
		timeMatched = false;
		offAlarm = false;
		alarmThread = new Thread(this);
		runAlarmThread=false;
	
		alarmButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				String[] hourList = new String[24];
				
				for(int i=0;i<hourList.length;i++)
				{
					hourList[i] = String.valueOf(i);
				}
				
				
				JComboBox<String> hourBox = new JComboBox<String>(hourList);
				hourBox.setBounds(0, 0,50,20);
				alarmFrame.add(hourBox);
				
				String[] minSecList = new String[60];
				for(int i=0;i<minSecList.length;i++)
				{
					minSecList[i] = String.valueOf(i);
				}
				
				JComboBox<String> minuteBox = new JComboBox<String>(minSecList);
				minuteBox.setBounds(50, 0,50,20);
				alarmFrame.add(minuteBox, BorderLayout.LINE_END);
				
				
				
				JComboBox<String> secondsBox = new JComboBox<String>(minSecList);
				secondsBox.setBounds(100, 0,50,20);
				alarmFrame.add(secondsBox, BorderLayout.LINE_END);
				
				
				JButton okButton = new JButton("Set");
				alarmFrame.add(okButton, BorderLayout.LINE_END);
				okButton.setBounds(160, 0, 100, 20);
				
				alarmFrame.setSize(300, 90);
				alarmFrame.setVisible(true);
				
				okButton.addActionListener(new ActionListener() 
				{
					
					@Override
					public void actionPerformed(ActionEvent e) 
					{
						if(!runAlarmThread)
						{
							runAlarmThread= true;
							alarmThread.start();
						}
						else
						{
							offAlarm = true;
							timeMatched = false;
							try 
							{
								alarmAction(timeMatched);
							}
							catch (IOException e1) 
							{
								e1.printStackTrace();
							} catch (LineUnavailableException e1) {
								e1.printStackTrace();
							}
						}
						
						hour =Integer.parseInt((String)hourBox.getSelectedItem());
						
						minute = Integer.parseInt((String)minuteBox.getSelectedItem());
						
						seconds = Integer.parseInt((String)secondsBox.getSelectedItem());
						
						alarmFrame.setVisible(false);
						hourBox.setSelectedIndex(0);
						minuteBox.setSelectedIndex(0);
						secondsBox.setSelectedIndex(0);
					}
				});
				
			}
		});
		
		offButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					offAlarm =true;
					timeMatched = false;
					alarmAction(timeMatched);
				} 
				catch (IOException e) 
				{
					e.printStackTrace();
				} catch (LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		});

	}
	
	public void run()
	{
	    while(true)
	    {
		      try
		      { 
		    	if(runAlarmThread)
		    	{
		    		 compareTime();
			  	     Thread.sleep(1000);
		    	}
		    	 
		   	  } 
		      catch(InterruptedException ie)
		      {
		        ie.printStackTrace();
		      } 
		      catch (IOException e) 
		      {
				e.printStackTrace();
		      } catch (LineUnavailableException e) {
				e.printStackTrace();
			}
	    }
	  }

	
	public void compareTime() throws IOException, LineUnavailableException 
	{
		TimeZone.setDefault(null);
		System.setProperty("user.timezone", "");
		
		
		if(hour == Calendar.getInstance().get(Calendar.HOUR_OF_DAY) 
				&& minute== Calendar.getInstance().get(Calendar.MINUTE) 
				&& seconds == Calendar.getInstance().get(Calendar.SECOND))
		{
			timeMatched = true;
			alarmAction(timeMatched);
			offAlarm = false;
			minuteNext = (minute+1)%60;
			
		}
		else if(minute == 59 && hour== Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
				&&  Calendar.getInstance().get(Calendar.MINUTE) == 59
				&& Calendar.getInstance().get(Calendar.SECOND)>=seconds)
		{
			while(hour== Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
					&&  Calendar.getInstance().get(Calendar.MINUTE) == 59
					&&(Calendar.getInstance().get(Calendar.SECOND))!= 59)
				{
					if(offAlarm)
					{ 
						timeMatched = false;
						break;
					}
					else
					{
						timeMatched = true;
					}
					
					alarmAction(timeMatched);
				}
			
		}
		else if(minute == 59 && 
				 Calendar.getInstance().get(Calendar.HOUR_OF_DAY) == (hour+1)%24 
				&&  Calendar.getInstance().get(Calendar.MINUTE) == 0
				&& Calendar.getInstance().get(Calendar.SECOND)<= seconds)
				{
					if(offAlarm)
					{ 
						timeMatched = false;
					}
					else
					{
						timeMatched = true;
					}
					
					alarmAction(timeMatched);
				}
		else if(minute != 59 && hour == Calendar.getInstance().get(Calendar.HOUR_OF_DAY) 
				&&minute <= Calendar.getInstance().get(Calendar.MINUTE)
				&& Calendar.getInstance().get(Calendar.MINUTE) <minuteNext)
				
				{
					while((Calendar.getInstance().get(Calendar.SECOND))!= seconds)
					{
						if(offAlarm)
						{
							timeMatched = false;
							break;
						}
						else
						{
							timeMatched = true;
						}
						alarmAction(timeMatched);
					}
					
			
			}
		else
		{
			timeMatched = false;
			alarmAction(timeMatched);
		}
	}
	
	public void alarmAction(boolean actAlarm) throws IOException, LineUnavailableException
	{
		
		if(actAlarm)
		{
			setBackground(Color.green);
			add(offButton, BorderLayout.LINE_END);
			offButton.setVisible(true);
			SoundUtils.tone(1000,100);
		}
		else
		{
			setBackground(Color.WHITE);
			offButton.setVisible(false);
		}
	}
}
