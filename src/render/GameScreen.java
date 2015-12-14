package render;
import java.applet.AudioClip;
import java.awt.*;
import javax.swing.*;

import exception.LoadMissionException;
import game.KeyHandel;
import game.LoadMap;
import game.Mob;
import game.Resource;
import game.Room;

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
	public boolean allClear = false;

	public static Point mse = new Point(0, 0);

	public static Room room;
	public static LoadMap loadmap;
	public static Store store;
	public static StartScreen startScreen;
	
	public static int myWidth, myHeight;
	public static int coinage = 2000,health = 10;
	public static int killed = 0,killToWin = 0,level = 1,maxLevel = 3;
	public static int mobType = 0;
	
	public int spawnTime = 1200,spawnFrame = 0;
	
	public static Mob[] mobs = new Mob[30];

	
	
	public GameScreen(JFrame frame) {
		
		frame.addMouseMotionListener(new KeyHandel(this));
		frame.addMouseListener(new KeyHandel(this));
		frame.addKeyListener(new KeyHandel(this));
		
		thread.start();
		frame.requestFocus();
	}


	public void define() {
		room = new Room();
		loadmap = new LoadMap();
		store = new Store();
		try {
			loadmap.loadSave("save/mission"+level);
		} catch (LoadMissionException e) {
			//all mission clear
		}
		
		for(int i =0;i<mobs.length;i++){
			mobs[i] = new Mob();
		}
	}
	
	public static boolean gameoverSoundTrigger;
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
		g2.drawLine(room.block[0][0].x - 1, 0, room.block[0][0].x - 1,
				room.block[room.worldHeight - 1][0].y + room.blockSize - 1);
		
		room.draw(g);// Draw room
		
		for(int i = 0;i<mobs.length;i++){
			if(mobs[i].inGame){
				mobs[i].draw(g);
			}
		}
		
		store.draw(g);// Draw the store
		if(!isWin && health < 1){
			g2.setColor(new Color(240, 20, 20));
			g2.setFont(new Font("Courier New", Font.BOLD, 40));
			g2.drawString("GAME OVER!", myWidth/2-90, myHeight/2);
			g2.drawString("Press Enter", myWidth/2-110, myHeight/2+45);
			Resource.soundTrack.stop();
			if (gameoverSoundTrigger) {
				Resource.gameoverSound.play();
				System.out.println("paly gameover song");
				gameoverSoundTrigger=false;
			}
			
			
		}
		
		if(isWin){
			Resource.shootSound[0].stop();
			Resource.soundTrack.stop();
			g2.clearRect(0, 0, getWidth(), getHeight());
			g2.setFont(new Font("Courier New", Font.BOLD, 35));
			System.out.println("win");
			if(level < maxLevel){
				
				g2.drawString("Congratulations!", myWidth/2-160, myHeight/2);
				g2.setFont(new Font("Courier New", Font.ITALIC, 20));
				g2.drawString("Press Enter to Continune.....", myWidth/2-170, myHeight/2+45);
			}else if(allClear){
				drawAllClear(g);
			}else {
				drawAllClear(g);
			}
			Resource.congratSound.play();
			System.out.println("play congratSound");
		}
	
		
	}
	
	public void drawAllClear(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		g2.drawString("Congratulations!", myWidth/2-160, myHeight/2);
		g2.drawString("You Win This Game", myWidth/2-180, myHeight/2+45);
		g2.setFont(new Font("Courier New", Font.ITALIC, 20));
		g2.drawString("press enter to exit", myWidth/2-170, myHeight/2+85);
	
	}
	
	public static void countKill(){
		killed++;
	}
	
	//bug
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
	
	public  static void hasWon(){
		if(killed == killToWin){
			isWin = true;
			killed = 0;
			coinage = 200;
			health = 20;
		}
	}
	
	@Override
	public void run() {
		while (true) {
			
			if (!isFirst && health > 0 &&!isWin) {
				room.logic();
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
							if(level == maxLevel){
								System.exit(0);
							}else{
								level++;
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
