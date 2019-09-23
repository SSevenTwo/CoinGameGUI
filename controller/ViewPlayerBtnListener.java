package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.CoinPanel;
import view.MainFrame;
import view.StatusBar;
import view.ViewPlayerForm;

public class ViewPlayerBtnListener implements ActionListener{
	private MainFrame mainFrame;
	private ViewPlayerForm viewPlayerPanel;
	private GameEngine gameEngine;
	private CoinPanel playerCoinPanel;

	public ViewPlayerBtnListener(MainFrame mainFrame, ViewPlayerForm viewPlayerPanel) {
		this.mainFrame = mainFrame;
		this.viewPlayerPanel = viewPlayerPanel;
		this.gameEngine = mainFrame.getGameEngine();
		this.playerCoinPanel = mainFrame.getPlayerCoinPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.add(playerCoinPanel,BorderLayout.CENTER);
		playerCoinPanel.setVisible(true);
		this.mainFrame.getCoinPanel().setVisible(false);
		
		String removePlayer = (String) viewPlayerPanel.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();
		
		Player playerToView = this.viewPlayer(playerId);
		playerCoinPanel.update(playerToView);
		playerCoinPanel.setVisible(true);
		mainFrame.revalidate();
		mainFrame.repaint();
		
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateCurrentView(playerToView.getPlayerName());
		statusBar.updateLastAction("Viewed Player");
	}
	
	public Player viewPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			return gameEngine.getPlayer(playerId);
		}
		return null;
	}
		
}