package com.mathworks.clock.dec26;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class WorkingFrame extends JFrame
{
	/** The size of the frame. */
	static final int HORIZONTAL_SIZE = Toolkit.getDefaultToolkit().getScreenSize().width;
	static final int VERTICAL_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private Container container;
	private JPanel activePanel ;
	private JPanel togglePanel;
	
	
	
	public WorkingFrame()
	 {
		 	setTitle("CLOCK");
			container = getContentPane();
			container.setBackground(Color.WHITE);
			setSize(HORIZONTAL_SIZE/2,VERTICAL_SIZE/2);
			setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			activePanel = new AnalogClockWithButtons();
			//activePanel = AnalogClock.getInstance();
			
            add(activePanel, BorderLayout.WEST);
			addtogglePanel();
			
			
			
			pack();
			setVisible(true);
			setResizable(true);
	 }
	
	private void addtogglePanel()
	 {
		
		 JToggleButton toggleButton;
		 togglePanel = new JPanel();
		 toggleButton = new JToggleButton("Digital");
		 Dimension dimension = new Dimension(200,50);
		 toggleButton.setPreferredSize(dimension);
		 togglePanel.setBackground(Color.WHITE);
		 togglePanel.add(toggleButton);
		 add(togglePanel, BorderLayout.EAST);
		 toggleButton.addActionListener(new ActionListener() 
		 {
			
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
				if(activePanel.getClass().getName().contains("AnalogClock"))
				{   
		            toggleButton.setText("Analog");
		            activePanel.setVisible(false);
					AnalogClock.getInstance().isRunnable = false;
					activePanel = DigitalClock.getInstance();
					DigitalClock.getInstance().isRunnable = true;
				}
				else if(activePanel.getClass().getName().contains("DigitalClock"))
				{
					toggleButton.setText("Digital");
					activePanel.setVisible(false);
		        	DigitalClock.getInstance().isRunnable = false;
		        	activePanel = new AnalogClockWithButtons();
		        	//activePanel = AnalogClock.getInstance();
		        	AnalogClock.getInstance().isRunnable = true; 
				}
				
				
				activePanel.setVisible(true);
				add(activePanel, BorderLayout.WEST);
				
			}
		});
					
	}
	
	public static void main(String[] args) 
	{
			new WorkingFrame();
	}
}
