package view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.Collection;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import controller.BetAmountListener;
import controller.SpinPlayerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GuiForm;

public class SpinPlayerForm extends GuiForm {
	private JButton spinPlayerBtn;
	private JComboBox<String> betTypes;
	private JTextField betAmount;

	private boolean isSpinning;

	public SpinPlayerForm(GameEngine gameEngine) {
		super(null, gameEngine, "Spin Player: ");
	}

	public SpinPlayerForm(Collection<Player> players, GameEngine gameEngine) {
		super(players, gameEngine, "Spin Player: ");
	}

	@Override
	public void updateButtonState(JButton button) {
		// Enables or disables the button depending on whether the system is spinning or not.
		if(isSpinning) {
			betAmount.setEnabled(false);
		}
		if (isSpinningOrPlayerListIsEmpty()) {
			button.setEnabled(false);
		} else {
			betAmount.setEnabled(true);
			button.setEnabled(true);
		}
	}

	public JButton getSpinPlayerBtn() {
		return spinPlayerBtn;
	}

	public void isSpinning(Boolean spinning) {
		this.isSpinning = spinning;
	}

	public void setSpinning(boolean isSpinning) {
		this.isSpinning = isSpinning;
	}
	
	public boolean checkIfAmountEmpty() {
		if(betAmount.getText().equals("")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	@Override
	public JButton makeComponentsAndReturnButton() {
		spinPlayerBtn = new JButton("Spin Player");
		spinPlayerBtn.setEnabled(false);
		setUpBetTypesComboBox();
		betAmount = new JTextField(10);
		betAmount.getDocument().addDocumentListener(new BetAmountListener(this));

//		// Add action listeners
		SpinPlayerBtnListener listener = new SpinPlayerBtnListener(this,getGameEngine(),betTypes,betAmount);
		spinPlayerBtn.addActionListener(listener);
		return spinPlayerBtn;
	}
	
	@Override 
	public void setUpGridBag(JButton button) {
		setGc(new GridBagConstraints());
		GridBagConstraints gc = getGc();

		/////////////// First row///////////////

		// Set the ratio of x and y cells
		gc.weightx = 1;
		gc.weighty = 0.01; 

		gc.gridy = 0; 
		gc.gridx = 0;
		
		gc.fill = GridBagConstraints.NONE; 
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		gc.insets = new Insets(10, 0, 0, 0);
		add(new JLabel("Player: "), gc);

		gc.gridx = 1;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(getPlayerList(), gc);

		/////////////// Next row///////////////
		gc.gridy = 2;
		gc.weightx = 1;
		gc.weighty = 0.01;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(new JLabel("Bet Type: "), gc);
		
		/////////////// Next row///////////////
		gc.gridy++;
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(betTypes, gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.weightx = 0.01;
		gc.weighty = 0.01;
		
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(new JLabel("Bet Amount:"), gc);
		
		/////////////// Next row///////////////
		gc.gridy++;
		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(betAmount, gc);

		/////////////// Next row///////////////
		gc.gridy++;
		gc.weightx = 1;
		gc.weighty = 1;

		gc.gridx = 0;
		gc.anchor = GridBagConstraints.FIRST_LINE_START;
		add(button, gc);
	}
	
	private void setUpBetTypesComboBox() {
		DefaultComboBoxModel<String> combo = new DefaultComboBoxModel<String>();
		combo.addElement("Coin 1");
		combo.addElement("Coin 2");
		combo.addElement("Both");
		betTypes = new JComboBox<String>();
		betTypes.setModel(combo);
	}
	
	private boolean isSpinningOrPlayerListIsEmpty() {
		if(getPlayers().size() == 0 || getPlayers() == null || this.isSpinning == true
				|| checkIfAmountEmpty() == true) {
			return true;
		}
		else return false;
	}

}
