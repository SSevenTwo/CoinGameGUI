package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.DocumentFilter;

import controller.AddPlayerBtnListener;
import model.interfaces.GameEngine;
import util.TextFilter;
import view.interfaces.GuiForm;

public class AddPlayerForm extends GuiForm {
	
	private JTextField playerId;
	private JTextField playerName;
	private JTextField initialPoints;
	private JButton addPlayerBtn;
	private MainFrame mainFrame;

	public AddPlayerForm(MainFrame mainFrame, GameEngine gameEngine) {
		super(null, gameEngine, "Add Player: ");
		setVisible(true);
		addActionListenerAndSetMainFrame(mainFrame);
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
	
	private void addActionListenerAndSetMainFrame(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		//Add action listeners
		addPlayerBtn.addActionListener(new AddPlayerBtnListener(mainFrame,this,getGameEngine()));
	}

	@Override
	public JButton makeComponentsAndReturnButton() {
		this.playerId = new JTextField(10);
		this.playerName = new JTextField(10);
		this.initialPoints = new JTextField(10);
		this.addPlayerBtn = new JButton("Add Player");
		
		DocumentFilter numberFilter = new TextFilter(mainFrame);
		((AbstractDocument)initialPoints.getDocument()).setDocumentFilter(numberFilter);
		
		return addPlayerBtn;
	}
	
	@Override 
	public void setUpGridBag(JButton button) {
		GridBagConstraints gc = new GridBagConstraints();

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
		add(button, gc);
	}

}
