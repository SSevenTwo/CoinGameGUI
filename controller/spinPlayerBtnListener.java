package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.SpinPlayerForm;
import view.StatusBar;

public class SpinPlayerBtnListener implements ActionListener {

	private SpinPlayerForm spinPlayerForm;
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private JTextField betAmount;
	private JComboBox<String> betTypes;
	private StatusBar statusBar;

	public SpinPlayerBtnListener(SpinPlayerForm spinPlayerForm, MainFrame mainFrame, JComboBox<String> betTypes,
			JTextField betAmount) {
		this.spinPlayerForm = spinPlayerForm;
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.betAmount = betAmount;
		this.betTypes = betTypes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getPlayerCoinPanel().setVisible(false);
		mainFrame.add(mainFrame.getCoinPanel(), BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);

		String removePlayer = (String) spinPlayerForm.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();
		spinPlayer(playerId);
	}

	public void spinPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			Player playerToSpin = gameEngine.getPlayer(playerId);
			if (mainFrame.playerHasNotAlreadySpun(playerToSpin)) {
				playerToSpin.setBetType(getBetType());
				if (getBetType().equals(BetType.NO_BET) && getBetAmountAsInteger() == 0) {
					playerToSpin.setBet(0);
					spinPlayerForm.setSpinning(true);
				} else if (getBetAmountAsInteger() > 0 && playerToSpin.getPoints() >= getBetAmountAsInteger()
						&& !(getBetType().equals(BetType.NO_BET))) {
					playerToSpin.setBet(getBetAmountAsInteger());
					spinPlayerForm.setSpinning(true);
				} else {
					JOptionPane.showMessageDialog(mainFrame,
							"Bet Amount Invalid! \nHint: If no bet is selected, bet amount must be 0.", "Error",
							JOptionPane.ERROR_MESSAGE);
					spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
					return;
				}
				spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
				mainFrame.getPlayersWhoHaveSpun().add(playerToSpin);

				new Thread() {
					public void run() {
						statusBar = mainFrame.getStatusBar();
						statusBar.updateCurrentView(playerToSpin.getPlayerName());
						statusBar.updateSystemStatus("Spinning Player...");
						statusBar.updateLastAction("Spin Player");
						gameEngine.spinPlayer(playerToSpin, 100, 1000, 100, 50, 500, 50);
						statusBar.updateSystemStatus("Idle");
						spinPlayerForm.isSpinning(false);
						spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());

						spinSpinnerIfReady();
					}
				}.start();

			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player has already spun!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public int getBetAmountAsInteger() {
		int amount = 0;
		try {
			amount = Integer.parseInt(betAmount.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(spinPlayerForm, "Amount must be a number!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return amount;
	}

	public BetType getBetType() {
		String betType = (String) betTypes.getSelectedItem();
		switch (betType) {
		case "No Bet":
			return BetType.NO_BET;
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

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
	}

	public void spinSpinnerIfReady() {
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			spinSpinner();
			statusBar.updateSystemStatus("Idle");
		}
	}

}
