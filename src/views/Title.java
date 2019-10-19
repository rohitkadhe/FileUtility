package views;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

import interfaces.Strings;

public class Title extends JPanel implements Strings {

    private static final long serialVersionUID = 1L;
    private GridBagConstraints gbc = new GridBagConstraints();

    private JLabel titleLabel = new JLabel(Strings.homePageTitle);

    public Title() {
	setUpLayoutConstraints();
	this.setBackground(new Color(232, 246, 255));
    }

    public void setUpLayoutConstraints() {
	this.setLayout(new GridBagLayout());
	Insets insets = new Insets(1, 1, 1, 1);
	gbc.insets = insets;
	gbc.gridx = 0;
	gbc.gridy = 0;
	gbc.weightx = 2;
	this.add(titleLabel, gbc);
    }

    public void setTitleText(String title) {
	titleLabel.setText(title);
    }
}
