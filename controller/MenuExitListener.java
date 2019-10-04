package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainFrame;

public class MenuExitListener implements ActionListener {
	private MainFrame mainFrame;

	public MenuExitListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to exit?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); 
		if (action == JOptionPane.OK_OPTION) {
			System.exit(0);
		}
	}

}
