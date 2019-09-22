package view;

import java.awt.GridBagConstraints;
import java.util.Collection;

import javax.swing.JButton;

import controller.ViewPlayerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GuiForm;

public class ViewPlayerForm extends GuiForm {
	private JButton viewPlayerBtn;
	private CoinPanel coinPanel;

	private GridBagConstraints gc;

	public ViewPlayerForm(GameEngine gameEngine, CoinPanel coinPanel) {
		super(null, gameEngine, "View Player:");
		this.coinPanel = coinPanel;
		addActionListenerToViewBtn();
	}

	public ViewPlayerForm(Collection<Player> players, GameEngine gameEngine, CoinPanel coinPanel) {
		super(players, gameEngine, "View Player:");
		this.coinPanel = coinPanel;
		addActionListenerToViewBtn();
	}

	public JButton getViewPlayerBtn() {
		return viewPlayerBtn;
	}

	@Override
	public JButton makeComponentsAndReturnButton() {
		this.viewPlayerBtn = new JButton("View");
		viewPlayerBtn.setEnabled(false);
		return viewPlayerBtn;
	}

	private void addActionListenerToViewBtn() {
		viewPlayerBtn.addActionListener(new ViewPlayerBtnListener(this, getGameEngine(), this.coinPanel));
	}

}