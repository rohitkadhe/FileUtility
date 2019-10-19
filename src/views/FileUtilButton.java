package views;

import javax.swing.JButton;

public class FileUtilButton extends JButton {

    private static final long serialVersionUID = 1L;

    public FileUtilButton() {
	this.setFocusable(false);
    }

    public FileUtilButton(String title) {
	super(title);
	this.setFocusable(false);
    }
}
