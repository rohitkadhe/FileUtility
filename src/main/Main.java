package main;
import controllers.SearchFileController;
import views.MainFrame;

public class Main {
    public static void main(String args[]) {
    	MainFrame mainFrame = new MainFrame();
    	new SearchFileController(mainFrame);
    	
    }
}
