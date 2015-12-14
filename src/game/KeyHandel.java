package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JComponent;

import ui.GameScreen;



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
		GameScreen.store.click(e.getButton());
		if(e.getButton()==1)InputUtility.setMouseLeftDown(true);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		InputUtility.setMouseLeftDown(false);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.myWidth) / 2),
//				(e.getY()) - ((Frame.size.height - Screen.myHeight) / 2));
		InputUtility.setMouseX(e.getX());
		InputUtility.setMouseX(e.getY());
		GameScreen.mse = new Point(e.getX(), e.getY()-32);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		InputUtility.setKeyTriggered(e.getKeyCode());
		if(e.getKeyCode() == 32){//spacebar
			GameScreen.startEnter = true;
		}
		if(e.getKeyCode() == 10){//enter
			GameScreen.pressEnter = true;
			screen.nextMission();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		GameScreen.pressEnter = false;
		//GameScreen.startEnter = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
