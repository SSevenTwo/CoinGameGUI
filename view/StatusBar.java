package view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class StatusBar extends JPanel{
	
	private MainFrame mainFrame;
	private JLabel systemStatus;
	private JLabel currentView;
	private JLabel lastAction;
	
	public StatusBar(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		systemStatus = new JLabel("System: Idle");
		currentView = new JLabel("Current Player Viewing: No one");
		lastAction = new JLabel("Last Action: N/A");
		
		setLayout(new GridBagLayout());
		setBorder(new MatteBorder(1, 0, 1, 0, Color.GRAY));
		systemStatus.setBorder(new MatteBorder(0, 1, 0, 1, Color.GRAY));
		currentView.setBorder(new MatteBorder(0, 1, 0, 1, Color.GRAY));
		
		GridBagConstraints gc = new GridBagConstraints();
		
		gc.fill = GridBagConstraints.BOTH;
		gc.weightx = 1;
		gc.weighty = 1;
		
		gc.gridy = 0;
		gc.gridx = 0;
		
		add(currentView,gc);
		
		gc.gridx = 1;
		
		add(systemStatus,gc);
		
		gc.gridx = 2;
		
		add(lastAction,gc);
	}
	
	public void updateLastAction(String action) {
		lastAction.setText("Last Action: " + action);
	}
	
	public void updateSystemStatus(String status) {
		systemStatus.setText("System: " + status);
	}
	
	public void updateCurrentView(String playerName) {
		currentView.setText("Current Player Viewing: " + playerName);
	}

}
