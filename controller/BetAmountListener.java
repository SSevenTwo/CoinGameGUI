package controller;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import view.SpinPlayerForm;

public class BetAmountListener implements DocumentListener{
	
	private SpinPlayerForm spinPlayerForm;

	public BetAmountListener(SpinPlayerForm spinPlayerForm) {
		this.spinPlayerForm = spinPlayerForm;
		
	}
	@Override
	public void changedUpdate(DocumentEvent e) {
		//	No action needed
	}

	@Override
	public void insertUpdate(DocumentEvent e) {
		spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		spinPlayerForm.updateButtonState(spinPlayerForm.getSpinPlayerBtn());
	}

}
