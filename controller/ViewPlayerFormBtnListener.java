package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.MainFrame;

public class ViewPlayerFormBtnListener implements ActionListener{
	
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public ViewPlayerFormBtnListener(MainFrame mainFrame, GameEngine gameEngine) {
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.hideForms();
		mainFrame.getViewPlayerPanel().setPlayers(gameEngine.getAllPlayers());
		mainFrame.add(mainFrame.getViewPlayerPanel(), BorderLayout.WEST);
		mainFrame.getViewPlayerPanel().setVisible(true);

	}

}
