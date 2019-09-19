package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.RemovePlayerForm;

public class RemovePlayerBtnListener implements ActionListener{
	private RemovePlayerForm removePlayerForm;
	private GameEngine gameEngine;

	public RemovePlayerBtnListener(RemovePlayerForm removePlayerForm, GameEngine gameEngine) {
		this.removePlayerForm = removePlayerForm;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String removePlayer = (String) removePlayerForm.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();

		gameEngine.removePlayer(new SimplePlayer(playerId, null, 0));
		removePlayerForm.setPlayers(gameEngine.getAllPlayers());
		}
}
