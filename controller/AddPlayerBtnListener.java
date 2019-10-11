package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.StatusBar;

public class AddPlayerBtnListener implements ActionListener{
	
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public AddPlayerBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
	}
	
	// Asks user to input player ID, name and initial points to add a new player.
	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newPlayerId = JOptionPane.showInputDialog("Please enter the player ID:");
		String newPlayerName = JOptionPane.showInputDialog("Please enter the player name:");
		
		if(newPlayerId == null || newPlayerName == null){
			   return;
			}
		
		int initialPoints = getInitialPointsDialog();
		if(initialPoints == -1) {
			return;
		}
		gameEngine.addPlayer(new SimplePlayer(newPlayerId, newPlayerName, initialPoints));
		
		updateStatusBar();
		updateSummaryPanelAndToolbar();
	}
	
	private void updateStatusBar() {
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateSystemStatus("Idle");
		statusBar.updateLastAction("Added Player");
	}
	
	// Creates an input dialogue box to enter initial points
	private int getInitialPointsDialog() {
		int newPlayerPoints =-1;
		try {
			newPlayerPoints = Integer.parseInt(JOptionPane.showInputDialog("Please enter the initial points:"));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(mainFrame,
					"You must enter a number!", "Error",
					JOptionPane.ERROR_MESSAGE);
			newPlayerPoints = -1;
		};
		return newPlayerPoints;
	}
	
	private void updateSummaryPanelAndToolbar() {
		mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
		mainFrame.getSummaryPanel().refreshSummary();
	}

}
