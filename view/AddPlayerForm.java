package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;

import controller.AddPlayerBtnListener;
import model.interfaces.GameEngine;

public class AddPlayerForm extends JPanel {
	
	private JTextField playerId;
	private JTextField playerName;
	private JTextField initialPoints;
	private JButton addPlayerBtn;
//	private FormListener listener;
	private GameEngine gameEngine;

	public AddPlayerForm(GameEngine gameEngine) {
		setLayout(new GridBagLayout());
		this.gameEngine = gameEngine;

		// Setting a border
		Border innerBorder = BorderFactory.createTitledBorder("Add Player");
		Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
		
		Rectangle r = this.getBounds();
		setPreferredSize(new Dimension(200, (int) r.getHeight()));
		
		//Make components
		this.playerId = new JTextField(10);
		this.playerName = new JTextField(10);
		this.initialPoints = new JTextField(10);
		this.addPlayerBtn = new JButton("Add Player");
		
		//Add action listeners
		addPlayerBtn.addActionListener(new AddPlayerBtnListener(this,gameEngine));
		
		GridBagConstraints gc = new GridBagConstraints();

		// Note the properties set carry to the next cells, but we just re-state it to
		// make it clear.

		/////////////// First row///////////////

		// Set the ratio of x and y cells
		gc.weightx = 1;
		gc.weighty = 0.1; 

		gc.gridx = 0;
		gc.gridy = 0; 
		
		gc.fill = GridBagConstraints.NONE; 
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Player ID:"), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(playerId, gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 0.1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		gc.insets = new Insets(0, 0, 0, 0);
		add(new JLabel("Name: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(playerName, gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = .1;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_END;
		add(new JLabel("Points: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(initialPoints, gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(addPlayerBtn, gc);
	}

	public JTextField getPlayerId() {
		return playerId;
	}

	public JTextField getPlayerName() {
		return playerName;
	}

	public JTextField getInitialPoints() {
		return initialPoints;
	}
	


}
