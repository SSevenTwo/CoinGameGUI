package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import controller.util.ControllerUtilities;
import view.MainFrame;
import view.StatusBar;
import view.Toolbar;

public class SpinSpinnerBtnListener implements ActionListener {
	private MainFrame mainFrame;
	private StatusBar statusBar;
	private Toolbar toolbar;

	public SpinSpinnerBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.toolbar = mainFrame.getToolbar();
		this.statusBar = mainFrame.getStatusBar();
	}

	// Spins the spinner early
	@Override
	public void actionPerformed(ActionEvent e) {
		if (confirmReadyToSpin() == JOptionPane.OK_OPTION) {
			updateToolBarToSpinning();
			spinSpinnerInNewThread();
		}
	}
	
	private void updateToolBarToSpinning() {
		toolbar.setSpinning(true);
		toolbar.updateButtonState();
	}

	private int confirmReadyToSpin() {
		int action = JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want spin the spinner early?",
				"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1
		return action;
	}

	private void spinSpinnerInNewThread() {
		new Thread() {
			public void run() {
				updateStatusBarToSpinning();
				ControllerUtilities.spinSpinner(mainFrame);
				ControllerUtilities.removePlayerIfNegativePoints(mainFrame);
				updateStatusBarAndToolbarToIdle();
			}
		}.start();
	}
	
	private void updateStatusBarToSpinning() {
		statusBar.updateCurrentView("Spinner!");
		statusBar.updateSystemStatus("Spinning spinner...");
	}
	
	private void updateStatusBarAndToolbarToIdle() {
		statusBar.updateSystemStatus("Idle");
		toolbar.setSpinning(false);
		toolbar.updateButtonState();
	}

}
