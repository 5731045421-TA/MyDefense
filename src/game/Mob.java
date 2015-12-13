package game;
import java.awt.*;

public class Mob extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int xC,yC;
	public int health;
	public int healthSpace = 3,healthHeight = 6;
	public int mobSize = 40;
	public static int mobHealth = 0;
	public int mobWalk = 0;
	public int healthDivider = 1;
	public int up = 0,down = 1,right = 2,left = 3;
	public int direction = right;
	public int mobID = Value.mobAir;
	public boolean inGame = false;
	public boolean isDead = false;
	public boolean isUpward = false;
	public boolean isDownward = false;
	public boolean isRightward = false;
	public boolean isLeftward = false;

	
	public Mob(){
		
	}
	int i = 0;
	public void spawnMob(int mobID,int i){
		for(int y = 0;y<Screen.room.block.length;y++){
			if(Screen.room.block[y][0].groundID == Value.groundRoad){
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}
		this.health = mobHealth;
		this.healthDivider = mobID+1;
		if(i != 0&& i>9){
			this.health+=40;
			this.healthDivider+=1;
		}
		this.mobID = mobID;
		this.inGame = true;
		this.isDead = false;
		
	}
	
	public int walkFrame = 0,walkSpeed = 10;
	public void physic(){
		if(walkFrame>=walkSpeed){
			if(direction == right){
				x++;
			}else if(direction == up){
				y--;
			}else if(direction == down){
				y++;
			}else if(direction == left){
				x--;
			}
			
			mobWalk++;
			
			if(mobWalk == Screen.room.blockSize){
				if(direction == right){
					xC++;
					isRightward = true;
				}else if(direction == up){
					yC--;
					isUpward = true;
				}else if(direction == down){
					yC++;
					isDownward = true;
				}else if (direction == left){
					xC--;
					isLeftward = true;
				}
				if(!isUpward){
					try {
						if(Screen.room.block[yC+1][xC].groundID == Value.groundRoad){
							direction = down;
						}
					} catch (Exception e) {}
				}
				if(!isDownward){
					try {
						if(Screen.room.block[yC-1][xC].groundID == Value.groundRoad){
							direction = up;
						}
					} catch (Exception e) {}
				}
				if(!isLeftward){
					try {
						if(Screen.room.block[yC][xC+1].groundID == Value.groundRoad){
							direction = right;
						}
					} catch (Exception e) {}
				}
				if(!isRightward){
					try {
						if(Screen.room.block[yC][xC-1].groundID == Value.groundRoad){
							direction = left;
						}
					} catch (Exception e) {}
				}
				
				if(Screen.room.block[yC][xC].airID == Value.airBase){
					deleteMob();
					loseHealth();
				}
				
				isUpward = false;
				isDownward = false;
				isLeftward = false;
				isRightward = false;
				mobWalk = 0;
			}
			
			walkFrame = 0;
		}else{
			walkFrame++;
		}
	}
	//player health
	private void loseHealth() {
		Screen.health--;
		Screen.countKill();
		
	}

	public void deleteMob() {
		inGame = false;
		isDead = true;
		direction = right;
		mobWalk = 0;
		
	}
	
	//Mob health
	public void looseHealth(int amo){
		health-=amo;
		checkDeath();
	}
	
	public void checkDeath(){
		if(health == 0){
			deleteMob();
		}
	}
	
	public boolean isDead(){
		if(health == 0){
			return true;
		}else{
			return false;
		}
	}
	
	int counter = 0;
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(inGame){
			g2d.drawImage(Resource.animationCreep3[mobID][counter++ % 4], null, x	,y);
			//health bar.
			g2d.setColor(Color.RED);
			g2d.fillRect(x , y - (healthSpace + healthHeight), width, healthHeight);
			
			g2d.setColor(Color.GREEN);
			g2d.fillRect(x , y - (healthSpace + healthHeight), health/(healthDivider), healthHeight);
			
			g2d.setColor(Color.BLACK);
			g2d.drawRect(x , y - (healthSpace + healthHeight), width, healthHeight - 1);
		}
	}
}
