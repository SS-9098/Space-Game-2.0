package Main;

import java.awt.Color;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Frame extends JFrame
{

	public Frame() 
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(500,600);
		this.getContentPane().setBackground(Color.black);
		this.setTitle("Space Game 2.0");
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	}

}
