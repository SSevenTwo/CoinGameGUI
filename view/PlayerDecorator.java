package view;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;

public class PlayerDecorator implements Player {

	private Player player;

	PlayerDecorator(Player player) {
		this.player = player;
	}
	
	public Player getPlayer() {
		return player;
	}
	
	@Override
	public String toString() {
		return player.getPlayerName();
	}

	@Override
	public String getPlayerName() {
		return player.getPlayerName();
	}

	@Override
	public void setPlayerName(String playerName) {
		player.setPlayerName(playerName);

	}

	@Override
	public int getPoints() {
		return player.getPoints();
	}

	@Override
	public void setPoints(int points) {
		player.setPoints(points);
	}

	@Override
	public String getPlayerId() {
		return player.getPlayerId();
	}

	@Override
	public boolean setBet(int bet) {
		return player.setBet(bet);
	}

	@Override
	public int getBet() {
		return player.getBet();
	}

	@Override
	public void setBetType(BetType betType) {
		player.setBetType(betType);
	}

	@Override
	public BetType getBetType() {
		return player.getBetType();
	}

	@Override
	public void resetBet() {
		player.resetBet();
	}

	@Override
	public CoinPair getResult() {
		return player.getResult();
	}

	@Override
	public void setResult(CoinPair coinPair) {
		player.setResult(coinPair);
	}

}
