package game;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;



public class KeyHandel implements MouseMotionListener, MouseListener,KeyListener {
	private Screen screen;

	public KeyHandel(Screen screen) {
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
		Screen.store.click(e.getButton());
	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		Screen.mse = new Point((e.getX()) + ((Frame.size.width - Screen.myWidth) / 2),
				(e.getY()) + ((Frame.size.height - Screen.myHeight) / 2));
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		Screen.mse = new Point((e.getX()) - ((Frame.size.width - Screen.myWidth) / 2),
//				(e.getY()) - ((Frame.size.height - Screen.myHeight) / 2));
	
		Screen.mse = new Point(e.getX(), e.getY()-32);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == e.VK_SPACE){
			Screen.startEnter = true;
		}
		if(e.getKeyCode() == e.VK_ENTER){
			Screen.pressEnter = true;
			screen.nextMission();
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Screen.pressEnter = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
