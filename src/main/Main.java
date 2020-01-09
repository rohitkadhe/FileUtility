package main;

import controllers.CopyFileController;
import controllers.DeleteFileController;
import controllers.MoveFileController;
import controllers.SearchFileController;
import views.MainFrame;

public class Main {
    public static void main(String args[]) {
    	MainFrame mainFrame = new MainFrame();
    	new SearchFileController(mainFrame);
    	new CopyFileController(mainFrame);
    	new MoveFileController(mainFrame);
    	new DeleteFileController(mainFrame);
    }
}
