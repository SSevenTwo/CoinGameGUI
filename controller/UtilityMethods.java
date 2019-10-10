package controller;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.StatusBar;

public class UtilityMethods {
	
	public static void removePlayerIfNegativePoints(MainFrame mainFrame) {
		GameEngine gameEngine = mainFrame.getGameEngine();
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getPoints() <= 0) {
				JOptionPane.showMessageDialog(mainFrame, String
						.format("Oh no! %s has lost all their points and have been eliminated!", player.getPlayerName()),
						"Player Lost", JOptionPane.INFORMATION_MESSAGE);
				gameEngine.removePlayer(player);
			}
		}
		updateSummaryPanelAndToolbar(mainFrame);
	}

	public static void updateSummaryPanelAndToolbar(MainFrame mainFrame) {
		GameEngine gameEngine = mainFrame.getGameEngine();
		mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
		mainFrame.getSummaryPanel().refreshSummary();
	}
	
	public static void spinSpinner(MainFrame mainFrame) {
		GameEngine gameEngine = mainFrame.getGameEngine();
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		mainFrame.getPlayerCoinPanel().setVisible(false);
		mainFrame.add(mainFrame.getCoinPanel(),BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
		UtilityMethods.removePlayerIfNegativePoints(mainFrame);
	}
	
	public static void spinSpinnerIfReady(MainFrame mainFrame) {
		StatusBar statusBar = mainFrame.getStatusBar();
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			UtilityMethods.spinSpinner(mainFrame);
			statusBar.updateSystemStatus("Idle");
		}
	}
}
