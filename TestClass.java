package com.mathworks.clock.dec26;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class TestClass extends JPanel {

	public static void main(String[] args) 
	{
		 Container container;
	
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(Color.black);
		mainPanel.setSize(100, 100);
		
		
		JFrame mainFrame = new JFrame();
		container = mainFrame.getContentPane();
		mainFrame.setSize(400, 400);
		container.setBackground(Color.WHITE);
		
		mainFrame.setLayout(new BorderLayout());
		mainFrame.add(mainPanel, BorderLayout.CENTER);
		mainFrame.pack();
		
		mainFrame.setVisible(true);
		

	}
	

}
