package render;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Main.Main;
import game.Resource;

public class StartScreen extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage startBG = null;
	public static boolean isFirst = true;
	public static JButton newGame = new JButton("New Game");

	
	static{
		try {
			ClassLoader loader = StartScreen.class.getClassLoader();
			startBG = ImageIO.read(loader.getResource("res/startBG.png"));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	public StartScreen(JFrame frame) {
		this.setPreferredSize(new Dimension(700, 550));
		this.setLayout(null);
		newGame.setBounds(300, 450, 100, 30);
		this.add(newGame);
		newGame.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//System.out.println("SADcasDFASF");
				frame.remove(Main.start);
				GameScreen GameScreen = new GameScreen(frame);
				frame.add(GameScreen);
				
				frame.setVisible(true);
				frame.validate();
				
				
			}
		});
		
		
		
		
		
	}
	int counter = 0;
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(startBG, null, 0, 0);
		
		playStartSong();
	}

	
	
	public void playStartSong(){
		Resource.coinSound.play();
	}
	
	
	
}
