package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.interfaces.GameEngine;
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
		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want spin the spinner early?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1

		if (action == JOptionPane.OK_OPTION) {
			toolbar.setSpinning(true);
			toolbar.updateButtonState();
			new Thread() {
				public void run() {
					statusBar.updateCurrentView("Spinner!");
					statusBar.updateSystemStatus("Spinning spinner...");
					spinSpinner();
					statusBar.updateSystemStatus("Idle");
					toolbar.setSpinning(false);
					toolbar.updateButtonState();
				}
			}.start();
		}
	}

	private void spinSpinner() {
		JOptionPane.showMessageDialog(mainFrame, "Spinner will now spin!", "Spinner", JOptionPane.INFORMATION_MESSAGE);
		gameEngine.spinSpinner(100, 1000, 100, 50, 500, 50);
		mainFrame.getPlayersWhoHaveSpun().clear();
		mainFrame.getToolbar().getPlayersWhoHaveBet().clear();
	}

}
