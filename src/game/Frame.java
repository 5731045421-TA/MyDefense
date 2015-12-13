package game;
import java.awt.*;

import javax.swing.JFrame;

public class Frame extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String title = "My Defense";
	public static Dimension size = new Dimension(700,550);
	
	
	public Frame() {
		setTitle(title);
		setSize(size);
		setResizable(false);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new GridLayout(1, 1, 0, 0));
		
		GameScreen GameScreen = new GameScreen(this);
		add(GameScreen);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Frame frame = new Frame();


	}
}
