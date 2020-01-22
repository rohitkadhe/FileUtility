package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import models.FileMoveUtil;
import views.MainFrame;
import views.MoveFrame;
import views.ProgressDialog;

public class MoveFileController extends GUIController {

	private FileMoveUtil moveUtil = new FileMoveUtil();
	public MoveFrame moveFrame;

	public MoveFileController(MainFrame mainFrame) {
		super(mainFrame);
		mainFrame.getMoveFiles().setListenser(new MainFrameMoveFilesButtonListener());
	}

	class MainFrameMoveFilesButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			moveFrame = new MoveFrame();
			moveFrame.setBrowseDestinationDirectoryButtonListener(new DestinationBrowseButtonListener());
			moveFrame.setBrowseSourceDirectoryButtonListener(new SourceBrowseButtonListener());
			moveFrame.setMoveButtonListener(new MoveButtonListener());

		}
	}

	class MoveButtonListener implements ActionListener {
		private ProgressDialog progressDialog;

		@Override
		public void actionPerformed(ActionEvent e) {
			String searchOption = moveFrame.getComboBoxSelectedItem();
			String fileName = moveFrame.getFileNameInputText();
			String fromDirectoryPath = moveFrame.getSourceDirectoryInputText();
			String toDirectoryPath = moveFrame.getDestinationDirectoryInputText();
			Thread t = new Thread(new Runnable() {
				@Override
				public void run() {
					progressDialog = new ProgressDialog();
					progressDialog.setProgressString(MOVING);
					try {
						moveUtil.moveFiles(fromDirectoryPath, toDirectoryPath, fileName, searchOption);
						progressDialog.closeDialog();
						moveFrame.openFolderContainingFile(toDirectoryPath);
					} catch (Exception e) {
						progressDialog.closeDialog();
						JOptionPane.showMessageDialog(null, ERROR_MOVING_FILES, HOMEPAGE_TITLE,
								JOptionPane.ERROR_MESSAGE);
					}
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
			int returnVal = fc.showOpenDialog(moveFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				moveFrame.setDestinationDirectoryInputText(file.getAbsolutePath());
			}
		}
	}

	class SourceBrowseButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			final JFileChooser fc = new JFileChooser();
			fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			fc.setApproveButtonText(SELECT_DIRECTORY);
			int returnVal = fc.showOpenDialog(moveFrame);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				moveFrame.setSourceDirectoryInputText(file.getAbsolutePath());
			}
		}
	}
}
