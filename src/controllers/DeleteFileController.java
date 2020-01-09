package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;
import models.FileDeleteUtil;
import views.DeleteFrame;
import views.MainFrame;
import views.ProgressDialog;

public class DeleteFileController extends GUIController {

	private FileDeleteUtil deleteUtil = new FileDeleteUtil();
	public DeleteFrame deleteFrame;
	
	public DeleteFileController(MainFrame mainFrame) {
		super(mainFrame);
		mainFrame.getDeleteFiles().setListenser(new MainFrameDeleteButtonListener());
	}
	 class MainFrameDeleteButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			deleteFrame = new DeleteFrame();
			deleteFrame.setBrowseDirectoryButtonListener(new BrowseButtonListener());
			deleteFrame.setSearchButtonListener(new DeleteButtonListener());
			 
		}
	}
	class DeleteButtonListener implements ActionListener{		private ProgressDialog progressDialog;
		@Override
		public void actionPerformed(ActionEvent e) {	
			String searchOption = deleteFrame.getComboBoxSelectedItem();
			String fileName = deleteFrame.getFileNameInputText();
			String directoryPath = deleteFrame.getBrowseDirectoryInputText();
			
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {	
					progressDialog = new ProgressDialog();
					progressDialog.setProgressString(DELETING);
					deleteUtil.moveToRecycleBin(directoryPath, fileName, searchOption);
					progressDialog.closeDialog();
				}	
			});
			t.start();
		}
	}
	class BrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText(SELECT_DIRECTORY);
			int returnVal = fc.showOpenDialog(deleteFrame);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				 File file = fc.getSelectedFile();
				 deleteFrame.setBrowseDirectoryInputText(file.getAbsolutePath());
			}			
		}
	}
}
