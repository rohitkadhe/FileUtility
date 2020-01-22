package views;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class FileUtilButton extends JButton {

	private static final long serialVersionUID = 1L;

	public FileUtilButton() {
		this.setFocusable(false);
		setPreferredSize(new Dimension(160, 30));
	}

	public FileUtilButton(String title) {
		super(title);
		this.setFocusable(false);
		setPreferredSize(new Dimension(160, 30));
	}

	public void setListenser(ActionListener al) {
		this.addActionListener(al);
	}

}
