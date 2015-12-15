package game;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Tower {

	public static int[] amo = {1,2,2,3,3,4,5,2,4,4,6,6,8,10};
	public static ArrayList<Tower> towers = new ArrayList<Tower>();
	
	public int x;
	public int y;
	public int range;
	
	public Tower(int x,int y,int range) {
		this.x =x;
		this.y = y;
		this.range =range;
	}
	
	public static void addTower(Tower i){
		towers.add(i);
	}
	
	public static void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		for(Tower i : towers){
			g2.drawRect(i.x - (i.range / 2)+45, i.y - (i.range / 2), 40 + i.range,
				40 + i.range);
		}
				
		
	}
	
}
