package view.interfaces;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;
import java.util.Collection;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.interfaces.GameEngine;
import model.interfaces.Player;

public abstract class GuiForm extends JPanel {

	private JComboBox<String> playerList;
	private Collection<Player> players;
	private GameEngine gameEngine;
	private GridBagConstraints gc;

	public GuiForm(GameEngine gameEngine, String label) {
		this(null, gameEngine, label);
	}

	public GuiForm(Collection<Player> players, GameEngine gameEngine, String label) {
		this.gameEngine = gameEngine;
		this.players = players;

		setLayout(new GridBagLayout());
		setVisible(false);
		makeBorder(label);
		setSize();
		setUpPlayerList();
		setUpGridBag(makeComponentsAndReturnButton());
	}

	public abstract JButton makeComponentsAndReturnButton();

	public void setPlayers(Collection<Player> players, JButton button) {
		this.players = players;
		refreshComboBox(button);
		applyPlayerListSettings();
		add(playerList, gc);
		updateButtonState(button);
		revalidate();
		repaint();
	}

	public void refreshComboBox(JButton button) {
		// Setup or refresh combo box upon removing players
		remove(playerList);
		this.playerList = new JComboBox<String>();
		addPlayersToComboBox();
		this.updateButtonState(button);
	}

	public void updateButtonState(JButton button) {
		if (playerListIsEmpty()) {
			button.setEnabled(false);
		} else {
			button.setEnabled(true);
		}
	}

	private void addPlayersToComboBox() {
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<String>();
		for (Player player : players) {
			System.out.println(player);
			combo.addElement(String.format("%s : %s", player.getPlayerName(), player.getPlayerId()));
			this.playerList.setModel(combo);
		}
	}

	private void applyPlayerListSettings() {
		gc.weighty = .01;
		gc.gridy = 1;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
	}

	private boolean playerListIsEmpty() {
		if (players.size() == 0 || players == null) {
			return true;
		} else
			return false;
	}

	private void makeBorder(String label) {
		Border innerBorder = BorderFactory.createTitledBorder(label);
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
	}

	private void setSize() {
		Rectangle r = this.getBounds();
		setPreferredSize(new Dimension(200, (int) r.getHeight()));
	}

	private void setUpPlayerList() {

		this.playerList = new JComboBox<String>();
	}

	public JComboBox<String> getPlayerList() {
		return playerList;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}
	
	public Collection<Player> getPlayers() {
		return players;
	}
	
	public GridBagConstraints getGc() {
		return gc;
	}
	
	public void setGc(GridBagConstraints gc) {
		this.gc = gc;
	}

	protected void setUpGridBag(JButton button) {
		gc = new GridBagConstraints();

		/////////////// First row///////////////

		// Set the ratio of x and y cells
		gc.weightx = 1;
		gc.weighty = .01;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.NONE;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Player: "), gc);

		gc.gridy = 0;
		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(playerList, gc);

		/////////////// Next row///////////////
		gc.gridy = 3;
		gc.weightx = 1;
		gc.weighty = 3;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(button, gc);
	}

}
