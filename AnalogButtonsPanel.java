package com.mathworks.clock.dec26;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;

public class AnalogButtonsPanel extends JPanel
{
	
	
	public AnalogButtonsPanel() 
	{
		AnalogButtons();
		setVisible(true);
	}
	
	private void AnalogButtons()
	 {
		JButton changeHourColor,changeMinColor,changeSecColor,changeBgColor,resetColorBtn;
		
		
		setBackground(Color.WHITE);
		changeHourColor= new JButton("Hour Color");
		changeMinColor= new JButton("Minute Color");
		changeSecColor= new JButton("Second Color");
		changeBgColor= new JButton("Background Color");
		resetColorBtn= new JButton("Reset Color");
		
		add(changeHourColor);
		add(changeMinColor);
		add(changeSecColor);
		add(changeBgColor);
		add(resetColorBtn);
		
		
		changeHourColor.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changeColor("hour");
				 
			}
		});
		
		changeMinColor.addActionListener(new ActionListener()
		  {
			  
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changeColor("minute");
			}
		});
		
		changeSecColor.addActionListener(new ActionListener()
		  {
			  
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changeColor("second");
				 
			}
		});
		  
		  changeBgColor.addActionListener(new ActionListener() 
		  {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				changeColor("background");
			}
		});
		  
		  resetColorBtn.addActionListener(new ActionListener() 
		  {
			  
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				AnalogClock.setAnalogColor();
				
				
			}
		});
	 }
	
	
	private void changeColor(String handName)
	 {
		 	Color initialcolor=Color.BLACK;  
		 	Color colorChosen;
		 	Clock analogClock = AnalogClock.getInstance();
		 	colorChosen=JColorChooser.showDialog(analogClock,"Select a color",initialcolor);
		 	analogClock.isRunnable=false;
		 	analogClock.setVisible(false);
			if(handName.equals("hour"))
			{
				AnalogClock.setHourColor(colorChosen);
			}
			else if(handName.equals("minute"))
			{
				AnalogClock.setMinColor(colorChosen);
			}
			else if(handName.equals("second"))
			{
				AnalogClock.setSecColor(colorChosen);
			}
			else
			{
				AnalogClock.setBgColor(colorChosen);
			}
			
			analogClock.isRunnable=true;
			analogClock.setVisible(true);
	 }
	
}

