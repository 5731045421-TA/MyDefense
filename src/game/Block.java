package game;
import java.awt.*;


public class Block extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Rectangle towerSquare;
	public int towerSquareSize = 100;
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
		//g2d.drawRect(x, y, width, height);
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
			// g2d.drawRect(towerSquare.x, towerSquare.y, towerSquare.width,
			// towerSquare.height);
			g2d.drawImage(Resource.turret[airID], null, x, y);
		}
	}

	public void physics() {
		if (shotMob != -1 && towerSquare.intersects(Screen.mobs[shotMob])) {
			shoting = true;
			
		} else {
			shoting = false;
		}
		if(shotMob != -1 && Screen.mobs[shotMob].isDead){
			shoting = false;
		}
		
		if (!shoting) {
			if (airID >= 0) {
				for (int i = 0; i < Screen.mobs.length; i++) {
					if (Screen.mobs[i].inGame) {
						
						if (towerSquare.intersects(Screen.mobs[i])) {
							shoting = true;
							shotMob = i;
							
						}
					}
				}
			}
		}
		//bug
		if(shoting){
			if(loseFrame>=loseTime){
				Screen.mobs[shotMob].looseHealth(1);
				if(Screen.mobs[shotMob].isDead()){
					getMoney(Screen.mobs[shotMob].mobID);
					shoting = false;
					shotMob = -1;
					Screen.countKill();
					System.out.println(Screen.killed);
					Screen.hasWon();
				}
				loseFrame = 0;
			}else{
				loseFrame++;
			}
			
			
		}
	}
	
	public void getMoney(int mobID){
			Screen.coinage += Value.deathReward[mobID] ;
			//System.out.println("get Money"+" "+Screen.mobs[shotMob].mobID);
	}

	public void fight(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		if (Screen.isDebug) {
			if (airID >= 0) {
				//g2d.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
				g2d.setComposite(transcluentWhite);
				g2d.setColor(Color.LIGHT_GRAY);
				g2d.fillOval(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
				
				g2d.setComposite(opaque);
			}
		}
		if (shoting) {
			g.setColor(Color.YELLOW);
			g.drawLine(x + (width / 2), y + (height / 2), Screen.mobs[shotMob].x + (Screen.mobs[shotMob].width / 2),
					Screen.mobs[shotMob].y + (Screen.mobs[shotMob].height / 2));
		}

	}
}
