package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class Resource {
	
	public static BufferedImage grass= null;
	public static BufferedImage road= null;
	public static BufferedImage base= null;
	public static BufferedImage heart = null;
	public static BufferedImage coin = null;
	public static BufferedImage miniMob = null;
	public static BufferedImage block = null;
	public static BufferedImage bin = null;
	public static BufferedImage upgrade = null;
	public static BufferedImage sell = null;
	public static BufferedImage[] turret = new BufferedImage[14];
	public static BufferedImage[][] animationCreep1=null;
	public static BufferedImage[][] animationCreep2=null;
	public static BufferedImage[][] animationCreep3=null;
	public static BufferedImage boss = null;
	
	public static AudioClip soundTrack;
	public static AudioClip deathSound;
	public static AudioClip shootSound;
	public static AudioClip coinSound;
	public static AudioClip congratSound;
	public static AudioClip gameoverSound;
	public static AudioClip startScreenSound;
	static {
		try {
			ClassLoader load = Resource.class.getClassLoader();
			grass = ImageIO.read(load.getResource("res/grass.png"));
			road = ImageIO.read(load.getResource("res/road.png"));
			base = ImageIO.read(load.getResource("res/base.png"));
			heart = ImageIO.read(load.getResource("res/heart.png"));
			coin = ImageIO.read(load.getResource("res/coin.png"));
			miniMob = ImageIO.read(load.getResource("res/miniMob.png"));
			block = ImageIO.read(load.getResource("res/block.png"));
			bin = ImageIO.read(load.getResource("res/bin.png"));
			upgrade = ImageIO.read(load.getResource("res/upgrade.png"));
			sell = ImageIO.read(load.getResource("res/sells.png"));
			turret[0] = ImageIO.read(load.getResource("res/tower/turret-1-1.png"));
			turret[1] = ImageIO.read(load.getResource("res/tower/turret-2-1.png"));
			turret[2] = ImageIO.read(load.getResource("res/tower/turret-3-1.png"));
			turret[3] = ImageIO.read(load.getResource("res/tower/turret-4-1.png"));
			turret[4] = ImageIO.read(load.getResource("res/tower/turret-5-1.png"));
			turret[5] = ImageIO.read(load.getResource("res/tower/turret-6-1.png"));
			turret[6] = ImageIO.read(load.getResource("res/tower/turret-7-1.png"));
			turret[7] = ImageIO.read(load.getResource("res/tower/turret-1-3.png"));
			turret[8] = ImageIO.read(load.getResource("res/tower/turret-2-3.png"));
			turret[9] = ImageIO.read(load.getResource("res/tower/turret-3-3.png"));
			turret[10] = ImageIO.read(load.getResource("res/tower/turret-4-3.png"));
			turret[11] = ImageIO.read(load.getResource("res/tower/turret-5-3.png"));
			turret[12] = ImageIO.read(load.getResource("res/tower/turret-6-3.png"));
			turret[13] = ImageIO.read(load.getResource("res/tower/turret-7-3.png"));
			
			BufferedImage creep1=ImageIO.read(load.getResource("res/creep1.png"));
			BufferedImage creep2=ImageIO.read(load.getResource("res/creep2.png"));
			BufferedImage creep3=ImageIO.read(load.getResource("res/creep3.png"));
			
			animationCreep1 =new BufferedImage[4][4];
			animationCreep2 =new BufferedImage[4][4];
			animationCreep3 =new BufferedImage[4][4];
			boss = ImageIO.read(load.getResource("res/boss.png"));
			
			for(int i=0;i<4;i++){
				for (int j = 0; j < 4; j++) {
					animationCreep1[i][j]=creep1.getSubimage(j*40, i*40, 40, 40);
					animationCreep2[i][j]=creep2.getSubimage(j*40, i*40, 40, 40);
					animationCreep3[i][j]=creep3.getSubimage(j*40, i*40, 40, 40);
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try{//sound
			ClassLoader loader=Resource.class.getClassLoader();
			soundTrack=Applet.newAudioClip(loader.getResource("soundRes/soundTrack3.wav"));
			coinSound=Applet.newAudioClip(loader.getResource("soundRes/coinSound.wav"));
			deathSound=Applet.newAudioClip(loader.getResource("soundRes/deathSound2.wav"));
			
			congratSound=Applet.newAudioClip(loader.getResource("soundRes/congratSound.wav"));
			gameoverSound=Applet.newAudioClip(loader.getResource("soundRes/gameoverSound.wav"));
			startScreenSound=Applet.newAudioClip(loader.getResource("soundRes/startScreenSound.wav"));

			shootSound=Applet.newAudioClip(loader.getResource("soundRes/shortShootSound.wav"));
			
		}catch(Exception e){
			soundTrack=null;
			coinSound=null;
			JOptionPane option = new JOptionPane();
			option.showMessageDialog(null,"Sound not found","Error",JOptionPane.ERROR_MESSAGE );
			System.exit(0);
			e.printStackTrace();
		}
	}

}
