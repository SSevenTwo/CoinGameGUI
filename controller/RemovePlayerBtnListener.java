package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.RemovePlayerForm;
import view.StatusBar;

public class RemovePlayerBtnListener implements ActionListener {
	private RemovePlayerForm removePlayerForm;
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private StatusBar statusBar;

	public RemovePlayerBtnListener(RemovePlayerForm removePlayerForm, MainFrame mainFrame) {
		this.removePlayerForm = removePlayerForm;
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String removePlayer = (String) removePlayerForm.getPlayerList().getSelectedItem();
		String[] nameAndId = removePlayer.split(":");
		String playerId = nameAndId[1].trim();

		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to remove this player?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1

		if (action == JOptionPane.OK_OPTION) {
			gameEngine.removePlayer(new SimplePlayer(playerId, null, 0));
			removePlayerForm.setPlayers(gameEngine.getAllPlayers(), removePlayerForm.getRemovePlayerBtn());
		}
		
		statusBar = mainFrame.getStatusBar();
		statusBar.updateSystemStatus("Idle");
		statusBar.updateLastAction("Removed Player");
		new Thread() {
			public void run() {
				spinSpinnerIfReady();
			}
		}.start();
		mainFrame.getSummaryPanel().refreshSummary();

	}
	
	// In the rare case that 1 remaining player is in the game with their result set due
	// to removal of other players.
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
}
