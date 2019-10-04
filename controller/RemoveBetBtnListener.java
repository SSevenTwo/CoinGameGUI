package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerWrapper;
import view.StatusBar;
import view.Toolbar;

public class RemoveBetBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private Toolbar toolbar;
	private StatusBar statusBar;

	public RemoveBetBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.statusBar = mainFrame.getStatusBar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapper != null) {
			Player playerToBet = playerWrapper.getPlayer();
			if (playerHasSetBet(playerToBet)) {
				playerToBet.resetBet();
				updateStatusBar();
				updateSummaryPanelAndToolbar();
				mainFrame.getPlayersWhoHaveSpun().add(playerToBet);
				new Thread() {
					public void run() {
						spinSpinnerIfReady();
					}
				}.start();
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player has no bet to remove.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		} else {
			JOptionPane.showMessageDialog(mainFrame, "No player to remove bets from!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	private boolean playerHasSetBet(Player playerToBet) {
		if (playerToBet.getBetType() != BetType.NO_BET) {
			return true;
		} else
			return false;
	}

	private void updateStatusBar() {
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateSystemStatus("Idle");
		statusBar.updateLastAction("Removed Bet");
	}

	private void updateSummaryPanelAndToolbar() {
		mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
		mainFrame.getSummaryPanel().refreshSummary();
	}

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
		removePlayerIfNegativePoints();
	}

	public void spinSpinnerIfReady() {
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			spinSpinner();
			statusBar.updateSystemStatus("Idle");
		}
	}

	private void removePlayerIfNegativePoints() {
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getPoints() <= 0) {
				gameEngine.removePlayer(player);
			}
		}
		updateSummaryPanelAndToolbar();
	}
}
