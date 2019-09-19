package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinPanel;
import view.ViewPlayerForm;

public class ViewPlayerBtnListener implements ActionListener{
	private ViewPlayerForm viewPlayerPanel;
	private GameEngine gameEngine;
	private CoinPanel coinPanel;

	public ViewPlayerBtnListener(ViewPlayerForm viewPlayerPanel, GameEngine gameEngine, CoinPanel coinPanel) {
		this.viewPlayerPanel = viewPlayerPanel;
		this.gameEngine = gameEngine;
		this.coinPanel = coinPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String removePlayer = (String) viewPlayerPanel.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();

		Player playerToView = this.viewPlayer(playerId);
		coinPanel.update(playerToView);
	}
	
	public Player viewPlayer(String playerId) {
		System.out.println(gameEngine.getPlayer(playerId));
		if (gameEngine.getPlayer(playerId) != null) {
			return gameEngine.getPlayer(playerId);
		}
		return null;
	}
		
}