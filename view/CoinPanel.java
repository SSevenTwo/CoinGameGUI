package view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import model.enumeration.CoinFace;
import model.interfaces.Coin;
import model.interfaces.Player;

@SuppressWarnings("serial")
public class CoinPanel extends JPanel {

	private BufferedImage coin1PreScale;
	private BufferedImage coin2PreScale;

	public CoinPanel() {
		coin1PreScale = null;
		coin2PreScale = null;
		try {
			coin1PreScale = ImageIO.read(new File("img/heads.png"));
			coin2PreScale = ImageIO.read(new File("img/tails.png"));
		} catch (IOException ioe) {
			JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}

	// Paints the coins into the panel taking into account the window size
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (coin1PreScale != null && coin2PreScale != null) {
			double scaleFactor = getNewScaleFactor(
					new Dimension(coin1PreScale.getWidth() * 2, coin1PreScale.getHeight()), getSize());

			int scaleWidth = (int) Math.round(coin1PreScale.getWidth() * scaleFactor);
			int scaleHeight = (int) Math.round(coin1PreScale.getHeight() * scaleFactor);

			Image coin1 = coin1PreScale.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);
			Image coin2 = coin2PreScale.getScaledInstance(scaleWidth, scaleHeight, Image.SCALE_SMOOTH);

			int x = 0;
			int y = (getHeight() - coin1.getHeight(this)) / 2;

			g.drawImage(coin1, x, y, this);

			x = ((coin1.getWidth(this) / 2) + (scaleWidth / 2));
			y = (getHeight() - coin1.getHeight(this)) / 2;
			g.drawImage(coin2, x, y, this);
		}
	}

	// Gets the scale ratio of the coin images and the new size (being the window)
	public double getScaleRatio(double originalSize, double newSize) {
		double ratio = 1;
		ratio = newSize / originalSize;
		return ratio;
	}

	// Gets the scale factor of the coin images and the new size (being the window)
	// Gets the smaller factor between the width and height to keep the aspect ratio
	// of the coins.
	public double getNewScaleFactor(Dimension originalSize, Dimension windowSize) {
		double factor = 1;
		if (originalSize != null && windowSize != null) {
			double newScaleWidth = getScaleRatio(originalSize.width, windowSize.width);
			double newScaleHeight = getScaleRatio(originalSize.height, windowSize.height);
			factor = Math.min(newScaleHeight, newScaleWidth);
		}
		return factor;
	}

	public void update(Coin coin, int coinNumber) {
		if (coinNumber == 1) {
			setCoin1Face(coin);
		} else {
			setCoin2Face(coin);
		}
	}

	public void update(Player player) {
		if (player.getResult() != null) {
			setPlayerCoin1(player);
			setPlayerCoin2(player);
		} else {
			hideAllCoins();
		}
	}

	private void hideAllCoins() {
		coin1PreScale = null;
		coin2PreScale = null;
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
			try {
				coin1PreScale = ImageIO.read(new File("img/heads.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				coin1PreScale = ImageIO.read(new File("img/tails.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void setCoin2Face(Coin coin) {
		if (coinFaceIsHeads(coin)) {
			try {
				coin2PreScale = ImageIO.read(new File("img/heads.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				coin2PreScale = ImageIO.read(new File("img/tails.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void setPlayerCoin1(Player player) {
		if (playerCoin1IsHeads(player)) {
			try {
				coin1PreScale = ImageIO.read(new File("img/heads.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				coin1PreScale = ImageIO.read(new File("img/tails.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void setPlayerCoin2(Player player) {
		if (playerCoin2IsHeads(player)) {
			try {
				coin2PreScale = ImageIO.read(new File("img/heads.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				coin2PreScale = ImageIO.read(new File("img/tails.png"));
			} catch (IOException ioe) {
				JOptionPane.showMessageDialog(this, "Can't find image file.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
