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

public class DeleteFrame extends JFrame implements Colors, SearchOption, Strings {

	private static final long serialVersionUID = 1L;
	private JTextField fileNameInput  = new JTextField();
	private JTextField browseDirectoryInput  = new JTextField();
	private  String[] comboBoxOptions = {EQUALS, BEGINS_WITH, ENDS_WITH, CONTAINS};
	private JComboBox comboBox = new JComboBox(comboBoxOptions);
	private FileUtilButton browseDirectoryButton = new FileUtilButton(BROWSE);
	private FileUtilButton deleteFilesButton = new FileUtilButton(DELETE_FILES);

	private JLabel searchFilterLabel = new JLabel(SEARCH_FILTER);

	private JLabel searchQueryLabel = new JLabel(FILE_NAME);
	private JLabel directoryLabel = new JLabel(DIRECTORY);
	
	public DeleteFrame() {
		JPanel contentPane = new JPanel();
		fileNameInput.setColumns(10);
		browseDirectoryInput.setColumns(10);
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		createHorizontalGroup(gl_contentPane);
		createVerticalGroup(gl_contentPane);
		contentPane.setLayout(gl_contentPane);
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(PRIMARY);
		setTitle(HOMEPAGE_TITLE);
		setBounds(100, 100, 450, 196);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setContentPane(contentPane);
		this.setVisible(true);
	}
	
	private void createHorizontalGroup(GroupLayout gl_contentPane) {
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(searchQueryLabel)
						.addComponent(searchFilterLabel)
						.addComponent(directoryLabel, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(fileNameInput, GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE)
							.addGap(105))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(browseDirectoryInput, GroupLayout.DEFAULT_SIZE, 231, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(browseDirectoryButton, GroupLayout.PREFERRED_SIZE, 82, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(12)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
								.addComponent(deleteFilesButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(comboBox, Alignment.LEADING, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
							.addGap(129))))));
	}
	
	private void createVerticalGroup(GroupLayout gl_contentPane) {
		gl_contentPane.setVerticalGroup(
				gl_contentPane.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_contentPane.createSequentialGroup()
						.addPreferredGap(ComponentPlacement.RELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(searchQueryLabel)
							.addComponent(fileNameInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(16)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(browseDirectoryInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(directoryLabel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(browseDirectoryButton, GroupLayout.PREFERRED_SIZE, 20, Short.MAX_VALUE))
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
							.addComponent(searchFilterLabel)
							.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(10)
						.addComponent(deleteFilesButton, GroupLayout.PREFERRED_SIZE, 26, GroupLayout.PREFERRED_SIZE))
			);
			
	}
	
	public void setBrowseDirectoryButtonListener(ActionListener al) {
		browseDirectoryButton.setListenser(al);
	}
	public void setSearchButtonListener(ActionListener al) {
		deleteFilesButton.setListenser(al);
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
