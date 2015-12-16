package Main;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;

import render.StartScreen;

public class Main {
	public static JFrame frame = new JFrame();
	public static String title = "My Defense";
	public static Dimension size = new Dimension(700,550);
	public static StartScreen start;
	
	public static void main(String[] args) {
		frame.setTitle(title);
		frame.setSize(size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setLayout(new GridLayout(1, 1, 0, 0));
		
		start = new StartScreen(frame);
		frame.add(start);
		
		frame.setVisible(true);
		frame.validate();
		
	}

}
