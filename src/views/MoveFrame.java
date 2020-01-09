package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import interfaces.Colors;
import interfaces.SearchOption;
import interfaces.Strings;

import java.awt.Desktop;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class MoveFrame extends JFrame implements Colors, SearchOption, Strings {

	private static final long serialVersionUID = 1L;
	private  String[] comboBoxOptions = {EQUALS, BEGINS_WITH, ENDS_WITH, CONTAINS};
	private JComboBox comboBox = new JComboBox(comboBoxOptions);
	
	private FileUtilButton moveFilesButton = new FileUtilButton(MOVE_FILES);
	private JLabel searchFilterLabel = new JLabel(SEARCH_FILTER);
	private JLabel searchQueryLabel = new JLabel(FILE_NAME);

	private JTextField sourceDirectoryInput = new JTextField();
	private final JLabel destinationDirectoryLabel = new JLabel(DESTINATION_DIRECTORY);
	private final JTextField destinationDirectoryInput = new JTextField();
	
	private final JTextField fileNameInput = new JTextField();
	private FileUtilButton browseSourceDirectoryButton = new FileUtilButton(BROWSE);
	private FileUtilButton browseDestinationDirectoryButton = new FileUtilButton(BROWSE);
	
	private JLabel sourceDirectoryLabel = new JLabel("Source Directory");
	
	public MoveFrame() {
		JPanel contentPane = new JPanel();
		fileNameInput.setColumns(10);
		destinationDirectoryInput.setColumns(10);
		sourceDirectoryInput.setColumns(10);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(PRIMARY);
		setTitle(HOMEPAGE_TITLE);
		setBounds(100, 100, 486, 252);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		createHorizontalGroup(gl_contentPane);		
		createVerticalGroup(gl_contentPane);
		contentPane.setLayout(gl_contentPane);

		setContentPane(contentPane);		
		this.setVisible(true);
	}
	
	private void createHorizontalGroup(GroupLayout gl_contentPane) {
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(searchQueryLabel)
						.addComponent(destinationDirectoryLabel)
						.addComponent(sourceDirectoryLabel)
						.addComponent(searchFilterLabel))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(moveFilesButton, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(fileNameInput, GroupLayout.PREFERRED_SIZE, 230, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(sourceDirectoryInput)
								.addComponent(destinationDirectoryInput, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(browseSourceDirectoryButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
								.addComponent(browseDestinationDirectoryButton, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
	}
	
	private void createVerticalGroup(GroupLayout gl_contentPane) {
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addGap(22)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(searchQueryLabel)
							.addComponent(fileNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(19)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
									.addComponent(sourceDirectoryInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(browseSourceDirectoryButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(18)
								.addComponent(sourceDirectoryLabel)))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(destinationDirectoryLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(destinationDirectoryInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(browseDestinationDirectoryButton, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(searchFilterLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addComponent(moveFilesButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
						.addContainerGap())
			);
	}
	
	public String getFileNameInputText() {
		return fileNameInput.getText().toString();
	}
	
	public String getSourceDirectoryInputText() {
		return sourceDirectoryInput.getText().toString();
	}
	
	public String getDestinationDirectoryInputText() {
		return destinationDirectoryInput.getText().toString();
	}
	
	public void setSourceDirectoryInputText(String text) {
		sourceDirectoryInput.setText(text);
	}
	
	public void setDestinationDirectoryInputText(String text) {
		destinationDirectoryInput.setText(text);
	}
	public void setMoveButtonListener(ActionListener al) {
		moveFilesButton.setListenser(al);
	}
	
	public void setBrowseSourceDirectoryButtonListener(ActionListener al) {
		browseSourceDirectoryButton.setListenser(al);
	}
	
	public void setBrowseDestinationDirectoryButtonListener(ActionListener al) {
		browseDestinationDirectoryButton.setListenser(al);
	}
	
	public String getComboBoxSelectedItem() {
		return comboBox.getSelectedItem().toString();
	}
	
	public void openFolderContainingFile(String path) {
		String parentPath = path;	
		if(!new File(path).isDirectory()) {
			parentPath = new File(path).getParent();	
		}
		try {
			Desktop.getDesktop().open(new File(parentPath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}

