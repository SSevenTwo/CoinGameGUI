package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.SpinPlayerForm;

public class spinPlayerBtnListener implements ActionListener{
	
	private SpinPlayerForm spinPlayerForm;
	private GameEngine gameEngine;
	
	public spinPlayerBtnListener(SpinPlayerForm spinPlayerForm, GameEngine gameEngine) {
		this.spinPlayerForm = spinPlayerForm;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		spinPlayerForm.setSpinning(true);
		spinPlayerForm.changeButtonState();
		String removePlayer = (String) spinPlayerForm.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();

		spinPlayer(playerId);
		
	}
	
	public void spinPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			Player playerToSpin = gameEngine.getPlayer(playerId);

			new Thread() {
				public void run() {
					gameEngine.spinPlayer(playerToSpin, 100, 1000, 100, 50, 500, 50);
					spinPlayerForm.isSpinning(false);
					spinPlayerForm.changeButtonState();
				}
			}.start();

		} else {
			System.out.println("Can't Spin.");
		}

	}

}
