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
	private MainFrame mainFrame;

	private GridBagConstraints gc;

	public RemovePlayerForm(MainFrame mainFrame) {
		super(null, mainFrame.getGameEngine(), "Remove Player:");
		this.mainFrame = mainFrame;
		addActionListener();
	}

	public RemovePlayerForm(Collection<Player> players, MainFrame mainFrame) {
		super(players,mainFrame.getGameEngine(),"Remove Player:");
		this.mainFrame = mainFrame;
		addActionListener();
	}
	
	@Override
	public JButton makeComponentsAndReturnButton() {
		this.removePlayerBtn = new JButton("Remove Player");
		removePlayerBtn.setEnabled(false);
		return removePlayerBtn;
	}
	
	private void addActionListener() {
		removePlayerBtn.addActionListener(new RemovePlayerBtnListener(this,mainFrame));
	}

	public JButton getRemovePlayerBtn() {
		return removePlayerBtn;
	}



}
