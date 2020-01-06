package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
			searchFrame.setBrowseDirectoryButtonListener(new BrowseButtonListener());
			searchFrame.setSearchButtonListener(new SearchButtonListener());
			 
		}
	}
	class SearchButtonListener implements ActionListener{
		private ArrayList<File> filesFound; 

		@Override
		public void actionPerformed(ActionEvent e) {
			try {	
				String searchOption = searchFrame.getComboBoxSelectedItem();
				String fileName = searchFrame.getFileNameInputText();
				String directoryPath = searchFrame.getBrowseDirectoryInputText();

				filesFound = searchUtil.searchFiles(directoryPath, fileName, searchOption);
				if(!filesFound.isEmpty()) {
					TableFrame tableFrame = new TableFrame("File Name", "File Location");
					for(File file:filesFound)
						tableFrame.addRowToTable(new Object[] {file.getName(), file.getAbsolutePath()});
				}
				else {
					JOptionPane.showMessageDialog(searchFrame,
						    "File with name" +" " + fileName + " not found", "File Utility", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch(NullPointerException ex) {
				JOptionPane.showMessageDialog(searchFrame,
					    "Fields Cannot be empty", "File Utility Error", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	class BrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText("Select Directory");
			int returnVal = fc.showOpenDialog(searchFrame);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 searchFrame.setBrowseDirectoryInputText(file.getAbsolutePath());
			}			
		}
	}
}
