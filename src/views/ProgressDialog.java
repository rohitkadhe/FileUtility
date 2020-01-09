package views;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

import interfaces.Colors;
import interfaces.Strings;

public class ProgressDialog implements Strings, Colors {

	private	JDialog dialog = new JDialog();
    private	JProgressBar progressBar = new JProgressBar(); 
	
    public ProgressDialog() {
    	dialog.setBounds(50, 50, 300, 100);
    	progressBar.setIndeterminate(true);
		progressBar.setStringPainted(true);
		dialog.setTitle(HOMEPAGE_TITLE);
		progressBar.setBackground(PRIMARY);
		dialog.add(progressBar);
		dialog.setVisible(true);
		dialog.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}
	
	public void setProgressString(String s) {
		progressBar.setString(s);
	}
	
	public void closeDialog() {
		dialog.dispose();
	}
}
