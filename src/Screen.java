import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Screen extends JPanel implements Runnable {
	public Thread thread = new Thread(this);
	public static BufferedImage grass;
	public static BufferedImage road;
	public static BufferedImage base;
	public static BufferedImage heart = null;
	public static BufferedImage coin = null;
	public static BufferedImage[] turret = new BufferedImage[7];
	public static BufferedImage[][] animationCreep1=null;
	public static BufferedImage[][] animationCreep2=null;
	public static BufferedImage[][] animationCreep3=null;

	public static boolean isFirst = true;

	public static Point mse = new Point(0, 0);

	public static Room room;
	public static Save save;
	public static Store store;
	
	public static int myWidth, myHeight;
	public static int coinage = 10,health = 100;
	public int spawnTime = 100,spawnFrame = 2400;
	
	public static Mob[] mobs = new Mob[100];

	static {
		try {
			grass = ImageIO.read(new File("res/grass.png"));
			road = ImageIO.read(new File("res/road.png"));
			base = ImageIO.read(new File("res/base.png"));
			heart = ImageIO.read(new File("res/heart.png"));
			coin = ImageIO.read(new File("res/coin.png"));
			turret[0] = ImageIO.read(new File("res/tower/turret-1-1.png"));
			turret[1] = ImageIO.read(new File("res/tower/turret-2-1.png"));
			turret[2] = ImageIO.read(new File("res/tower/turret-3-1.png"));
			turret[3] = ImageIO.read(new File("res/tower/turret-4-1.png"));
			turret[4] = ImageIO.read(new File("res/tower/turret-5-1.png"));
			turret[5] = ImageIO.read(new File("res/tower/turret-6-1.png"));
			turret[6] = ImageIO.read(new File("res/tower/turret-7-1.png"));
			
			BufferedImage creep1=ImageIO.read(new File("res/creep1.png"));
			BufferedImage creep2=ImageIO.read(new File("res/creep2.png"));
			BufferedImage creep3=ImageIO.read(new File("res/creep3.png"));
			
			animationCreep1 =new BufferedImage[4][4];
			animationCreep2 =new BufferedImage[4][4];
			animationCreep3 =new BufferedImage[4][4];
			
			for(int i=0;i<4;i++){
				for (int j = 0; j < 4; j++) {
					animationCreep1[i][j]=creep1.getSubimage(j*50, i*50, 50, 50);
					animationCreep2[i][j]=creep2.getSubimage(j*50, i*50, 50, 50);
					animationCreep3[i][j]=creep3.getSubimage(j*50, i*50, 50, 50);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			grass = null;
			road = null;
			base = null;
		}
	}

	public Screen(Frame frame) {
		frame.addMouseMotionListener(new KeyHandel());
		frame.addMouseListener(new KeyHandel());
		
		thread.start();
	}

	public void define() {
		room = new Room();
		save = new Save();
		store = new Store();
		save.loadSave(new File("save/mission1"));
		
		for(int i =0;i<mobs.length;i++){
			mobs[i] = new Mob();
		}
	}

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
	}
	int is =0;
	public void mobSpawner(){
		if(spawnFrame >= spawnTime){
			for(int i =0;i<mobs.length;i++){
				if(!mobs[i].inGame){
					mobs[i].spawnMob(0);
					break;
				}
			}
			spawnFrame = 0;
			System.out.println("spaw"+is++);
		}else{
			spawnFrame++;
		}
	}
	
	@Override
	public void run() {
		while (true) {
			if (!isFirst) {
				room.physic();
				mobSpawner();
				for(int i = 0;i<mobs.length;i++){
					if(mobs[i].inGame){
						mobs[i].physic();
					}
				}
			}

			repaint();

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
