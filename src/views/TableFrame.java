package views;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

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
	
	private DefaultTableModel model;
	
	public TableFrame(Object ...columnNames) {
		table = new JTable(new DefaultTableModel(new Object[][] {},columnNames));
		model = (DefaultTableModel) getTable().getModel();
		this.add(createUpperPanel(), BorderLayout.NORTH);
		this.add(createCenterPanel(), BorderLayout.CENTER);
		
		table.setDefaultEditor(Object.class, null);
		this.setVisible(true);
		addTableRowListener();
		this.pack();
	}
	private JPanel createUpperPanel() {
		JPanel upperPanel = new JPanel();
		JLabel upperPanelTitle = new JLabel(HOMEPAGETITLE);
		upperPanelTitle.setFont(new Font("Arial", Font.BOLD, 15));
		upperPanel.setBackground(PRIMARY);
		upperPanel.add(upperPanelTitle);
		return upperPanel;
	}
	
	private JPanel createCenterPanel() {
		JPanel centerPanel = new JPanel();
		
		centerPanel.setBackground(PRIMARY);
		centerPanel.setLayout(new BorderLayout());  
		JScrollPane scroll = new JScrollPane(getTable());
		
		//This is to set the table columns color 
		JTableHeader header = getTable().getTableHeader();
		header.setBackground(PRIMARY);
		header.setReorderingAllowed(false);
		
		//Sets the table background to white 
		getTable().setOpaque(true);
		getTable().setFillsViewportHeight(true);
		
		centerPanel.add(scroll, BorderLayout.CENTER);
		
		return centerPanel;
	}
	public DefaultTableModel getModel() {
		return model;
	}
	public JTable getTable() {
		return table;
	}
	public void addTableRowListener() {
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent evt) {
				int row = table.rowAtPoint(evt.getPoint());
				if(row>=0) {							
					String path = (String) table.getValueAt(row, 1);
					String parentPath = new File(path).getParent();	
					try {
						Desktop.getDesktop().open(new File(parentPath));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
	}
}
