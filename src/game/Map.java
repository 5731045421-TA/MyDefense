package game;
import java.awt.Graphics;

import render.GameScreen;

public class Map {
	public int worldWidth = 15;
	public int worldHeight = 10;
	public int blockSize = 40;
	public Block[][] block;

	public Map() {
		define();
	}

	public void define() {
		block = new Block[worldHeight][worldWidth];

		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x] = new Block((GameScreen.myWidth / 2) - ((worldWidth * blockSize / 2)) + (x * blockSize),
						y * blockSize, blockSize, blockSize, Value.groundGrass, Value.airAir);
			}
		}

	}

	public void draw(Graphics g) {
		//draw Grass and road
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
					block[y][x].drawGround(g);
			}
		}
		//draw area and lazer
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
				block[y][x].fight(g);
			}
		}
		//draw tower and base
		for (int y = 0; y < block.length; y++) {
			for (int x = 0; x < block[0].length; x++) {
					block[y][x].drawAir(g);
			}
		}
	}

	public void logic() {
		for(int y = 0;y<block.length;y++){
			for(int x = 0;x<block[0].length;x++){
				block[y][x].attack();
			}
		}
	}
}
