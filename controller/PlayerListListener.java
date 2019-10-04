package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.PlayerWrapper;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getCoinPanel().setVisible(false);

		PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapper != null) {
			Player playerToView = playerWrapper.getPlayer();
			playerCoinPanel.update(playerToView);
			mainFrame.add(playerCoinPanel,BorderLayout.CENTER);
			playerCoinPanel.setVisible(true);
			mainFrame.revalidate();
			mainFrame.repaint();

			StatusBar statusBar = mainFrame.getStatusBar();
			statusBar.updateCurrentView(playerToView.getPlayerName());
			statusBar.updateLastAction("Viewed Player");
		}

	}

}
