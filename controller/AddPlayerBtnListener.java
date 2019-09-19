package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.AddPlayerForm;

public class AddPlayerBtnListener implements ActionListener {
	
	private AddPlayerForm addPlayerForm;
	private GameEngine gameEngine;

	public AddPlayerBtnListener(AddPlayerForm addPlayerForm, GameEngine gameEngine) {
		this.addPlayerForm = addPlayerForm;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String newPlayerId = addPlayerForm.getPlayerId().getText();
		String newPlayerName = addPlayerForm.getPlayerName().getText();
		int newPlayerPoints = Integer.parseInt(addPlayerForm.getInitialPoints().getText());
		
		gameEngine.addPlayer(new SimplePlayer(newPlayerId, newPlayerName, newPlayerPoints));
		
		System.out.println("Person Added.");
		for (Player player : gameEngine.getAllPlayers()) {
			System.out.println(player.getPlayerName());
		}

	}

}
