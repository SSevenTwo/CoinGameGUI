package view;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import controller.AddPlayerBtnListener;
import controller.PlaceBetBtnListener;
import controller.RemovePlayerBtnListener;
import controller.MenuAboutListener;
import controller.MenuCheckBoxListener;
import controller.MenuExitListener;

@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	private JMenu file;
	private JMenu window;
	private JMenu show;
	private JMenu help;
	private JMenuItem addPlayerItem;
	private JMenuItem removePlayerItem;
	private JMenuItem placeBetItem;
	private JMenuItem aboutItem;
	private JMenuItem exitItem;
	private MainFrame mainFrame;
	private JCheckBoxMenuItem displayForms;

	public MenuBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		
		makeComponents();
		addComponents();
		addActionListeners();
		setAcceleratorsAndMnemonics();

	}
	
	private void makeComponents() {
		file = new JMenu("File");
		addPlayerItem = new JMenuItem("Add Player");
		removePlayerItem = new JMenuItem("Remove Player");
		placeBetItem = new JMenuItem("Place Bet");
		exitItem = new JMenuItem("Exit");
		window = new JMenu("Window");
		show = new JMenu("Show");
		help = new JMenu("Help");
		aboutItem = new JMenuItem("About Program");
		displayForms = new JCheckBoxMenuItem("Display Summary");
		displayForms.setSelected(true);
	}

	private void addComponents() {
		file.add(addPlayerItem);
		file.add(removePlayerItem);
		file.addSeparator();
		file.add(placeBetItem);
		file.addSeparator();
		file.add(exitItem);
		help.add(aboutItem);
		show.add(displayForms);
		window.add(show);
		add(file);
		add(window);
		add(help);
	}

	private void addActionListeners() {
		addPlayerItem.addActionListener(new AddPlayerBtnListener(mainFrame));
		removePlayerItem.addActionListener(new RemovePlayerBtnListener(mainFrame));
		placeBetItem.addActionListener(new PlaceBetBtnListener(mainFrame));
		displayForms.addActionListener(new MenuCheckBoxListener(mainFrame));
		aboutItem.addActionListener(new MenuAboutListener(mainFrame));
		exitItem.addActionListener(new MenuExitListener(mainFrame));
	}

	private void setAcceleratorsAndMnemonics() {
		addPlayerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
		removePlayerItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		placeBetItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.CTRL_MASK));
		displayForms.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
		file.setMnemonic(KeyEvent.VK_F);
		window.setMnemonic(KeyEvent.VK_W);
		help.setMnemonic(KeyEvent.VK_H);
	}

}
