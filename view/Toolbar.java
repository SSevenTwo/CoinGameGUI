package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JToolBar;
import javax.swing.border.MatteBorder;

import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class Toolbar extends JToolBar {

	private MainFrame mainFrame;
	private GameEngine gameEngine;

	private JButton viewPlayerBtn;
	private JButton addPlayerBtn;
	private JButton removePlayerBtn;
	private JButton placeBetBtn;
	private JButton removeBetBtn;
	private JButton spinPlayerBtn;
	private JButton spinSpinnerBtn;
	private JLabel activePlayer;
	private JComboBox<PlayerWrapper> playerList;
	private Collection<Player> players;
	private GridBagConstraints gc;
	private boolean isSpinning;
	private Collection<Player> playersWhoHaveBet;

	public Toolbar(MainFrame mainFrame) {
		this(null, mainFrame);

	}

	public Toolbar(Collection<Player> players, MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.players = players;
		this.playerList = new JComboBox<PlayerWrapper>();
		this.addPlayerBtn = new JButton("Add Player");
		this.removePlayerBtn = new JButton("Remove Player");
		this.placeBetBtn = new JButton("Place Bet");
		this.removeBetBtn = new JButton("Remove Bet");
		this.spinPlayerBtn = new JButton("Spin Player");
		this.spinSpinnerBtn = new JButton("Spin Spinner");
		this.activePlayer = new JLabel("Current Active Player: ");
		this.playersWhoHaveBet = new ArrayList<Player>();

		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.weightx = 0.1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.gridy = 0;

		gc.fill = GridBagConstraints.HORIZONTAL;

		add(addPlayerBtn, gc);

		gc.gridx++;
		add(removePlayerBtn, gc);

		gc.gridx++;
		add(placeBetBtn, gc);

		gc.gridx++;
		add(removeBetBtn, gc);

		gc.gridx++;
		add(spinPlayerBtn, gc);

		gc.gridx++;
		add(spinSpinnerBtn, gc);

		gc.gridx++;
		add(activePlayer, gc);

		gc.gridx++;
		if (players != null) {
			setPlayers(players);
		} else {
			gc.weightx = 1;
			add(this.playerList, gc);
		}

		setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
		setFloatable(false);
	}

	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}

	public JButton getRemovePlayerBtn() {
		return removePlayerBtn;
	}

	public JButton getSpinPlayerBtn() {
		return spinPlayerBtn;
	}

	public JButton getViewPlayerBtn() {
		return viewPlayerBtn;
	}

	public JButton getSpinSpinnerBtn() {
		return spinSpinnerBtn;
	}

	public void setPlayers(Collection<Player> players) {
		this.players = players;
		refreshComboBox();
		gc.weightx = 1;
		add(playerList, gc);
		revalidate();
		repaint();
	}

	public void refreshComboBox() {
		// Setup or refresh combo box upon removing players
		remove(playerList);
		this.playerList = new JComboBox<PlayerWrapper>();
		addPlayersToComboBox();
	}

	private void addPlayersToComboBox() {
		DefaultComboBoxModel<PlayerWrapper> combo = new DefaultComboBoxModel<PlayerWrapper>();
		for (Player player : players) {
			PlayerWrapper playerWrapper = new PlayerWrapper(player);
			combo.addElement(playerWrapper);
		}
		this.playerList.setModel(combo);
	}

	private boolean playerListIsEmpty() {
		if (players.size() == 0 || players == null) {
			return true;
		} else
			return false;
	}

	public JComboBox<PlayerWrapper> getPlayerList() {
		return playerList;
	}

	public void updateButtonState() {
		Player player = null;
		PlayerWrapper playerWrapper = (PlayerWrapper) playerList.getSelectedItem();
		if (playerWrapper != null) {
			player = playerWrapper.getPlayer();
		}

		if (isSpinning) {
			spinPlayerBtn.setEnabled(false);
			spinSpinnerBtn.setEnabled(false);
			return;
		}
		if (player != null && player.getBetType().equals(BetType.NO_BET)) {
			spinPlayerBtn.setEnabled(false);
			spinSpinnerBtn.setEnabled(true);
		} 
		
//		if (isSpinningOrPlayerListIsEmpty()) {
//			spinPlayerBtn.setEnabled(false);
//			spinSpinnerBtn.setEnabled(false);
//		} 
		else {
			spinPlayerBtn.setEnabled(true);
			spinSpinnerBtn.setEnabled(true);
		}
	}

	private boolean isSpinningOrPlayerListIsEmpty() {
		if (isPlayerListEmptyOrIsSpinningOrIfAmountEmpty()) {
			return true;
		} else
			return false;
	}

	private boolean isPlayerListEmptyOrIsSpinningOrIfAmountEmpty() {
		if (getPlayers().size() == 0 || getPlayers() == null || this.isSpinning == true) {
			return true;
		} else
			return false;
	}

	public Collection<Player> getPlayers() {
		return players;
	}

	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}

	public JButton getPlaceBetBtn() {
		return this.placeBetBtn;
	}

	public JButton getRemoveBetBtn() {
		return this.removeBetBtn;
	}

	public Collection<Player> getPlayersWhoHaveBet() {
		return playersWhoHaveBet;
	}

}
