package view;

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

import controller.spinPlayerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class SpinPlayerForm extends JPanel {
	private JComboBox<String> playerList;
	private JButton spinPlayerBtn;
	private Collection<Player> players;
	private GameEngine gameEngine;
	private boolean isSpinning;

	private GridBagConstraints gc;

	public SpinPlayerForm(GameEngine gameEngine) {
		this(null, gameEngine);
	}

	public SpinPlayerForm(Collection<Player> players, GameEngine gameEngine) {
		setLayout(new GridBagLayout());
		setVisible(false);
		this.gameEngine = gameEngine;

		// Setting a border
		Border innerBorder = BorderFactory.createTitledBorder("Spin Player:");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// Setting size
		Rectangle r = this.getBounds();
		setPreferredSize(new Dimension(200, (int) r.getHeight()));

		// Make components
		this.players = players;
		this.spinPlayerBtn = new JButton("Spin Player");
		this.playerList = new JComboBox<String>();
		this.spinPlayerBtn.setEnabled(false);

//		// Add action listeners
		spinPlayerBtn.addActionListener(new spinPlayerBtnListener(this,gameEngine));

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
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(spinPlayerBtn, gc);
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
		refreshComboBox();
		gc.weightx = 1;
		gc.weighty = .01;
		gc.gridy = 2;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(playerList, gc);
		this.changeButtonState();
		revalidate();
		repaint();
	}

	public void refreshComboBox() {
		// Setup or refresh combo box upon removing players

		remove(playerList);
		this.playerList = new JComboBox<String>();
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<String>();
		for (Player player : players) {
			System.out.println(player);
			combo.addElement(String.format("%s : %s", player.getPlayerName(), player.getPlayerId()));
			this.playerList.setModel(combo);
		}

	}

	public void changeButtonState() {
		// Enables or disables the button depending on whether the system is spinning or not.
		if (players.size() == 0 || players == null || this.isSpinning == true) {
			spinPlayerBtn.setEnabled(false);
		} else {
			spinPlayerBtn.setEnabled(true);
		}
	}

	public JButton getRemovePlayerBtn() {
		return spinPlayerBtn;
	}

	public JComboBox<String> getPlayerList() {
		return playerList;
	}

	public void isSpinning(Boolean spinning) {
		this.isSpinning = spinning;
	}

	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}
	
	

}
