package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import models.FileSearchUtil;
import views.MainFrame;
import views.SearchFrame;
import views.TableFrame;

public class SearchFileController extends GUIController {

	private FileSearchUtil searchUtil = new FileSearchUtil();
	public SearchFrame searchFrame;
	
	public SearchFileController(MainFrame mainFrame) {
		super(mainFrame);
		mainFrame.getSearchFiles().setListenser(new MainFrameSearchButtonListener());
	}
	 class MainFrameSearchButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			searchFrame = new SearchFrame();
			searchFrame.getBrowseDirectoryButton().setListenser(new BrowseButtonListener());
			searchFrame.getSearchButton().setListenser(new SearchButtonListener());
			 
		}
	}
	class SearchButtonListener implements ActionListener{
		private ArrayList<File> filesFound; 

		@Override
		public void actionPerformed(ActionEvent e) {
			try {				
				String searchOption = (String)searchFrame.getComboBox().getSelectedItem();
				String fileName = searchFrame.getFileNameInput().getText().toString();
				String directoryPath = searchFrame.getDirectoryInput().getText().toString();
				filesFound = searchUtil.searchFiles(directoryPath, fileName, searchOption);
				if(!filesFound.isEmpty()) {
					TableFrame tableFrame = new TableFrame("File Name", "File Location");
					for(File file:filesFound)
						tableFrame.getModel().addRow(new Object[] {file.getName(), file.getAbsolutePath()});
				}
			}catch(NullPointerException ex) {
				
			}
		}
	}
	class BrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int returnVal = fc.showOpenDialog(searchFrame);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 searchFrame.getDirectoryInput().setText(file.getAbsolutePath());
			}			
			else {
				System.out.println("Pressed cancel");
			}
		}
	}
}
