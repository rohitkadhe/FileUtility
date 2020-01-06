package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import interfaces.Colors;
import interfaces.Strings;

public class MainFrame extends JFrame implements Strings, Colors 
{
	private static final long serialVersionUID = 1L;
	
	private FileUtilButton searchFiles = new FileUtilButton(SEARCHFORFILES);
	private FileUtilButton copyFiles = new FileUtilButton(COPYFILES);
	private FileUtilButton moveFiles = new FileUtilButton(MOVEFILES);
	private FileUtilButton deleteFiles = new FileUtilButton(DELETEFILES);
	private FileUtilButton exitOut = new FileUtilButton(EXIT);
	public Container c = new Container();
	public CardLayout card = new CardLayout();
	public MainFrame() 
	{
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(createUpperPanel(), BorderLayout.NORTH);
		outerPanel.add(createCenterPanel(), BorderLayout.CENTER);
		c.setLayout(card);
		c.add(outerPanel, HOMEPAGETITLE);
		this.add(c);
		this.setTitle(HOMEPAGETITLE);
		this.setBounds(50, 50, 300, 340);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupExitButtonAction();
	}

	private JPanel createUpperPanel()
	{
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground((PRIMARY));
		JLabel label = new JLabel(HOMEPAGETITLE);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		upperPanel.add(label);
		
		return upperPanel;
	}
	
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(232, 246, 255));
		FlowLayout L = new FlowLayout( );
		centerPanel.setLayout(L);
		
		centerPanel.add(searchFiles);
		centerPanel.add(moveFiles);
		centerPanel.add(copyFiles);
		centerPanel.add(deleteFiles);
		exitOut.setForeground(Color.RED); 

		centerPanel.add(exitOut);

		return centerPanel;
	}               

	public FileUtilButton getSearchFiles() {
		return searchFiles;
	}
	public FileUtilButton getCopyFiles() {
		return copyFiles;
	}
	public FileUtilButton getMoveFiles() {
		return moveFiles;
	}
	public FileUtilButton getDeleteFiles() {
		return deleteFiles;
	}
	public void setupExitButtonAction() {
		exitOut.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				dispose();
				System.exit(0);
			}
			
		});
	}
}
