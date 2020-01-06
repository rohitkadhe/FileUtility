package views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import interfaces.Colors;
import interfaces.SearchOption;
import interfaces.Strings;

import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;

public class SearchFrame extends JFrame implements Colors, SearchOption, Strings {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField fileNameInput;
	private JTextField browseDirectoryInput;
	private  String[] comboBoxOptions = {EQUALS, BEGINS_WITH, ENDS_WITH, CONTAINS};
	private JComboBox comboBox = new JComboBox(comboBoxOptions);
	private FileUtilButton browseDirectoryButton = new FileUtilButton("Browse");
	private FileUtilButton searchButton = new FileUtilButton("Search");
	
	private JLabel searchForFilesLabel = new JLabel("Search For Files");
	private JLabel searchFilterLabel = new JLabel("Search Filter");

	public SearchFrame() {
		setTitle(HOMEPAGETITLE);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 196);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel searchQueryLabel = new JLabel("File Name");
		fileNameInput = new JTextField();
		fileNameInput.setColumns(10);
		JLabel directoryLabel = new JLabel("Directory");
		
		browseDirectoryInput = new JTextField();
		browseDirectoryInput.setColumns(10);
		this.setVisible(true);
		contentPane.setBackground(PRIMARY);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		createHorizontalGroup(gl_contentPane, searchQueryLabel, directoryLabel);
		createVerticalGroup(gl_contentPane, searchQueryLabel, directoryLabel);
		contentPane.setLayout(gl_contentPane);
	}
	
	private void createHorizontalGroup(GroupLayout gl_contentPane , JLabel searchQueryLabel, JLabel directoryLabel) {
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(157)
					.addComponent(searchForFilesLabel)
					.addContainerGap(177, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(searchQueryLabel)
						.addComponent(searchFilterLabel)
						.addComponent(directoryLabel, GroupLayout.DEFAULT_SIZE, 62, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fileNameInput, GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE)
							.addGap(105))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(browseDirectoryInput, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(browseDirectoryButton, GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)
							.addGap(5))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(searchButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(129))))
		);
	}
	
	private void createVerticalGroup(GroupLayout gl_contentPane , JLabel searchQueryLabel, JLabel directoryLabel) {
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addComponent(searchForFilesLabel)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchQueryLabel)
						.addComponent(fileNameInput))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(10)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(browseDirectoryInput)
								.addComponent(directoryLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addComponent(browseDirectoryButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(searchFilterLabel)
						.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(10)
					.addComponent(searchButton, GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE))
		);
	}
	
	public void setBrowseDirectoryButtonListener(ActionListener al) {
		browseDirectoryButton.setListenser(al);
	}
	public void setSearchButtonListener(ActionListener al) {
		searchButton.setListenser(al);
	}
	
	public String getFileNameInputText() {
		return fileNameInput.getText().toString();
	}
	
	public String getBrowseDirectoryInputText() {
		return browseDirectoryInput.getText().toString();
	}
	
	public void setBrowseDirectoryInputText(String text) {
		browseDirectoryInput.setText(text);
	}
	
	public String getComboBoxSelectedItem() {
		return comboBox.getSelectedItem().toString();
	}
}
