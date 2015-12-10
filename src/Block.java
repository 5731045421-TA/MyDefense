import java.awt.*;

public class Block extends Rectangle{
	public int groundID;
	public int airID;
	public Block(int x,int y ,int width,int height,int groundId,int airID){
		setBounds(x,y,width,height);
		this.groundID =groundId;
		this.airID = airID;
	}
	public void draw (Graphics g){
		Graphics2D g2d = (Graphics2D)g;
		g2d.drawRect(x, y, width, height);
		if(groundID == Value.groundGrass)g2d.drawImage(Screen.grass, null, x, y);
		else if(groundID == Value.groundRoad)g2d.drawImage(Screen.road, null, x, y);
		else if(groundID == Value.groundBase)g2d.drawImage(Screen.base, null, x, y);
		if(airID != Value.airAir){
			g2d.drawImage(Screen.road, null, x, y);
		}
	}
}
