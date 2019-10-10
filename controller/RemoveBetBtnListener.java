package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerDecorator;
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
		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (decoratedPlayer != null) {
			Player playerToBet = decoratedPlayer.getPlayer();
			if (playerHasSetBet(playerToBet)) {
				playerToBet.resetBet();
				updateStatusBar();
				UtilityMethods.updateSummaryPanelAndToolbar(mainFrame);
				mainFrame.getPlayersWhoHaveSpun().add(playerToBet);
				new Thread() {
					public void run() {
						UtilityMethods.spinSpinnerIfReady(mainFrame);
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

}
