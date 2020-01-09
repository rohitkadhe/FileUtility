package controllers;

import interfaces.Colors;
import interfaces.Strings;
import views.MainFrame;

public abstract class GUIController implements Strings, Colors {

	protected MainFrame mainFrame;
	
	public GUIController(MainFrame mainFrame) {
		this.setMainFrame(mainFrame);
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}

	public void setMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
}
