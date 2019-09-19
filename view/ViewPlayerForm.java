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

import controller.ViewPlayerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class ViewPlayerForm extends JPanel {
	private JComboBox<String> playerList;
	private JButton viewPlayerBtn;
	private Collection<Player> players;
	private GameEngine gameEngine;
	private CoinPanel coinPanel;

	private GridBagConstraints gc;

	public ViewPlayerForm(GameEngine gameEngine, CoinPanel coinPanel) {
		this(null, gameEngine, coinPanel);
	}

	public ViewPlayerForm(Collection<Player> players, GameEngine gameEngine, CoinPanel coinPanel) {
		setLayout(new GridBagLayout());
		setVisible(false);
		this.gameEngine = gameEngine;
		this.coinPanel = coinPanel;

		// Setting a border
		Border innerBorder = BorderFactory.createTitledBorder("View Player:");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

		// Setting size
		Rectangle r = this.getBounds();
		setPreferredSize(new Dimension(200, (int) r.getHeight()));

		// Make components
		this.players = players;
		this.viewPlayerBtn = new JButton("View");
		this.playerList = new JComboBox<String>();
		viewPlayerBtn.setEnabled(false);

//		// Add action listeners
		viewPlayerBtn.addActionListener(new ViewPlayerBtnListener(this,gameEngine,coinPanel));

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
		add(viewPlayerBtn, gc);
	}

//	public void setListener(FormListener listener) {
//		this.listener = listener;
//	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
		refreshComboBox();
		gc.weighty = .01;
		gc.gridy = 1;
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
		this.changeButtonState();
	}

	public void changeButtonState() {
		// Determines
		if (players.size() == 0 || players == null) {
			viewPlayerBtn.setEnabled(false);
		} else {
			viewPlayerBtn.setEnabled(true);
		}
	}

	public JButton getViewPlayerBtn() {
		return viewPlayerBtn;
	}

	public JComboBox<String> getPlayerList() {
		return playerList;
	}

}