package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;

import view.MainFrame;

public class MenuCheckBoxListener implements ActionListener {
	private MainFrame mainFrame;

	public MenuCheckBoxListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JCheckBoxMenuItem button = (JCheckBoxMenuItem) e.getSource();
		mainFrame.getSummaryPanel().setVisible(button.isSelected());
	}

}
