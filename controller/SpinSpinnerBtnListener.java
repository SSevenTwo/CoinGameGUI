package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.MainFrame;
import view.StatusBar;
import view.Toolbar;

public class SpinSpinnerBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private GameEngine gameEngine;
	private StatusBar statusBar;
	private Toolbar toolbar;

	public SpinSpinnerBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
		this.toolbar = mainFrame.getToolbar();
		this.statusBar = mainFrame.getStatusBar();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (confirmReadyToSpin() == JOptionPane.OK_OPTION) {
			toolbar.setSpinning(true);
			toolbar.updateButtonState();
			spinSpinnerInNewThread();
		}
	}

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		mainFrame.add(mainFrame.getCoinPanel(),BorderLayout.CENTER);
		mainFrame.getCoinPanel().setVisible(true);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
	}

	private int confirmReadyToSpin() {
		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want spin the spinner early?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1
		return action;
	}

	private void spinSpinnerInNewThread() {
		new Thread() {
			public void run() {
				statusBar.updateCurrentView("Spinner!");
				statusBar.updateSystemStatus("Spinning spinner...");
				spinSpinner();
				removePlayerIfNegativePoints();
				statusBar.updateSystemStatus("Idle");
				toolbar.setSpinning(false);
				toolbar.updateButtonState();
			}
		}.start();
	}

	private void removePlayerIfNegativePoints() {
		for (Player player : gameEngine.getAllPlayers()) {
			if (player.getPoints() <= 0) {
				JOptionPane.showMessageDialog(mainFrame, String
						.format("Oh no! %s has lost all their points and have been eliminated!", player.getPlayerName()),
						"Player Lost", JOptionPane.INFORMATION_MESSAGE);
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
