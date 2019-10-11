package controller.util;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerDecorator;
import view.StatusBar;

public class ControllerUtilities {

	public static void removePlayerIfNegativePoints(MainFrame mainFrame) {
		GameEngine gameEngine = mainFrame.getGameEngine();
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getPoints() <= 0) {
				JOptionPane.showMessageDialog(mainFrame,
						String.format("Oh no! %s has lost all their points and have been eliminated!",
								player.getPlayerName()),
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
		displayCoinPanel(mainFrame);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		resetPlayerSpinAndBetTracker(mainFrame);
	}

	public static void resetPlayerSpinAndBetTracker(MainFrame mainFrame) {
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
		ControllerUtilities.removePlayerIfNegativePoints(mainFrame);
	}

	public static void spinSpinnerIfReady(MainFrame mainFrame) {
		StatusBar statusBar = mainFrame.getStatusBar();
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			ControllerUtilities.spinSpinner(mainFrame);
			statusBar.updateSystemStatus("Idle");
		}
	}

	private static void displayCoinPanel(MainFrame mainFrame) {
		mainFrame.getPlayerCoinPanel().setVisible(false);
		mainFrame.add(mainFrame.getCoinPanel(), BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);
	}

	public static boolean decoratedPlayerIsNotNull(PlayerDecorator decoratedPlayer, MainFrame mainFrame) {
		if (decoratedPlayer != null) {
			return true;
		} else {
			JOptionPane.showMessageDialog(mainFrame, "There is no player!", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

	}
}
