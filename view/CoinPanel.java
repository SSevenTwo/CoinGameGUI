package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.Player;

public class CoinPanel extends JPanel {

	private JLabel coin1Heads;
	private JLabel coin1Tails;
	private JLabel coin2Heads;
	private JLabel coin2Tails;
	private GridBagConstraints gc;

	private int spinCount;

	public CoinPanel() {
		this.spinCount = 1;
		this.coin1Heads = new JLabel();
		this.coin1Tails = new JLabel();
		this.coin2Heads = new JLabel();
		this.coin2Tails = new JLabel();

		ImageIcon heads = new ImageIcon("img/heads.png");
		ImageIcon tails = new ImageIcon("img/tails.png");
		coin1Heads.setIcon(heads);
		coin1Tails.setIcon(tails);
		coin2Heads.setIcon(heads);
		coin2Tails.setIcon(tails);

		setLayout(new GridBagLayout());
		gc = new GridBagConstraints();
		gc.fill = GridBagConstraints.NONE;
		gc.gridy = 0;
		gc.gridx = 0;
		add(coin1Heads);
		gc.gridy = 0;
		gc.gridx = 1;
		add(coin2Heads);
	}

	public void update(Coin coin1) {
		if (this.isCoinOne(this.spinCount) == true) {
			this.hideCoinOne();
			this.removeCoinOne();
			if (coin1.getFace().equals(CoinFace.HEADS)) {
				gc.gridy = 0;
				gc.gridx = 0;
				add(coin1Heads);
				coin1Heads.setVisible(true);
			} else {
				this.hideCoinOne();
				this.removeCoinOne();
				gc.gridy = 0;
				gc.gridx = 0;
				coin1Tails.setVisible(true);
				add(coin1Tails);
			}
		} else {
			if (coin1.getFace().equals(CoinFace.HEADS)) {
				this.hideCoinTwo();
				this.removeCoinTwo();
				gc.gridy = 0;
				gc.gridx = 1;
				add(coin2Heads);
				coin2Heads.setVisible(true);
			} else {
				this.hideCoinTwo();
				this.removeCoinTwo();
				gc.gridy = 0;
				gc.gridx = 1;
				coin2Tails.setVisible(true);
				add(coin2Tails);
			}
		}
		this.spinCount++;
		this.resetSpinCount();
		revalidate();
		repaint();
	}

	public void update(Player player) {
		this.removeAllCoins();
		this.hideCoins();
		if (player.getResult() != null) {
			if (player.getResult().getCoin1().getFace().equals(CoinFace.HEADS)) {
				gc.gridy = 0;
				gc.gridx = 0;
				add(coin1Heads);
				coin1Heads.setVisible(true);
			} else {
				gc.gridy = 0;
				gc.gridx = 0;
				coin1Tails.setVisible(true);
				add(coin1Tails);
			}

			if (player.getResult().getCoin2().getFace().equals(CoinFace.HEADS)) {
				gc.gridy = 0;
				gc.gridx = 1;
				add(coin2Heads);
				coin2Heads.setVisible(true);
			} else {
				gc.gridy = 0;
				gc.gridx = 1;
				coin2Tails.setVisible(true);
				add(coin2Tails);
			}
		}
		revalidate();
		repaint();
	}

	public void hideCoins() {
		coin1Heads.setVisible(false);
		coin1Tails.setVisible(false);
		coin2Heads.setVisible(false);
		coin2Tails.setVisible(false);
	}

	public void hideCoinOne() {
		coin1Heads.setVisible(false);
		coin1Tails.setVisible(false);
	}

	public void hideCoinTwo() {
		coin2Heads.setVisible(false);
		coin2Tails.setVisible(false);
	}

	public void removeAllCoins() {
		remove(coin1Heads);
		remove(coin1Tails);
		remove(coin2Heads);
		remove(coin2Tails);
	}

	public void removeCoinOne() {
		remove(coin1Heads);
		remove(coin1Tails);
	}

	public void removeCoinTwo() {
		remove(coin2Heads);
		remove(coin2Tails);
	}

	public boolean isCoinOne(int spinCount) {
		if (spinCount % 2 != 0) {
			return true;
		}
		return false;
	}

	private void resetSpinCount() {
		if (this.spinCount == 9) {
			this.spinCount = 1;
		}
	}
}
