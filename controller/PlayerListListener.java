package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.PlayerDecorator;
import view.StatusBar;
import view.Toolbar;

public class PlayerListListener implements ActionListener {
	private MainFrame mainFrame;
	private CoinPanel playerCoinPanel;
	private Toolbar toolbar;

	public PlayerListListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.toolbar = mainFrame.getToolbar();
		this.playerCoinPanel = mainFrame.getPlayerCoinPanel();
	}

	// DIsplay the players coin panel with their last results
	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getCoinPanel().setVisible(false);

		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (decoratedPlayer != null) {
			Player playerToView = decoratedPlayer.getPlayer();
			// Shows the spinning player if they are spinning
			if (checkIfPlayerIsSpinning(playerToView)) {
			} else {
				updatePlayerCoinPanel(playerToView);
			}
			updateMainFrameAndToolbar();
			updateStatusBar(playerToView);
		}

	}

	private boolean checkIfPlayerIsSpinning(Player playerToView) {
		if (toolbar.isSpinning()) {
			if (toolbar.getPlayerSpinning() != null) {
				Player playerSpinning = toolbar.getPlayerSpinning();
				if (playerToView.getPlayerId().equals(playerSpinning.getPlayerId())) {
					playerCoinPanel.setVisible(false);
					mainFrame.add(mainFrame.getCoinPanel(), BorderLayout.CENTER);
					mainFrame.getCoinPanel().setVisible(true);
					return true;
				}
			}
		}
		return false;
	}

	private void updatePlayerCoinPanel(Player playerToView) {
		playerCoinPanel.update(playerToView);
		mainFrame.add(playerCoinPanel, BorderLayout.CENTER);
		playerCoinPanel.setVisible(true);
	}

	private void updateMainFrameAndToolbar() {
		mainFrame.getToolbar().updateButtonState();
		mainFrame.revalidate();
		mainFrame.repaint();
	}

	private void updateStatusBar(Player playerToView) {
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateCurrentView(playerToView.getPlayerName());
		statusBar.updateLastAction("Viewed Player");
	}

}
