package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SpinPlayerForm;

public class SpinPlayerBtnListener implements ActionListener {

	private SpinPlayerForm spinPlayerForm;
	private GameEngine gameEngine;
	private JTextField betAmount;
	private JComboBox<String> betTypes;

	public SpinPlayerBtnListener(SpinPlayerForm spinPlayerForm, GameEngine gameEngine, JComboBox<String> betTypes, JTextField betAmount) {
		this.spinPlayerForm = spinPlayerForm;
		this.gameEngine = gameEngine;
		this.betAmount = betAmount;
		this.betTypes = betTypes;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (getBetAmountAsInteger() != 0) {
			spinPlayerForm.setSpinning(true);
			spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
			String removePlayer = (String) spinPlayerForm.getPlayerList().getSelectedItem();
			String[] nameAndId = removePlayer.split(":");
			String playerId = nameAndId[1].trim();
			spinPlayer(playerId);
		}

	}

	public void spinPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			Player playerToSpin = gameEngine.getPlayer(playerId);
			playerToSpin.setBet(getBetAmountAsInteger());
			playerToSpin.setBetType(getBetType());

			new Thread() {
				public void run() {
					gameEngine.spinPlayer(playerToSpin, 100, 1000, 100, 50, 500, 50);
					spinPlayerForm.isSpinning(false);
					spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
				}
			}.start();

		} else {
			System.out.println("Can't Spin.");
		}

	}
	
	public int getBetAmountAsInteger() {
		int amount = 0;
		try {
			amount = Integer.parseInt(betAmount.getText());
		}catch(Exception e) {
			JOptionPane.showMessageDialog(spinPlayerForm, "Amount must be a number!", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		return amount;
	}
	
	public BetType getBetType() {
		String betType = (String) betTypes.getSelectedItem();
		switch(betType) {
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
