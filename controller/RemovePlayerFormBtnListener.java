package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.MainFrame;

public class RemovePlayerFormBtnListener implements ActionListener{
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public RemovePlayerFormBtnListener(MainFrame mainFrame, GameEngine gameEngine) {
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.hideForms();
		mainFrame.getRemovePlayerForm().setPlayers(gameEngine.getAllPlayers());
		mainFrame.add(mainFrame.getRemovePlayerForm(), BorderLayout.WEST);
		mainFrame.getRemovePlayerForm().setVisible(true);

	}

}
