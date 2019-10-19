package views;

import java.awt.BorderLayout;
import javax.swing.JPanel;

import interfaces.Strings;

public class Header extends JPanel implements Strings {
    private static final long serialVersionUID = 1L;

    private Title mainTitle = new Title();
    private NavigationBar navBar = new NavigationBar();

    private FileUtilButton searchForFiles = new FileUtilButton(Strings.searchForFiles);
    private FileUtilButton moveFiles = new FileUtilButton(Strings.moveFiles);

    private FileUtilButton copyFiles = new FileUtilButton(Strings.copyFiles);
    private FileUtilButton deleteFiles = new FileUtilButton(Strings.deleteFiles);

    public Header() {
	this.setLayout(new BorderLayout());
	this.add(mainTitle, BorderLayout.NORTH);
	this.add(navBar, BorderLayout.CENTER);
	navBar.addComponents(searchForFiles, moveFiles, copyFiles, deleteFiles);
    }
}
