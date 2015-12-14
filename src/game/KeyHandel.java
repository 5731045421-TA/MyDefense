package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import exception.SellTowerException;
import render.GameScreen;



public class KeyHandel implements MouseMotionListener, MouseListener,KeyListener {
	private GameScreen screen;

	public KeyHandel(GameScreen screen) {
		this.screen = screen;
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {
		try {
			GameScreen.store.click(e.getButton());
		} catch (SellTowerException e1) {
			for (int y = 0; y < GameScreen.room.block.length; y++) {
				for (int x = 0; x < GameScreen.room.block[0].length; x++) {
					GameScreen.room.block[y][x].shoting = false;
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {

		GameScreen.mse = new Point(e.getX(), e.getY()-32);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == 10){//enter
			if(!GameScreen.isWin){
				screen.pause();
			}
			System.out.println("enter");
			GameScreen.pressEnter = true;
			screen.nextMission();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		GameScreen.pressEnter = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
