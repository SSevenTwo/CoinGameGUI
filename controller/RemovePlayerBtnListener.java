package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

	@Override
	public void actionPerformed(ActionEvent e) {

		PlayerDecorator decoratedPlayer = (PlayerDecorator) toolbar.getPlayerList().getSelectedItem();
		if (decoratedPlayer != null) {
			Player playerToRemove = decoratedPlayer.getPlayer();

			if (confirmRemovalOfPlayer() == JOptionPane.OK_OPTION) {
				gameEngine.removePlayer(playerToRemove);
				updateToolbar();
				Player playerWrapper = (Player) toolbar.getPlayerList().getSelectedItem();
				if (playerWrapper != null) {
					Player playerToView = decoratedPlayer.getPlayer();
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

	
	private void spinSpinnerInNewThreadIfReady() {
		new Thread() {
			public void run() {
				UtilityMethods.spinSpinnerIfReady(mainFrame);
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
