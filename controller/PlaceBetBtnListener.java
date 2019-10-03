package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerWrapper;
import view.StatusBar;
import view.Toolbar;

public class PlaceBetBtnListener implements ActionListener {

	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private Toolbar toolbar;
	private Collection<Player> playersWhoHaveBet;
	

	public PlaceBetBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.playersWhoHaveBet = toolbar.getPlayersWhoHaveBet();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapper != null) {
			Player playerToBet = playerWrapper.getPlayer();
			if (!playersWhoHaveBet.contains(playerToBet)) {
				Object[] possibilities = { "Coin 1", "Coin 2", "Both" };
				String betType = (String) JOptionPane.showInputDialog(mainFrame, "Please select a bet type: ",
						"Place bet", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);

				if (betType == null) {
					return;
				}

				int betAmount = 0;
//				if(getBetType(betType).equals(BetType.NO_BET)){
//					mainFrame.getPlayersWhoHaveSpun().add(playerToBet);
//					playerToBet.setBetType(getBetType(betType));
//					playerToBet.setBet(0);
//					StatusBar statusBar = mainFrame.getStatusBar();
//					statusBar.updateSystemStatus("Idle");
//					statusBar.updateLastAction("Placed bet");
//
//					mainFrame.getSummaryPanel().refreshSummary();
//					toolbar.updateButtonState();
//					return;
//				}
				try {
					betAmount = Integer.parseInt(JOptionPane.showInputDialog("Please enter the bet amount:"));
				} catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(mainFrame, "You must enter a number!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

				if (playerToBet.setBet(betAmount)) {
					playerToBet.setBetType(getBetType(betType));
					StatusBar statusBar = mainFrame.getStatusBar();
					statusBar.updateSystemStatus("Idle");
					statusBar.updateLastAction("Placed bet");

					mainFrame.getSummaryPanel().refreshSummary();
					playersWhoHaveBet.add(playerToBet);
				} else {
					JOptionPane.showMessageDialog(mainFrame, "Invalid bet amount.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player already has a bet placed.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}

		} else {
			JOptionPane.showMessageDialog(mainFrame, "There is no player!", "Error", JOptionPane.ERROR_MESSAGE);
		}
		toolbar.updateButtonState();
	}

	public BetType getBetType(String betType) {
		switch (betType) {
//		case "No Bet":
//			return BetType.NO_BET;
		case "Coin 1":
			return BetType.COIN1;
		case "Coin 2":
			return BetType.COIN2;
		case "Both":
			return BetType.BOTH;
		default:
			return null;
		}

	}
}
