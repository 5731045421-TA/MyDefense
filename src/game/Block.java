package game;
import java.awt.*;

import ui.GameScreen;


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
							
						}
					}
				}
			}
		}
	
		if(shoting){
			if(loseFrame>=loseTime&&airID>=0){
					GameScreen.mobs[shotMob].looseHealth(Tower.amo[airID%7]);
				if(GameScreen.mobs[shotMob].isDead()){
					getMoney(GameScreen.mobs[shotMob].mobID);
					shoting = false;
					shotMob = -1;
					GameScreen.countKill();
					System.out.println(GameScreen.killed);
					GameScreen.hasWon();
				}
				loseFrame = 0;
			}else{
				loseFrame++;
			}
			
			
		}
	}
	
	public void getMoney(int mobID){
		GameScreen.coinage += Value.deathReward[mobID] ;
			//System.out.println("get Money"+" "+Screen.mobs[shotMob].mobID);
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
			g.setColor(Color.YELLOW);
			g.drawLine(x + (width / 2), y + (height / 2), GameScreen.mobs[shotMob].x + (GameScreen.mobs[shotMob].width / 2),
					GameScreen.mobs[shotMob].y + (GameScreen.mobs[shotMob].height / 2));
		}

	}
}
