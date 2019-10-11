package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.util.ControllerUtilities;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerDecorator;
import view.StatusBar;
import view.Toolbar;

public class RemoveBetBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private Toolbar toolbar;

	public RemoveBetBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.toolbar = mainFrame.getToolbar();
	}

	// Removes the players current bet if they have one.
	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (ControllerUtilities.decoratedPlayerIsNotNull(decoratedPlayer, mainFrame)) {
			Player playerToBet = decoratedPlayer.getPlayer();
			if (playerHasSetBet(playerToBet)) {
				playerToBet.resetBet();
				updateStatusBar();
				ControllerUtilities.updateSummaryPanelAndToolbar(mainFrame);
				mainFrame.getPlayersWhoHaveSpun().add(playerToBet);
				new Thread() {
					public void run() {
						ControllerUtilities.spinSpinnerIfReady(mainFrame);
					}
				}.start();
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player has no bet to remove.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
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
