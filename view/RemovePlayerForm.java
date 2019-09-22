package view;

import java.awt.GridBagConstraints;
import java.util.Collection;

import javax.swing.JButton;

import controller.RemovePlayerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GuiForm;

public class RemovePlayerForm extends GuiForm {

	private JButton removePlayerBtn;

	private GridBagConstraints gc;

	public RemovePlayerForm(GameEngine gameEngine) {
		super(null, gameEngine, "Remove Player:");
	}

	public RemovePlayerForm(Collection<Player> players, GameEngine gameEngine) {
		super(players,gameEngine,"Remove Player:");
	}
	
	@Override
	public JButton makeComponentsAndReturnButton() {
		this.removePlayerBtn = new JButton("Remove Player");
		removePlayerBtn.setEnabled(false);
		removePlayerBtn.addActionListener(new RemovePlayerBtnListener(this,getGameEngine()));
		return removePlayerBtn;
	}

	public JButton getRemovePlayerBtn() {
		return removePlayerBtn;
	}



}
