package views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import interfaces.Colors;
import interfaces.Strings;

public class TableFrame extends JFrame implements Strings, Colors {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JLabel filesFoundField;

	private DefaultTableModel model;

	public TableFrame(Object... columnNames) {
		table = new JTable(new DefaultTableModel(new Object[][] {}, columnNames));
		model = (DefaultTableModel) table.getModel();
		table.setDefaultEditor(Object.class, null);
		addTableRowListener();
		this.setVisible(true);
		this.add(createUpperPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		this.add(createLowerPanel(), BorderLayout.SOUTH);
		this.pack();
	}

	private JPanel createUpperPanel() {
		JPanel upperPanel = new JPanel();
		JLabel upperPanelTitle = new JLabel(HOMEPAGE_TITLE);
		upperPanelTitle.setFont(new Font("Arial", Font.BOLD, 15));
		upperPanel.setBackground(PRIMARY);
		upperPanel.add(upperPanelTitle);
		return upperPanel;
	}

	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();

		centerPanel.setBackground(PRIMARY);
		centerPanel.setLayout(new BorderLayout());
		JScrollPane scroll = new JScrollPane(table);

		JTableHeader header = table.getTableHeader();
		header.setBackground(PRIMARY);
		header.setReorderingAllowed(false);

		table.setFillsViewportHeight(true);

		centerPanel.add(scroll, BorderLayout.CENTER);

		return centerPanel;
	}

	private JPanel createLowerPanel() {
		JPanel lowerPanel = new JPanel();
		lowerPanel.setBackground(PRIMARY);
		filesFoundField = new JLabel(FILES_FOUND + "0");
		lowerPanel.add(filesFoundField);
		return lowerPanel;
	}

	public void addTableRowListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if (row >= 0) {
					String path = (String) table.getValueAt(row, 1);
					openFolderContainingFile(path);
				}
			}
		});
	}

	public void openFolderContainingFile(String path) {
		String parentPath = path;
		if (!new File(path).isDirectory()) {
			parentPath = new File(path).getParent();
		}
		try {
			Desktop.getDesktop().open(new File(parentPath));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void fillTable(ArrayList<File> filesFound) {
		for (File file : filesFound)
			addRowToTable(new Object[] { file.getName(), file.getAbsolutePath() });
		filesFoundField.setText(FILES_FOUND + (Integer.toString(filesFound.size())));
	}

	private void addRowToTable(Object[] rowData) {
		model.addRow(rowData);
	}
}
