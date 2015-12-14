package game;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Resource {
	
	public static BufferedImage grass= null;
	public static BufferedImage road= null;
	public static BufferedImage base= null;
	public static BufferedImage heart = null;
	public static BufferedImage coin = null;
	public static BufferedImage block = null;
	public static BufferedImage bin = null;
	public static BufferedImage upgrade = null;
	public static BufferedImage sell = null;
	public static BufferedImage[] turret = new BufferedImage[14];
	public static BufferedImage[][] animationCreep1=null;
	public static BufferedImage[][] animationCreep2=null;
	public static BufferedImage[][] animationCreep3=null;
	
	public static AudioClip soundTrack;
	public static AudioClip deathSound;
	public static AudioClip shootSound[]=new AudioClip[2];
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
		
		try{
			ClassLoader loader=Resource.class.getClassLoader();
			soundTrack=Applet.newAudioClip(loader.getResource("soundRes/soundTrack.wav"));
			coinSound=Applet.newAudioClip(loader.getResource("soundRes/coinSound.wav"));
			deathSound=Applet.newAudioClip(loader.getResource("soundRes/deathSound2.mp3"));
			
			congratSound=Applet.newAudioClip(loader.getResource("soundRes/congratSound.mp3"));
			gameoverSound=Applet.newAudioClip(loader.getResource("soundRes/gameoverSound.mp3"));
			startScreenSound=Applet.newAudioClip(loader.getResource("soundRes/startScreenSound.mp3"));
			for(int i=1;i<=shootSound.length;i++){
				shootSound[i-1]=Applet.newAudioClip(loader.getResource("soundRes/shootSound"+i+".wav"));
			}
			
			
		}catch(Exception e){
			soundTrack=null;
			coinSound=null;
			System.out.println("sound not found");
			e.printStackTrace();
		}
	}

}
