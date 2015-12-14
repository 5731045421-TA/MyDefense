package ui;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.JFrame;

import game.Resource;

public class StartScreen extends JComponent implements Runnable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage startBG = null;
	private boolean isFirst = true;
	public Thread startThread = new Thread(this);
	
	static{
		try {
			ClassLoader loader = StartScreen.class.getClassLoader();
			startBG = ImageIO.read(loader.getResource("res/startBG.png"));
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	public StartScreen(JFrame frame) {
		setPreferredSize(new Dimension(700, 550));
		
		
		frame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}
	int counter = 0;
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(startBG, null, 0, 0);
		
		if(counter == 3){
			g2.setFont(new Font("Courier New", Font.ITALIC, 50));
			g2.setColor(Color.WHITE);
			g2.drawString("HERE", 300, 250);
			counter = 0;
		}else counter++;
		
		playStartSong();
	}

	@Override
	public void run() {
		while(true){
			
			if(isFirst){
				
				isFirst = false;
			}
			
			repaint();

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	public void playStartSong(){
		Resource.soundTrack.play();
	}
	
	
	
}
