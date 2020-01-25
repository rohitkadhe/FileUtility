package views;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.Colors;
import interfaces.Strings;

public class MainFrame extends JFrame implements Strings, Colors {
	private static final long serialVersionUID = 1L;

	private FileUtilButton searchFiles = new FileUtilButton(SEARCH_FOR_FILES);
	private FileUtilButton copyFiles = new FileUtilButton(COPYFILES);
	private FileUtilButton moveFiles = new FileUtilButton(MOVE_FILES);
	private FileUtilButton deleteFiles = new FileUtilButton(DELETE_FILES);
	private FileUtilButton exitOut = new FileUtilButton(EXIT);
	public Container c = new Container();
	public CardLayout card = new CardLayout();

	public MainFrame() {
		JPanel outerPanel = new JPanel();
		outerPanel.setLayout(new BorderLayout());
		outerPanel.add(createUpperPanel(), BorderLayout.NORTH);
		outerPanel.add(createCenterPanel(), BorderLayout.CENTER);
		c.setLayout(card);
		c.add(outerPanel, HOMEPAGE_TITLE);
		add(c);
		setTitle(HOMEPAGE_TITLE);
		setBounds(50, 50, 300, 340);
		setResizable(false);
		setVisible(true);
		setState(Frame.NORMAL);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setupExitButtonAction();
	}

	private JPanel createUpperPanel() {
		JPanel upperPanel = new JPanel();
		upperPanel.setBackground((PRIMARY));
		JLabel label = new JLabel(HOMEPAGE_TITLE);
		label.setFont(new Font("Arial", Font.BOLD, 20));
		upperPanel.add(label);

		return upperPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(new Color(232, 246, 255));
		FlowLayout L = new FlowLayout();
		centerPanel.setLayout(L);

		centerPanel.add(searchFiles);
		centerPanel.add(moveFiles);
		centerPanel.add(copyFiles);
		centerPanel.add(deleteFiles);
		exitOut.setForeground(ACCENT_RED);

		centerPanel.add(exitOut);

		return centerPanel;
	}

	public void setSearchFilesListener(ActionListener al) {
		searchFiles.setListenser(al);
	}

	public void setCopyFilesListener(ActionListener al) {
		copyFiles.setListenser(al);
	}

	public void setMoveFilesListener(ActionListener al) {
		moveFiles.setListenser(al);
	}

	public void setDeleteFilesListener(ActionListener al) {
		deleteFiles.setListenser(al);
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
