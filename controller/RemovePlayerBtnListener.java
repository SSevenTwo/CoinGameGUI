package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.util.ControllerUtilities;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerDecorator;
import view.StatusBar;
import view.Toolbar;

public class RemovePlayerBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private StatusBar statusBar;
	private Toolbar toolbar;

	public RemovePlayerBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.statusBar = mainFrame.getStatusBar();
	}

	// Removes a player
	@Override
	public void actionPerformed(ActionEvent e) {

		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (ControllerUtilities.decoratedPlayerIsNotNull(decoratedPlayer, mainFrame)) {
			Player playerToRemove = decoratedPlayer.getPlayer();

			if (confirmRemovalOfPlayer() == JOptionPane.OK_OPTION) {
				removePlayer(playerToRemove);
				updatePlayerStatusBarToNextPlayer();
			}

			statusBar.updateSystemStatus("Idle");
			statusBar.updateLastAction("Removed Player");

			spinSpinnerInNewThreadIfReady();

			mainFrame.getSummaryPanel().refreshSummary();
			updateStatusBarIfLastPlayer();
		}
	}
	
	// Updates the status bar to show the player after the removed player
	private void updatePlayerStatusBarToNextPlayer() {
		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (decoratedPlayer != null) {
			Player playerToView = decoratedPlayer.getPlayer();
			statusBar.updateCurrentView(playerToView.getPlayerName());
		}
	}
	
	private void removePlayer(Player playerToRemove) {
		gameEngine.removePlayer(playerToRemove);
		updateToolbar();
	}

	private void spinSpinnerInNewThreadIfReady() {
		new Thread() {
			public void run() {
				ControllerUtilities.spinSpinnerIfReady(mainFrame);
			}
		}.start();
	}

	private void updateToolbar() {
		toolbar.setPlayers(gameEngine.getAllPlayers());
		toolbar.getPlayerList().addActionListener(new PlayerListListener(mainFrame));
	}

	private int confirmRemovalOfPlayer() {
		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to remove this player?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1
		return action;
	}

	private void updateStatusBarIfLastPlayer() {
		if (gameEngine.getAllPlayers().size() == 0) {
			statusBar.updateCurrentView("No one");
		}
	}

}
