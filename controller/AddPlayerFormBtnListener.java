package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.SimplePlayer;
import model.interfaces.GameEngine;
import view.MainFrame;
import view.StatusBar;

public class AddPlayerFormBtnListener implements ActionListener{
	
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public AddPlayerFormBtnListener(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		this.gameEngine = mainFrame.getGameEngine();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String newPlayerId = JOptionPane.showInputDialog("Please enter the player ID:");
		String newPlayerName = JOptionPane.showInputDialog("Please enter the player name:");
		
		if(newPlayerId == null || newPlayerName == null){
			   return;
			}
		
		int newPlayerPoints = 0;
		
		try {
			newPlayerPoints = Integer.parseInt(JOptionPane.showInputDialog("Please enter the initial points:"));
		} catch (NumberFormatException e1) {
			JOptionPane.showMessageDialog(mainFrame,
					"You must enter a number!", "Error",
					JOptionPane.ERROR_MESSAGE);
		};
		
		gameEngine.addPlayer(new SimplePlayer(newPlayerId, newPlayerName, newPlayerPoints));
		
		StatusBar statusBar = mainFrame.getStatusBar();
		statusBar.updateSystemStatus("Idle");
		statusBar.updateLastAction("Added Player");
		
		mainFrame.getToolbar().setPlayers(gameEngine.getAllPlayers());
		mainFrame.getSummaryPanel().refreshSummary();
	}

}
