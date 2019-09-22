package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import model.interfaces.GameEngine;
import view.MainFrame;
import view.ViewPlayerForm;

public class ViewPlayerFormBtnListener implements ActionListener{
	
	private MainFrame mainFrame;
	private GameEngine gameEngine;

	public ViewPlayerFormBtnListener(MainFrame mainFrame, GameEngine gameEngine) {
		this.mainFrame = mainFrame;
		this.gameEngine = gameEngine;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		mainFrame.hideForms();
		ViewPlayerForm viewPlayerForm = mainFrame.getViewPlayerForm();
		viewPlayerForm.setPlayers(gameEngine.getAllPlayers(),viewPlayerForm.getViewPlayerBtn());
		mainFrame.add(mainFrame.getViewPlayerForm(), BorderLayout.WEST);
		mainFrame.getViewPlayerForm().setVisible(true);

	}

}
