package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.PlayerWrapper;
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

	@Override
	public void actionPerformed(ActionEvent e) {

		PlayerWrapper playerWrapperToRemove = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
		if (playerWrapperToRemove != null) {
			Player playerToRemove = playerWrapperToRemove.getPlayer();

			if (confirmRemovalOfPlayer() == JOptionPane.OK_OPTION) {
				gameEngine.removePlayer(playerToRemove);
				updateToolbar();
				PlayerWrapper playerWrapper = (PlayerWrapper) toolbar.getPlayerList().getSelectedItem();
				if (playerWrapper != null) {
					Player playerToView = playerWrapper.getPlayer();
					statusBar.updateCurrentView(playerToView.getPlayerName());
				}
			}

			statusBar.updateSystemStatus("Idle");
			statusBar.updateLastAction("Removed Player");
			
			spinSpinnerInNewThreadIfReady();
			
			mainFrame.getSummaryPanel().refreshSummary();
			updateStatusBarIfLastPlayer();
		} else {
			JOptionPane.showMessageDialog(mainFrame, "There is no player to remove.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	// In the rare case that 1 remaining player is in the game with their result set
	// due to removal of other players.
	public void spinSpinnerIfReady() {
		if (mainFrame.readyToSpinSpinner()) {
			statusBar.updateCurrentView("Spinner!");
			statusBar.updateSystemStatus("Spinning spinner...");
			mainFrame.getPlayerCoinPanel().setVisible(false);
			spinSpinner();
			statusBar.updateSystemStatus("Idle");
		}
	}

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
	}
	
	private void spinSpinnerInNewThreadIfReady() {
		new Thread() {
			public void run() {
				spinSpinnerIfReady();
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
		if(gameEngine.getAllPlayers().size()==0) {
			statusBar.updateCurrentView("No one");
		}
	}

}
