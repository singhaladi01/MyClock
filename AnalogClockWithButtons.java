package com.mathworks.clock.dec26;


import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JPanel;

public class AnalogClockWithButtons extends JPanel
{
	public AnalogClockWithButtons() 
	{
		System.out.println("Analog with Buttons");
		setLayout(new GridLayout(2, 1,50,10));
		setBackground(Color.WHITE);
		add(AnalogClock.getInstance());
		add(new AnalogButtonsPanel());
	}
}
