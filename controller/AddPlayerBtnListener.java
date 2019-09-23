package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.AddPlayerForm;
import view.MainFrame;
import view.StatusBar;

public class AddPlayerBtnListener implements ActionListener {
	
	private MainFrame mainFrame;
	private AddPlayerForm addPlayerForm;
	private GameEngine gameEngine;

	public AddPlayerBtnListener(MainFrame mainFrame, AddPlayerForm addPlayerForm, GameEngine gameEngine) {
		this.mainFrame = mainFrame;
		this.addPlayerForm = addPlayerForm;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String newPlayerId = addPlayerForm.getPlayerId().getText();
		String newPlayerName = addPlayerForm.getPlayerName().getText();
		int newPlayerPoints = Integer.parseInt(addPlayerForm.getInitialPoints().getText());
		
		gameEngine.addPlayer(new SimplePlayer(newPlayerId, newPlayerName, newPlayerPoints));
		
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateSystemStatus("Idle");
		statusBar.updateLastAction("Added Player");
		
		mainFrame.getSummaryPanel().refreshSummary();
	}

}
