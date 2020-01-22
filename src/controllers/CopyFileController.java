package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import models.FileMoveUtil;
import views.CopyFrame;
import views.MainFrame;
import views.ProgressDialog;

public class CopyFileController extends GUIController {

	private FileMoveUtil moveUtil = new FileMoveUtil();
	public CopyFrame copyFrame;

	public CopyFileController(MainFrame mainFrame) {
		super(mainFrame);
		mainFrame.getCopyFiles().setListenser(new MainFrameCopyFilesButtonListener());
	}

	class MainFrameCopyFilesButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			copyFrame = new CopyFrame();
			copyFrame.setBrowseDestinationDirectoryButtonListener(new DestinationBrowseButtonListener());
			copyFrame.setBrowseSourceDirectoryButtonListener(new SourceBrowseButtonListener());
			copyFrame.setCopyButtonListener(new CopyButtonListener());
		}
	}

	class CopyButtonListener implements ActionListener {
		private ProgressDialog progressDialog;

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchOption = copyFrame.getComboBoxSelectedItem();
			String fileName = copyFrame.getFileNameInputText();
			String fromDirectoryPath = copyFrame.getSourceDirectoryInputText();
			String toDirectoryPath = copyFrame.getDestinationDirectoryInputText();
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					progressDialog = new ProgressDialog();
					progressDialog.setProgressString(COPYING);
					try {
						moveUtil.copyFiles(fromDirectoryPath, toDirectoryPath, fileName, searchOption);
						progressDialog.closeDialog();
					} catch (Exception e) {
						progressDialog.closeDialog();
						JOptionPane.showMessageDialog(null, ERROR_COPYING_FILES, HOMEPAGE_TITLE,
								JOptionPane.ERROR_MESSAGE);
					}
					copyFrame.openFolderContainingFile(toDirectoryPath);
				}
			});
			t.start();
		}
	}

	class DestinationBrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText(SELECT_DIRECTORY);
			int returnVal = fc.showOpenDialog(copyFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				copyFrame.setDestinationDirectoryInputText(file.getAbsolutePath());
			}
		}
	}

	class SourceBrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText(SELECT_DIRECTORY);
			int returnVal = fc.showOpenDialog(copyFrame);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				copyFrame.setSourceDirectoryInputText(file.getAbsolutePath());
			}
		}
	}
}
