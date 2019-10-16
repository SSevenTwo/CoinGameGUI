package controller;

import java.awt.BorderLayout;
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

public class SpinPlayerBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private StatusBar statusBar;
	private Toolbar toolbar;
	
	public SpinPlayerBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.statusBar = mainFrame.getStatusBar();
	}

	// Spins the player if they have not already spun
	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getPlayerCoinPanel().setVisible(false);
		mainFrame.add(mainFrame.getCoinPanel(), BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);

		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (ControllerUtilities.decoratedPlayerIsNotNull(decoratedPlayer, mainFrame)) {
			Player playerToRemove = decoratedPlayer.getPlayer();
			spinPlayer(playerToRemove.getPlayerId());
		}
	}

	public void spinPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			Player playerToSpin = gameEngine.getPlayer(playerId);
			if (mainFrame.playerHasNotAlreadySpun(playerToSpin)) {
				updateToolBarSpinning(playerToSpin);
				mainFrame.getPlayersWhoHaveSpun().add(playerToSpin);
				spinPlayerInNewThread(playerToSpin);

			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player has already spun!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	private void spinPlayerInNewThread(Player playerToSpin) {
		new Thread() {
			public void run() {
				updateStatusBarToSpinning(playerToSpin);
				gameEngine.spinPlayer(playerToSpin, 100, 1000, 100, 50, 500, 50);
				updateStatusBarAndToolbarToIdle();
				ControllerUtilities.spinSpinnerIfReady(mainFrame);
			}
		}.start();
	}
	
	private void updateToolBarSpinning(Player playerToSpin) {
		toolbar.setSpinning(true);
		toolbar.setPlayerSpinning(playerToSpin);
		toolbar.updateButtonState();
	}
	
	private void updateStatusBarToSpinning(Player playerToSpin) {
		statusBar = mainFrame.getStatusBar();
		statusBar.updateCurrentView(playerToSpin.getPlayerName());
		statusBar.updateSystemStatus("Spinning Player...");
		statusBar.updateLastAction("Spin Player");
	}
	
	private void updateStatusBarAndToolbarToIdle() {
		statusBar.updateSystemStatus("Idle");
		toolbar.setPlayerSpinning(null);
		toolbar.setSpinning(false);
		toolbar.updateButtonState();
	}

}
