package game;

public class InputUtility {

	private static int mouseX,mouseY;
	private static boolean mouseLeftDown,mouseRightDown;
	private static boolean mouseLeftTriggered,mouseRightTriggered;
	private static boolean[] keyPressed = new boolean[256];
	private static boolean[] keyTriggered = new boolean[256];

	
	public static boolean[] getKeyPressed() {
		return keyPressed;
	}
	public static void setKeyPressed(boolean[] keyPressed) {
		InputUtility.keyPressed = keyPressed;
	}
	public static int getMouseX() {
		return mouseX; 
	}
	public static void setMouseX(int mouseX) {
		InputUtility.mouseX = mouseX;
	}
	public static int getMouseY() {
		return mouseY;
	}
	public static void setMouseY(int mouseY) {
		InputUtility.mouseY = mouseY;
	}
	public static boolean isMouseLeftDown() {
		return mouseLeftDown;
	}
	public static void setMouseLeftDown(boolean mouseLeftDown) {
		InputUtility.mouseLeftDown = mouseLeftDown;
	}
	public static boolean isMouseRightDown() {
		return mouseRightDown;
	}
	public static void setMouseRightDown(boolean mouseRightDown) {
		InputUtility.mouseRightDown = mouseRightDown;
	}
	public static boolean isMouseLeftTriggered() {
		return mouseLeftTriggered;
	}
	public static void setMouseLeftTriggered(boolean mouseLeftTriggered) {
		InputUtility.mouseLeftTriggered = mouseLeftTriggered;
	}
	public static boolean isMouseRightTriggered() {
		return mouseRightTriggered;
	}
	public static void setMouseRightTriggered(boolean mouseRightTriggered) {
		InputUtility.mouseRightTriggered = mouseRightTriggered;
	}
	public static boolean getKeyTriggered(int key){
		return InputUtility.keyTriggered[key];
	}
	public static void setKeyTriggered(int key) {
		InputUtility.keyTriggered[key]=true;
	}

}

