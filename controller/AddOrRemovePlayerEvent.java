package controller;

import java.util.EventObject;

import model.interfaces.Player;

public class AddOrRemovePlayerEvent extends EventObject {
	
	private String name;
	private String id;
	private int points;
	private Player player;

	public AddOrRemovePlayerEvent(Object source) {
		super(source);
	}
	
	public AddOrRemovePlayerEvent(Object source, String playerId, String playerName, int initialPoints) {
		super(source);
		this.id = playerId;
		this.name = playerName;
		this.points = initialPoints;
	}
	
	public AddOrRemovePlayerEvent(Object source, Player player) {
		super(source);
		this.player = player;
	}

	public String getName() {
		return name;
	}

	public String getId() {
		return id;
	}

	public int getPoints() {
		return points;
	}
	
	public Player getPlayer() {
		return player;
	}

}
