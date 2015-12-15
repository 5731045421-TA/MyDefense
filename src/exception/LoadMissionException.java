package exception;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class LoadMissionException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LoadMissionException(Exception e) {
		super(e);
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				JOptionPane option = new JOptionPane();
				option.showMessageDialog(null,"File not found the game will close","Error",JOptionPane.ERROR_MESSAGE );
				System.exit(0);
				
				
			}
		});
	}
	

}
