package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.MainFrame;

public class MenuAboutListener implements ActionListener {

	private MainFrame mainFrame;

	public MenuAboutListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(mainFrame, "Created by Ian Nguyen \nID: S3788210", "About",
				JOptionPane.INFORMATION_MESSAGE);
	}


}
