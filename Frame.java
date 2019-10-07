package com.mathworks.clock.dec26;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class Frame extends JFrame
{
	private static final long serialVersionUID = 1L;
	
	/** The size of the frame. */
	static final int HORIZONTAL_SIZE = Toolkit.getDefaultToolkit().getScreenSize().width;
	static final int VERTICAL_SIZE = Toolkit.getDefaultToolkit().getScreenSize().height;
	
	private Container frameContainer;
	private JFrame frame;
	
	//private JPanel initPanel;
	private JPanel activePanel;
	
	
	 
	 public Frame()
	 {
		 	
		 	frame = new JFrame("CLOCK");
			frameContainer = frame.getContentPane();
			frameContainer.setBackground(Color.WHITE);
			frame.setSize(HORIZONTAL_SIZE/2,VERTICAL_SIZE/2);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			//initPanel = new AnalogClockWithButtons();
			//initPanel = DigitalClock.getInstance();
			activePanel = new TogglePanel(new AnalogClockWithButtons()).returnActivePanel();
			frame.add(activePanel,BorderLayout.CENTER);
			frame.add(new TogglePanel(activePanel), BorderLayout.EAST);
			
			frame.pack();
			frame.setVisible(true);
			frame.setResizable(true);
			
			/*initPanel=new TogglePanel(initPanel).returnActivePanel();
			activePanel.setVisible(true);
			frame.add(activePanel,BorderLayout.CENTER);*/
			
						
	 }
	 
	 
	 
	 
	  
	public static void main(String[] args) 
	{

		new Frame();
		/*JFrame jFrame = new JFrame();
		System.out.println("revive");
		jFrame.add(new AnalogClockWithButtons());
		jFrame.setVisible(true);*/
		
		
	}
	 
	  
}
