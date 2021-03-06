package render;
import java.awt.*;
import javax.swing.*;
import exception.LoadMissionException;
import game.LoadMap;
import game.Map;
import game.Mob;
import game.Player;
import game.Resource;

import input.KeyHandel;



public class GameScreen extends JComponent implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Thread thread = new Thread(this);
	
	public static boolean isFirst = true;
	public static boolean isWin = false;
	public static boolean pressEnter = false;
	public static boolean isPause = false;
	public static boolean retry = false;

	public static Point mse = new Point(0, 0);

	public static Map map;
	public static LoadMap loadmap;
	public static Store store;
	
	public static int myWidth, myHeight;
	public static int mobType = 0;
	
	public int spawnTime = 1200,spawnFrame = 0;
	
	public static Mob[] mobs;

	
	
	public GameScreen(JFrame frame) {
		
		frame.addMouseMotionListener(new KeyHandel(this));
		frame.addMouseListener(new KeyHandel(this));
		frame.addKeyListener(new KeyHandel(this));
		
		thread.start();
		frame.requestFocus();
	}


	public void define() {
		//mobs = new Mob[Player.killToWin];
		map = new Map();
		loadmap = new LoadMap();
		store = new Store();
		try {
			loadmap.loadSave("save/mission"+Player.level);
		} catch (LoadMissionException e) {
			
		}
		mobs = new Mob[Player.killToWin];
		for(int i =0;i<mobs.length;i++){
			mobs[i] = new Mob();
			
		}
		
		Resource.startScreenSound.stop();
		Resource.congratSound.stop();
		Resource.gameoverSound.stop();
		Resource.soundTrack.loop();//soundTrack
	}
	
	public static boolean gameoverSoundTrigger=true;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		if (isFirst) {
			myWidth = getWidth();
			myHeight = getHeight();
			define();
			isFirst = false;
		}
		
		g2.setBackground(Color.DARK_GRAY);
		g2.clearRect(0, 0, getWidth(), getHeight());
		g2.setColor(Color.BLACK);
		g2.drawLine(map.block[0][0].x - 1, 0, map.block[0][0].x - 1,
				map.block[map.worldHeight - 1][0].y + map.blockSize - 1);
		
		map.draw(g);// Draw map
		
		for(int i = 0;i<mobs.length;i++){
			if(mobs[i].inGame){
				mobs[i].draw(g);
			}
		}
		
		store.draw(g);// Draw the store
		
		if(!isWin && Player.health < 1){//game over
			Resource.shootSound.stop();	
			g2.setColor(new Color(240, 20, 20));
			g2.setFont(new Font("Courier New", Font.BOLD, 40));
			g2.drawString("GAME OVER!", myWidth/2-90, myHeight/2);		
			g2.drawString("Press spacebar to retry", myWidth/2-300, myHeight/2+45);
			
			if(retry){
				
				define();
				Player.killed = 0;
				Player.coinage = 200;
				Player.health = 10;
				Player.level = 1;
				retry = false;
			}
			

		}
		
		
		if(isWin){			
			Resource.soundTrack.stop();
			Resource.shootSound.stop();
			Resource.congratSound.play();
			g2.clearRect(0, 0, getWidth(), getHeight());
			g2.setFont(new Font("Courier New", Font.BOLD, 35));

			if(Player.level < Player.maxLevel){
				
				g2.drawString("Congratulations!", myWidth/2-160, myHeight/2);
				g2.setFont(new Font("Courier New", Font.ITALIC, 20));
				g2.drawString("Press Enter to Continune.....", myWidth/2-170, myHeight/2+45);
			}else {
				drawAllClear(g);
			}

		}
		
	
		
	}
	
	public void drawAllClear(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("Congratulations!", myWidth/2-160, myHeight/2);
		g2.drawString("You Win This Game", myWidth/2-180, myHeight/2+45);
		g2.setFont(new Font("Courier New", Font.ITALIC, 20));
		g2.drawString("press enter to exit", myWidth/2-170, myHeight/2+85);
	
	}
	
	
	
	public void mobSpawner(){
		if(spawnFrame >= spawnTime){
			for(int i =0;i<mobs.length;i++){
				if(!mobs[i].inGame&&!mobs[i].isDead){
					mobs[i].spawnMob(mobType,i);
					break;
				}
			}
			spawnFrame = 0;
		}else{
			spawnFrame++;
		}
	}
	
	
	@Override
	public void run() {
		while (true) {
			
			if (!isFirst && Player.health > 0 &&!isWin) {
				map.logic();
				mobSpawner();
				
				for(int i = 0;i<mobs.length;i++){
					if(mobs[i].inGame){
						mobs[i].move();
					}
				}
			}
			if(isPause){
				try {
					synchronized (thread) {
						thread.wait();
						
					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else{
				
				repaint();
				
				if(isWin){
					try {
						synchronized (thread) {
							thread.wait();
							if(Player.level == Player.maxLevel){
								System.exit(0);
							}else{
								Player.level++;
								define();
								isWin = false;
							}
						}
						
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
					
				}
			}


			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}



	public synchronized void nextMission() {
		synchronized (thread) {
			thread.notify();
		}
		
	}
	
	public synchronized void pause() {
		if(!isPause){
			isPause = true;
		}else{
			synchronized (thread) {
				thread.notify();
			}
			isPause = false;
		}
		
	}
}
