package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.MatteBorder;

import model.enumeration.BetType;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class SummaryPanel extends JPanel {

	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private JPanel playerList;

	public SummaryPanel(MainFrame mainFrame) {

		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();

		setLayoutAndPanelBorder();
		setSize();
		playerList = new JPanel(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;

		playerList.add(new JPanel(), gbc);
		add(new JScrollPane(playerList));
		addPlayers(gameEngine.getAllPlayers());

	}

	public void refreshSummaryWithResults(CoinPair spinnerResults) {
		displayWinLossResults(spinnerResults);
	}

	public void refreshSummary() {
		updatePlayerList();
	}

	private void displayWinLossResults(CoinPair spinnerResult) {
		setUpPlayerList();
		addWinLossResults(gameEngine.getAllPlayers(), spinnerResult);
	}

	// Decorates the player boxes to red, green or blue depending if they win, loss or did not bet
	private void addWinLossResults(Collection<Player> players, CoinPair spinnerResult) {

		for (Player player : players) {
			JPanel panel = new JPanel();

			if(player.getBetType().equals(BetType.NO_BET)) {
				panel.add(new PlayerBox(player, 3));
				panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.BLUE));
				panel.setBackground(Color.BLUE);
			}
			else if (playerWinOrLose(player, spinnerResult)) {
				panel.add(new PlayerBox(player, 1));
				panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.GREEN));
				panel.setBackground(Color.GREEN);
			} else {
				panel.add(new PlayerBox(player, 2));
				panel.setBorder(new MatteBorder(1, 1, 1, 1, Color.RED));
				panel.setBackground(Color.RED);
			}
			GridBagConstraints gb = new GridBagConstraints();
			gb.gridwidth = GridBagConstraints.REMAINDER;
			gb.weightx = 1;
			gb.fill = GridBagConstraints.HORIZONTAL;
			playerList.add(panel, gb, 0);
			gb.gridy++;
			validate();
			repaint();
		}
		playAgain(spinnerResult);
	}

	private void addPlayers(Collection<Player> players) {
		makePlayerBoxes(players);
	}

	// Creates player boxes for the summary panel
	private void makePlayerBoxes(Collection<Player> players) {
		for (Player player : players) {
			JPanel panel = new JPanel();
			panel.add(new PlayerBox(player));
			panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
			GridBagConstraints gb = new GridBagConstraints();
			gb.gridwidth = GridBagConstraints.REMAINDER;
			gb.weightx = 1;
			gb.fill = GridBagConstraints.HORIZONTAL;
			playerList.add(panel, gb, 0);
			gb.gridy++;
			validate();
			repaint();
		}
	}

	private void updatePlayerList() {
		setUpPlayerList();
		if (gameEngine.getAllPlayers().size() == 0) {
			playerList.removeAll();
			revalidate();
			repaint();
		}
		addPlayers(gameEngine.getAllPlayers());
	}

	private void playAgain(CoinPair spinnerResult) {
		String message = String.format("Spinner results: %s \nSpin to play again!", spinnerResult.toString());
		JOptionPane.showMessageDialog(mainFrame, message, "Finished", JOptionPane.INFORMATION_MESSAGE);
		resetAllPlayers();
		mainFrame.getToolbar().updateButtonState();

	}

	// Resets player bets and removes their results.
	private void resetAllPlayers() {
		for (Player player : gameEngine.getAllPlayers()) {
			gameEngine.placeBet(player, 0, BetType.NO_BET);
			player.setResult(null);
		}
	}

	private void setLayoutAndPanelBorder() {
		setLayout(new BorderLayout());

		Border innerBorder = BorderFactory.createTitledBorder("Summary Panel");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

	private boolean playerWinOrLose(Player player, CoinPair spinnerResult) {
		BetType betType = player.getBetType();

		switch (betType) {
		case COIN1:
			if (player.getResult().getCoin1().equals(spinnerResult.getCoin1())) {
				return true;
			} else
				return false;
		case COIN2:
			if (player.getResult().getCoin2().equals(spinnerResult.getCoin2())) {
				return true;
			} else
				return false;
		case BOTH:
			if (player.getResult().equals(spinnerResult)) {
				return true;
			} else
				return false;
		default:
			return false;
		}
	}

	private void setUpPlayerList() {
		playerList.removeAll();
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.weightx = 1;
		gbc.weighty = 1;
		playerList.add(new JPanel(), gbc);
	}

	private void setSize() {
		Rectangle r = this.getBounds();
		setPreferredSize(new Dimension(280, (int) r.getHeight()));
	}

}
