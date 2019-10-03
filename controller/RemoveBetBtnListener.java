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

	public RemoveBetBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapper != null) {
			Player playerToBet = playerWrapper.getPlayer();
			if(playerToBet.getBetType() != null) {
			playerToBet.resetBet();
			StatusBar statusBar = mainFrame.getStatusBar();
			statusBar.updateSystemStatus("Idle");
			statusBar.updateLastAction("Removed Bet");

			mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
			mainFrame.getSummaryPanel().refreshSummary();
			}
			else {
				JOptionPane.showMessageDialog(mainFrame, "Player has no bet to remove.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(mainFrame, "No player to remove bets from!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}
}
