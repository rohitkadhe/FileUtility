package controllers;

import views.MainFrame;

public abstract class GUIController {

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
