package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.Player;
@SuppressWarnings("serial")
public class PlayerBox extends JPanel {
	private String id;
	private String name;
	private int points;
	private BetType betType;
	private int bet;
	private CoinPair result;
	private int playerWin;
	private String colour;
	private String status;

	public PlayerBox(Player player) {
		this(player, 0);
	}

	public PlayerBox(Player player, int playerWin) {
		setLayout(new GridBagLayout());
		setInstanceVariables(player, playerWin);
		determineWinOrLoss();
		setPreferredSize(new Dimension(230, 100));
		setSize(getPreferredSize());
		setUpGridBag();
	}

	private void determineWinOrLoss() {
		if (playerWin == 1) {
			status = "Win!";
			colour = "green";
		}
		if (playerWin == 2) {
			status = "Lose!";
			colour = "red";
		}
		if (playerWin == 0) {
			status = "";
			colour = "";
		}
		if (playerWin == 3) {
			status = "Did not bet!";
			colour = "blue";
		}
	}
	
	private void setInstanceVariables(Player player, int playerWin) {
		this.id = player.getPlayerId();
		this.name = player.getPlayerName();
		this.points = player.getPoints();
		this.betType = player.getBetType();
		this.bet = player.getBet();
		this.result = player.getResult();
		this.playerWin = playerWin; // 0 is default, 1 is win, 2 is loss
		this.colour = "";
		this.status = "";
	}
	
	private void setUpGridBag() {
		GridBagConstraints gc = new GridBagConstraints();

		gc.weightx = 1;
		gc.weighty = 1;
		
		/////////////// First row///////////////
		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.BOTH;

		gc.gridy = 0;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(new JLabel("<html>" + name + " : " + id + "<font color=" + colour + "> " + status + "</font></html>"), gc);

		/////////////// Next row///////////////
		gc.gridy++;

		gc.gridx = 0;
		add(new JLabel("Points:"), gc);

		gc.gridx = 1;
		add(new JLabel("" + points), gc);

		/////////////// Next row///////////////
		gc.gridy++;

		gc.gridx = 0;
		add(new JLabel("Bet Type:"), gc);

		gc.gridx = 1;
		if (betType == null) {
			add(new JLabel("-"), gc);
		} else {
			add(new JLabel("" + betType), gc);
		}

		/////////////// Next row///////////////
		gc.gridy++;

		gc.gridx = 0;
		add(new JLabel("Bet: "), gc);

		gc.gridx = 1;
		add(new JLabel("" + bet), gc);

		/////////////// Next row///////////////
		gc.gridy++;

		gc.gridx = 0;
		add(new JLabel("Results: "), gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.gridx = 0;
		if (result != null) {
			add(new JLabel("" + result), gc);
		} else {
			add(new JLabel("Player has not spun."), gc);
		}
	}

}
