package views;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    public MainFrame() {

	this.setBounds(50, 50, 525, 350);
	this.setLayout(new BorderLayout());
	this.setResizable(false);
    }

    public static void main(String agrs[]) {
	MainFrame mf = new MainFrame();
	Header header = new Header();
	mf.add(header, BorderLayout.NORTH);
	mf.add(new CardLayoutContainer(), BorderLayout.CENTER);
	mf.setVisible(true);

    }
}
