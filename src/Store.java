import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Store {
	public static int shopWidth = 8;
	public static int buttonSize = 40;
	public static int cellSpace = 2;
	public static int iconSize = 20;
	public static int iconTextY = 15;
	
	public Rectangle[] button = new Rectangle[shopWidth];
	public Rectangle buttonHealth;
	public Rectangle buttonCoin;

	public Store() {
		define();
	}

	private void define() {
		for (int i = 0; i < button.length; i++) {
			button[i] = new Rectangle((Screen.myWidth / 2) - ((shopWidth * buttonSize) / 2) + ((buttonSize+cellSpace) * i), 
					(Screen.room.block[Screen.room.worldHeight-1][0].y)+Screen.room.blockSize+(cellSpace*15),
					buttonSize, buttonSize);
			
		}
		buttonHealth = new Rectangle(Screen.room.block[0][0].x - 1,button[0].y,iconSize,iconSize);
		buttonCoin = new Rectangle(Screen.room.block[0][0].x - 1,button[0].y+button[0].height-iconSize+cellSpace,iconSize,iconSize);
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		for (int i = 0; i < button.length; i++) {
			//g2d.setColor(Color.WHITE);
			if(button[i].contains(Screen.mse)){
				g2d.setColor(new Color(255,255,150));
				g2d.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}else{
				g2d.setColor(Color.BLACK);
				g2d.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			}
			if(i<button.length-1)g2d.drawImage(Screen.turret[i],null,button[i].x, button[i].y);
		}
//		g2d.fillRect(buttonHealth.x, buttonHealth.y, buttonHealth.width, buttonHealth.height);
//		g2d.fillRect(buttonCoin.x, buttonCoin.y, buttonCoin.width, buttonCoin.height);
		g2d.drawImage(Screen.heart, null, buttonHealth.x, buttonHealth.y);
		g2d.drawImage(Screen.coin, null, buttonCoin.x, buttonCoin.y);
		g2d.setFont(new Font("Courier New", Font.BOLD, 14));
		g2d.setColor(Color.WHITE);
		g2d.drawString(""+Screen.health, buttonHealth.x+buttonHealth.width+cellSpace, buttonHealth.y+iconTextY);
		g2d.drawString(""+Screen.coinage, buttonCoin.x+buttonCoin.width+cellSpace, buttonCoin.y+iconTextY);
	}
}
