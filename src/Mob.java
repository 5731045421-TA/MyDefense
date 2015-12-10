import java.awt.*;

public class Mob extends Rectangle{
	public int xC,yC;
	public int mobSize = 50;
	public int mobWalk = 0;
	public int up = 0,down = 1,right = 2,left = 3;
	public int direction = right;
	public int mobID = Value.mobAir;
	public boolean inGame = false;
	public boolean isUpward = false;
	public boolean isDownward = false;
	public boolean isRightward = false;
	public boolean isLeftward = false;
	
	public Mob(){
		
	}
	public void spawnMob(int mobID){
		for(int y = 0;y<Screen.room.block.length;y++){
			if(Screen.room.block[y][0].groundID == Value.groundRoad){
				setBounds(Screen.room.block[y][0].x, Screen.room.block[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}
		
		this.mobID = mobID;
		inGame = true;
	}
	public int walkFrame = 0,walkSpeed = 5;
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
	
	public void draw(Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		if(inGame){
			g2d.drawImage(Screen.animationCreep1[0][0], null, x	,y);
		}
	}
}
