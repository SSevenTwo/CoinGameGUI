package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.Player;

public class CoinPanel extends JPanel {

	private JLabel coin1;
	private JLabel coin2;
	private GridBagConstraints gc;
	private ImageIcon heads;
	private ImageIcon tails;
	private MainFrame mainFrame;

	public CoinPanel(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		makeComponents();
		setUpGridBag();
	}

	public void update(Coin coin, int coinNumber) {
		displayAllCoins();
		if (coinNumber == 1) {
			setCoin1Face(coin);
		} else {
			setCoin2Face(coin);
		}
		revalidate();
		repaint();
	}

	public void update(Player player) {
		if (player.getResult() != null) {
			displayAllCoins();
			setPlayerCoin1(player);
			setPlayerCoin2(player);
		} else {
			hideAllCoins();
//			JOptionPane.showMessageDialog(mainFrame, "Player has not spun yet. No coins to display.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		revalidate();
		repaint();
	}

	private void hideAllCoins() {
		coin1.setVisible(false);
		coin2.setVisible(false);
	}

	private void displayAllCoins() {
		coin1.setVisible(true);
		coin2.setVisible(true);
	}

	private void makeComponents() {
		this.coin1 = new JLabel();
		this.coin2 = new JLabel();
		this.heads = new ImageIcon("img/heads.png");
		this.tails = new ImageIcon("img/tails.png");
		coin1.setIcon(heads);
		coin2.setIcon(tails);
	}

	private void setUpGridBag() {
		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 0;
		gc.gridx = 0;
		add(coin1);
		gc.gridy = 0;
		gc.gridx = 1;
		add(coin2);
	}

	private boolean coinFaceIsHeads(Coin coin) {
		if (coin.getFace().equals(CoinFace.HEADS)) {
			return true;
		} else
			return false;
	}

	private boolean playerCoin1IsHeads(Player player) {
		if (player.getResult().getCoin1().getFace().equals(CoinFace.HEADS)) {
			return true;
		} else
			return false;
	}

	private boolean playerCoin2IsHeads(Player player) {
		if (player.getResult().getCoin2().getFace().equals(CoinFace.HEADS)) {
			return true;
		} else
			return false;
	}

	private void setCoin1Face(Coin coin) {
		if (coinFaceIsHeads(coin)) {
			coin1.setIcon(heads);
		} else {
			coin1.setIcon(tails);
		}
	}

	private void setCoin2Face(Coin coin) {
		if (coinFaceIsHeads(coin)) {
			coin2.setIcon(heads);
		} else {
			coin2.setIcon(tails);
		}
	}

	private void setPlayerCoin1(Player player) {
		if (playerCoin1IsHeads(player)) {
			coin1.setIcon(heads);
		} else {
			coin1.setIcon(tails);
		}
	}

	private void setPlayerCoin2(Player player) {
		if (playerCoin2IsHeads(player)) {
			coin2.setIcon(heads);
		} else {
			coin2.setIcon(tails);
		}
	}
}
