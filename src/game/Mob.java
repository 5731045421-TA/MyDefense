package game;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import render.GameScreen;

public class Mob extends Rectangle{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int xC,yC;
	public int health;
	public int healthSpace = 3,healthHeight = 6;
	public int mobSize = 40;
	public static int mobHealth;
	public int mobWalk = 0;
	public int healthDivider = 1;
	public int up = 0,down = 1,right = 2,left = 3;
	public int direction = right;
	public int mobID;
	public boolean inGame = false;
	public boolean isDead = false;
	public boolean isUpward = false;
	public boolean isDownward = false;
	public boolean isRightward = false;
	public boolean isLeftward = false;
	public int type = 1;
	
	public Mob(){
		
	}
	int i = 0;
	public void spawnMob(int mobID,int i){
		for(int y = 0;y<GameScreen.map.block.length;y++){
			if(GameScreen.map.block[y][0].groundID == Value.groundRoad){
				setBounds(GameScreen.map.block[y][0].x, GameScreen.map.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}
		this.health = mobHealth;
		this.healthDivider = mobID+1;
		if(i != 0&& i>9){
			this.health+=40;
			this.healthDivider+=1;
			this.type = 2;
		}
		if(i > 19){
			this.health+=40;
			this.healthDivider+=1;
			this.type = 3;
		}
		if(i >= 29){
			this.health+=200;
			this.healthDivider+=5;
			this.type = 4;
		}
		this.mobID = mobID;
		this.inGame = true;
		this.isDead = false;
	}
	
	public int walkFrame = 0,walkSpeed = 10;
	
	public void move(){
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
			
			if(mobWalk == GameScreen.map.blockSize){
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
						if(GameScreen.map.block[yC+1][xC].groundID == Value.groundRoad){
							direction = down;
						}
					} catch (Exception e) {}
				}
				if(!isDownward){
					try {
						if(GameScreen.map.block[yC-1][xC].groundID == Value.groundRoad){
							direction = up;
						}
					} catch (Exception e) {}
				}
				if(!isLeftward){
					try {
						if(GameScreen.map.block[yC][xC+1].groundID == Value.groundRoad){
							direction = right;
						}
					} catch (Exception e) {}
				}
				if(!isRightward){
					try {
						if(GameScreen.map.block[yC][xC-1].groundID == Value.groundRoad){
							direction = left;
						}
					} catch (Exception e) {}
				}
				
				if(GameScreen.map.block[yC][xC].airID == Value.airBase){
					deleteMob();
					Player.loseHealth();
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
	

	public void deleteMob() {
		inGame = false;
		isDead = true;
		
		
	}
	
	//Mob health
	public void looseHealth(int amo){
		health-=amo;
		checkDeath();
	}
	
	public void checkDeath(){
		if(health <= 0){
			deleteMob();
			Resource.deathSound.play();//play dead sound
		}
	}
	
	
	
	public boolean isDead(){
		if(health <= 0){
			return true;
		}else{
			return false;
		}
	}
	
	int counter = 0,change = 50,ani = 0;
	AffineTransform at=new AffineTransform();
	AffineTransformOp aop;
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		
		//create rotater
		
		int numquadrants=0;
		if(direction==up)numquadrants=3;
		else if(direction==down)numquadrants=1;
		else if(direction==left)numquadrants=2;
		at.quadrantRotate(numquadrants,20,20);
		aop=new AffineTransformOp(at, AffineTransformOp.TYPE_BICUBIC);
		
		if(inGame){
			if(counter >= change){
				ani++;
				if(ani == 4)ani = 0;
				
				counter = 0;
			}else {
				counter++;
			}

			if(type == 1){				
				g2d.drawImage(Resource.animationCreep1[mobID][ani], aop, x,y);
			}else if(type == 2){
				g2d.drawImage(Resource.animationCreep2[mobID][ani], aop, x,y);
			}else if(type == 3){
				g2d.drawImage(Resource.animationCreep3[mobID][ani], aop, x,y);
			}else if(type > 3){
				g2d.drawImage(Resource.boss, null, x,y);
			}
			at.setToIdentity();

			
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
