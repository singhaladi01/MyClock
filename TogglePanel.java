package com.mathworks.clock.dec26;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class TogglePanel extends JPanel

{
	private JToggleButton toggleButton;
	private JPanel activePanel;
	
	public TogglePanel(JPanel panelInstance)
	{
		toggleButton = new JToggleButton("Digital");
		Dimension d = new Dimension(200,50);
		toggleButton.setPreferredSize(d);
		setBackground(Color.WHITE);
		add(toggleButton);
		activePanel=panelInstance;
		toggle();
	}
	
	
	public void toggle()
	{
		 toggleButton.addActionListener(new ActionListener() 
		 {
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				if(activePanel.getClass().getName().contains("AnalogClockWithButtons"))
		        {	
					toggleButton.setText("Analog");
					activePanel.setVisible(false);
					AnalogClock.getInstance().isRunnable = false;
					activePanel = DigitalClock.getInstance();
					DigitalClock.getInstance().isRunnable = true;
		        }
		        else
		        {
		        	toggleButton.setText("Digital");
		        	activePanel.setVisible(false);
		        	DigitalClock.getInstance().isRunnable = false;
		        	activePanel = new AnalogClockWithButtons();
		        	AnalogClock.getInstance().isRunnable = true;   
		        }
				activePanel.setVisible(true);
			}
		});			
	}
	
	public JPanel returnActivePanel()
	{
		return activePanel;
	}

}
