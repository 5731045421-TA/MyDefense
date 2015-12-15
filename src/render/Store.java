package render;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import exception.SellTowerException;
import game.Player;
import game.Resource;
import game.Value;

public class Store {
	public static int shopWidth = 10;
	public static int buttonSize = 40;
	public static int cellSpace = 2;
	public static int iconSize = 20;
	public static int iconTextY = 15;
	public static int heldID = -1;
	public static int[] buttonID = { 0, 1, 2, 3, 4, 5, 6, -2, -4, -5 };
	public static int[] buttonPrice = { 20, 30, 30, 50, 50, 100, 200, 0, 0, 0 };
	public static int[] upgradePrice = { 20, 30, 30, 50, 50, 100, 200 };

	public Rectangle[] button = new Rectangle[shopWidth];
	public Rectangle buttonHealth;
	public Rectangle buttonCoin;
	public Rectangle buttonMob;

	protected static final AlphaComposite transcluentWhite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f);
	protected static final AlphaComposite opaque = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1);

	public boolean holdsItem = false;

	public Store() {
		define();
	}

	// init the point of sell button
	private void define() {
		for (int i = 0; i < button.length; i++) {
			button[i] = new Rectangle(
					(GameScreen.myWidth / 2) - ((shopWidth * buttonSize) / 2) + ((buttonSize + cellSpace) * i),
					(GameScreen.map.block[GameScreen.map.worldHeight - 1][0].y) + GameScreen.map.blockSize
							+ (cellSpace * 15),
					buttonSize, buttonSize);

		}
		buttonHealth = new Rectangle(GameScreen.map.block[0][0].x - 1, button[0].y, iconSize, iconSize);
		buttonCoin = new Rectangle(GameScreen.map.block[0][0].x - 1,
				button[0].y + button[0].height - iconSize + cellSpace, iconSize, iconSize);
		buttonMob = new Rectangle(GameScreen.map.block[0][0].x - 1,
				button[0].y + (button[0].height * 2) + -(iconSize * 2) + cellSpace, iconSize, iconSize);
	}

	public void click(int mouseButton) throws SellTowerException {
		if (mouseButton == 1) { // left mouse button
			for (int i = 0; i < button.length; i++) {
				if (button[i].contains(GameScreen.mse)) {
					heldID = buttonID[i];
					holdsItem = true;
					if (heldID == Value.airTrashCan) {// Delete item
						heldID = Value.airAir;
						holdsItem = false;
					} else if (heldID == Value.airSell || heldID == Value.airUpGrade) {

					}
				}
			}
			// buy tower
			if (holdsItem && heldID > -4) {
				if (Player.coinage >= buttonPrice[heldID]) {
					for (int y = 0; y < GameScreen.map.block.length; y++) {
						for (int x = 0; x < GameScreen.map.block[0].length; x++) {
							if (GameScreen.map.block[y][x].contains(GameScreen.mse)) {
								if (GameScreen.map.block[y][x].groundID != Value.groundRoad
										&& GameScreen.map.block[y][x].airID == Value.airAir) {
									GameScreen.map.block[y][x].airID = heldID;
									GameScreen.map.block[y][x].towerSquare = new Rectangle(
											GameScreen.map.block[y][x].x - (Value.towerRange[heldID] / 2),
											GameScreen.map.block[y][x].y - (Value.towerRange[heldID] / 2),
											GameScreen.map.block[y][x].width + Value.towerRange[heldID],
											GameScreen.map.block[y][x].height + Value.towerRange[heldID]);
									Player.coinage -= buttonPrice[heldID];
								}
							}
						}
					}
					Resource.coinSound.play();// coinSonund
				}
			}
			try { // sell tower
				if (holdsItem && heldID == Value.airSell) {
					for (int y = 0; y < GameScreen.map.block.length; y++) {
						for (int x = 0; x < GameScreen.map.block[0].length; x++) {
							if (GameScreen.map.block[y][x].contains(GameScreen.mse)) {
								if (GameScreen.map.block[y][x].airID >= 0) {

									Player.coinage += buttonPrice[GameScreen.map.block[y][x].airID % 7] / 2;
									GameScreen.map.block[y][x].airID = -1;
									GameScreen.map.block[y][x].shoting = false;
									GameScreen.map.block[y][x].inGame = false;
								}
							}
						}
					}
					Resource.coinSound.play();
				}

			} catch (Exception e) {
				throw new SellTowerException(e);
			}

		}//upgrade tower
		if (holdsItem && heldID == Value.airUpGrade) {
			for (int y = 0; y < GameScreen.map.block.length; y++) {
				for (int x = 0; x < GameScreen.map.block[0].length; x++) {
					if (GameScreen.map.block[y][x].contains(GameScreen.mse)) {
						if (GameScreen.map.block[y][x].airID >= 0 && GameScreen.map.block[y][x].airID < 7
								&& Player.coinage >= upgradePrice[GameScreen.map.block[y][x].airID]) {
							Player.coinage -= upgradePrice[GameScreen.map.block[y][x].airID];
							GameScreen.map.block[y][x].airID += 7;
							int a = GameScreen.map.block[y][x].airID;
							GameScreen.map.block[y][x].towerSquare = new Rectangle(
									GameScreen.map.block[y][x].x - (Value.towerRange[a] / 2),
									GameScreen.map.block[y][x].y - (Value.towerRange[a] / 2),
									GameScreen.map.block[y][x].width + Value.towerRange[a],
									GameScreen.map.block[y][x].height + Value.towerRange[a]);

						}
					}
				}
			}
			Resource.coinSound.play();
		}
	}

	public void draw(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		for (int i = 0; i < button.length; i++) {
			if (button[i].contains(GameScreen.mse) && i != 7) {
				g2d.setColor(new Color(255, 200, 120));
				g2d.fillRect(button[i].x, button[i].y, button[i].width, button[i].height);
			} else {
				if (i == 7)
					g2d.drawImage(Resource.bin, null, button[i].x, button[i].y);
				else
					g2d.drawImage(Resource.block, null, button[i].x, button[i].y);
			}

			g2d.setColor(Color.WHITE);
			g2d.setFont(new Font("Courier New", Font.BOLD, 14));
			if (buttonPrice[i] > 0)
				g2d.drawString("$" + buttonPrice[i], button[i].x + 5, button[i].y - 4); // draw
																						// price

			if (i < button.length - 3)
				g2d.drawImage(Resource.turret[i], null, button[i].x, button[i].y); // draw
																					// turret
			if (i == 8) {
				g2d.drawImage(Resource.sell, null, button[i].x, button[i].y);
			}
			if (i == 9) {
				g2d.drawImage(Resource.upgrade, null, button[i].x, button[i].y);
			}
		}

		g2d.drawImage(Resource.heart, null, buttonHealth.x, buttonHealth.y);
		g2d.drawImage(Resource.coin, null, buttonCoin.x, buttonCoin.y);
		g2d.drawImage(Resource.miniMob, null, buttonMob.x, buttonMob.y + 2);
		g2d.setFont(new Font("Courier New", Font.BOLD, 15));
		g2d.setColor(Color.WHITE);
		// draw Stage number
		if (Player.level == Player.maxLevel) {
			g2d.drawString("Final Stage", buttonHealth.x - 25 + buttonHealth.width + cellSpace,
					buttonHealth.y + iconTextY - 25);
		} else {
			g2d.drawString("Stage " + Player.level, buttonHealth.x - 25 + buttonHealth.width + cellSpace,
					buttonHealth.y + iconTextY - 25);
		}
		// drawString health and coin
		g2d.setFont(new Font("Courier New", Font.BOLD, 14));
		g2d.drawString("" + Player.health, buttonHealth.x + buttonHealth.width + cellSpace, buttonHealth.y + iconTextY);
		g2d.drawString("" + Player.coinage, buttonCoin.x + buttonCoin.width + cellSpace, buttonCoin.y + iconTextY);
		g2d.drawString("" + Player.killed + " / " + Player.killToWin, buttonMob.x + +buttonMob.width + cellSpace,
				buttonMob.y + iconTextY);

		if (holdsItem) {
			// draw sample turret
			if (heldID >= 0) {

				g2d.drawImage(Resource.turret[heldID], null, GameScreen.mse.x - 20, GameScreen.mse.y - 20);

			} else if (heldID == Value.airSell) {
				g2d.drawImage(Resource.sell, null, GameScreen.mse.x - 20, GameScreen.mse.y - 20);
			} else if (heldID == Value.airUpGrade) {
				g2d.drawImage(Resource.upgrade, null, GameScreen.mse.x - 20, GameScreen.mse.y - 20);
			}

		}
	}
}
