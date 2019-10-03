package view;

import model.interfaces.Player;

public class PlayerWrapper {
	
	private Player player;
	
	public PlayerWrapper(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public String toString() {
		return player.getPlayerName();
	}

}
