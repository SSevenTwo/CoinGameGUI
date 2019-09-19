package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.MainFrame;

public class SpinPlayerFormBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public SpinPlayerFormBtnListener(MainFrame mainFrame, GameEngine gameEngine) {
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.hideForms();
		mainFrame.getSpinPlayerForm().setPlayers(gameEngine.getAllPlayers());
		mainFrame.add(mainFrame.getSpinPlayerForm(), BorderLayout.WEST);
		mainFrame.getSpinPlayerForm().setVisible(true);

	}
}
