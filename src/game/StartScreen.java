package game;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

public class StartScreen extends JComponent{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage startBG = null;
	static{
		try {
			ClassLoader loader = StartScreen.class.getClassLoader();
			startBG = ImageIO.read(loader.getResource("res/startBG.png"));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	public StartScreen() {
		setPreferredSize(new Dimension(700, 550));
		
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(startBG, null, 0, 0);
	}
	
}
