package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerWrapper;
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

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.getPlayerCoinPanel().setVisible(false);
		mainFrame.add(mainFrame.getCoinPanel(), BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);

		PlayerWrapper playerWrapperToRemove = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapperToRemove != null) {
			Player playerToRemove = playerWrapperToRemove.getPlayer();
			spinPlayer(playerToRemove.getPlayerId());
		}
	}

	public void spinPlayer(String playerId) {
		if (gameEngine.getPlayer(playerId) != null) {
			Player playerToSpin = gameEngine.getPlayer(playerId);
			if (mainFrame.playerHasNotAlreadySpun(playerToSpin)) {
				toolbar.setSpinning(true);
				toolbar.updateButtonState();
				mainFrame.getPlayersWhoHaveSpun().add(playerToSpin);

				new Thread() {
					public void run() {
						statusBar = mainFrame.getStatusBar();
						statusBar.updateCurrentView(playerToSpin.getPlayerName());
						statusBar.updateSystemStatus("Spinning Player...");
						statusBar.updateLastAction("Spin Player");
						gameEngine.spinPlayer(playerToSpin, 100, 1000, 100, 50, 500, 50);
						statusBar.updateSystemStatus("Idle");
						toolbar.setSpinning(false);
						toolbar.updateButtonState();

						spinSpinnerIfReady();
					}
				}.start();

			} else {
				JOptionPane.showMessageDialog(mainFrame, "Player has already spun!", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
		removePlayerIfNegativePoints();
	}

	public void spinSpinnerIfReady() {
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			spinSpinner();
			statusBar.updateSystemStatus("Idle");
		}
	}
	
	private void removePlayerIfNegativePoints() {
		for(Player player: gameEngine.getAllPlayers()) {
			if(player.getPoints()<=0) {
				gameEngine.removePlayer(player);
			}
		}
		updateSummaryPanelAndToolbar();
	}
	
	private void updateSummaryPanelAndToolbar() {
		mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
		mainFrame.getSummaryPanel().refreshSummary();
	}

}
