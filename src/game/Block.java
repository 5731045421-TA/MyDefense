package game;
import java.awt.*;

import render.GameScreen;


public class Block extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Rectangle towerSquare;
	public int towerSquareSize = 80;
	public int groundID;
	public int airID;
	public int loseTime = 100,loseFrame = 0;

	public int shotMob = -1;
	public boolean shoting = false;
	
	public final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f);
	public final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

	public Block(int x, int y, int width, int height, int groundId, int airID) {
		setBounds(x, y, width, height);
		towerSquare = new Rectangle(x - (towerSquareSize / 2), y - (towerSquareSize / 2), width + towerSquareSize,
				height + towerSquareSize);
		this.groundID = groundId;
		this.airID = airID;
	}

	public void drawGround(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawRect(x, y, width, height);
		if (groundID == Value.groundGrass)
			g2d.drawImage(Resource.grass, null, x, y);
		else if (groundID == Value.groundRoad)
			g2d.drawImage(Resource.road, null, x, y);
	}
	
	public void drawAir(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		//g2d.drawRect(x, y, width, height);

		if (airID == Value.airBase) {
			g2d.drawImage(Resource.base, null, x, y);
		} else if (airID >= 0) {
//			 g2d.drawRect(towerSquare.x, towerSquare.y, towerSquare.width,
//			 towerSquare.height);
			g2d.drawImage(Resource.turret[airID], null, x, y);
		}
	}

	public void attack() {
		if (shotMob != -1 && towerSquare.intersects(GameScreen.mobs[shotMob])) {
			shoting = true;
			
		} else {
			shoting = false;
		}
		if(shotMob != -1 && GameScreen.mobs[shotMob].isDead){
			shoting = false;
		}
		
		if (!shoting) {
			if (airID >= 0) {
				for (int i = 0; i < GameScreen.mobs.length; i++) {
					if (GameScreen.mobs[i].inGame) {
						
						if (towerSquare.intersects(GameScreen.mobs[i])) {
							shoting = true;
							shotMob = i;
							Resource.shootSound[0].play();
						}
					}
				}
			}
		}
	
		if(shoting){
			if(loseFrame>=loseTime&&airID>=0){
					GameScreen.mobs[shotMob].looseHealth(Tower.amo[airID]);
				if(GameScreen.mobs[shotMob].isDead()){
					Player.getMoney(GameScreen.mobs[shotMob].mobID);
					shoting = false;
					shotMob = -1;
					Player.countKill();
					System.out.println(Player.killed);
					GameScreen.hasWon();
				}
				loseFrame = 0;
			}else{
				loseFrame++;
			}
			Resource.shootSound[0].play();
			
		}
	}
	

	public void fight(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (airID >= 0) {
			//g2d.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			g2d.setComposite(transcluentWhite);
			g2d.setColor(Color.LIGHT_GRAY);
			g2d.fillOval(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
			g2d.setComposite(opaque);
		}
		
		if (shoting) {
			g2d.setStroke(new BasicStroke(3));
			g2d.setColor(Color.YELLOW);
			g2d.drawLine(x + (width / 2), y + (height / 2), GameScreen.mobs[shotMob].x + (GameScreen.mobs[shotMob].width / 2),
					GameScreen.mobs[shotMob].y + (GameScreen.mobs[shotMob].height / 2));
			g2d.setStroke(new BasicStroke(1));
		}

	}
}
