package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import models.FileSearchUtil;
import views.MainFrame;
import views.ProgressDialog;
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

	class SearchButtonListener implements ActionListener {
		private ArrayList<File> filesFound;
		private ProgressDialog progressDialog;

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchOption = searchFrame.getComboBoxSelectedItem();
			String fileName = searchFrame.getFileNameInputText();
			String directoryPath = searchFrame.getBrowseDirectoryInputText();

			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					progressDialog = new ProgressDialog();
					progressDialog.setProgressString(SEARCHING);
					try {
						filesFound = searchUtil.searchFiles(directoryPath, fileName, searchOption);
						if (!filesFound.isEmpty()) {
							progressDialog.closeDialog();
							createTable(filesFound, fileName);
						}
					} catch (Exception e) {
						progressDialog.closeDialog();
						JOptionPane.showMessageDialog(null, NO_FILES_FOUND, HOMEPAGE_TITLE,
								JOptionPane.INFORMATION_MESSAGE);
					}
					progressDialog.closeDialog();
				}
			});
			t.start();
		}

		private void createTable(ArrayList<File> filesFound, String fileName) {
			TableFrame tableFrame = new TableFrame(FILE_NAME, FILE_LOCATION);
			populateTable(filesFound, tableFrame);
		}

		private void populateTable(ArrayList<File> filesFound, TableFrame tableFrame) {
			for (File file : filesFound)
				tableFrame.addRowToTable(new Object[] { file.getName(), file.getAbsolutePath() });
		}
	}

	class BrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText(SELECT_DIRECTORY);
			int returnVal = fc.showOpenDialog(searchFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				searchFrame.setBrowseDirectoryInputText(file.getAbsolutePath());
			}
		}
	}
}
