package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MainFrame;

public class AddPlayerFormBtnListener implements ActionListener{
	
	private MainFrame mainFrame;

	public AddPlayerFormBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		mainFrame.hideForms();
		mainFrame.getAddPlayerForm().setVisible(true);
		mainFrame.add(mainFrame.getAddPlayerForm(), BorderLayout.WEST);
	}

}
