package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.PlayerWrapper;
import view.StatusBar;
import view.Toolbar;

public class PlayerListListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private CoinPanel playerCoinPanel;
	private Toolbar toolbar;

	public PlayerListListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.playerCoinPanel = mainFrame.getPlayerCoinPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		toolbar.updateButtonState();
		mainFrame.add(playerCoinPanel, BorderLayout.CENTER);
		playerCoinPanel.setVisible(true);
		this.mainFrame.getCoinPanel().setVisible(false);

		PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapper != null) {
			Player playerToView = playerWrapper.getPlayer();
			playerCoinPanel.update(playerToView);
			playerCoinPanel.setVisible(true);
			mainFrame.revalidate();
			mainFrame.repaint();

			StatusBar statusBar = mainFrame.getStatusBar();
			statusBar.updateCurrentView(playerToView.getPlayerName());
			statusBar.updateLastAction("Viewed Player");
		}

	}

}
