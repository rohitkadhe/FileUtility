package views;

import java.awt.Component;
import java.awt.GridLayout;
import javax.swing.JPanel;

public class NavigationBar extends JPanel {

    private static final long serialVersionUID = 1L;

    public NavigationBar() {
	this.setLayout(new GridLayout());
    }

    public void addComponents(Component... components) {
	for (Component component : components) {
	    this.add(component);
	}
    }
}
