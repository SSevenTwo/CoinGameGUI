package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;

import javax.swing.JOptionPane;

import controller.util.ControllerUtilities;
import model.enumeration.BetType;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerDecorator;
import view.StatusBar;
import view.Toolbar;

public class PlaceBetBtnListener implements ActionListener {

	private MainFrame mainFrame;
	private Toolbar toolbar;
	private Collection<Player> playersWhoHaveBet;

	public PlaceBetBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.toolbar = mainFrame.getToolbar();
		this.playersWhoHaveBet = toolbar.getPlayersWhoHaveBet();
	}
	
	// Places a bet for a user IF they have not set a bet.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (ControllerUtilities.decoratedPlayerIsNotNull(decoratedPlayer,mainFrame)) {
			Player playerToBet = decoratedPlayer.getPlayer();
			if (playerHasNotBet(playerToBet)) {
				getBetFromUser(playerToBet);
			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player already has a bet placed.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		toolbar.updateButtonState();
	}
	
	// Ask the user for bet amount / bet types
	private void getBetFromUser(Player playerToBet) {
		String betType = getBetTypeDialog();
		if (betType == null) {return;}
		int betAmount = getBetAmountDialog();
		setBet(playerToBet, betAmount, betType);
		toolbar.updateButtonState();
	}
	
	private String getBetTypeDialog() {
		Object[] possibilities = { "Coin 1", "Coin 2", "Both" };
		return (String) JOptionPane.showInputDialog(mainFrame, "Please select a bet type: ",
				"Place bet", JOptionPane.PLAIN_MESSAGE, null, possibilities, null);
	}

	private BetType getBetType(String betType) {
		switch (betType) {
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
	
	private int getBetAmountDialog() {
		int betAmount = 0;
		try {
			betAmount = Integer.parseInt(JOptionPane.showInputDialog("Please enter the bet amount:"));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(mainFrame, "You must enter a number!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return betAmount;
	}
	
	// Set the bet for the player
	private void setBet(Player playerToBet, int betAmount, String betType) {
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
	}
	

	private boolean playerHasNotBet(Player player) {
		if (!playersWhoHaveBet.contains(player)) {
			return true;
		} else
			return false;
	}
}
