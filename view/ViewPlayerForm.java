package view;

import java.util.Collection;

import javax.swing.JButton;

import controller.ViewPlayerBtnListener;
import model.interfaces.Player;
import view.interfaces.GuiForm;

public class ViewPlayerForm extends GuiForm {
	private JButton viewPlayerBtn;

	public ViewPlayerForm(MainFrame mainFrame, CoinPanel coinPanel) {
		super(null, mainFrame.getGameEngine(), "View Player:");
		addActionListenerToViewBtn(mainFrame);
	}

	public ViewPlayerForm(Collection<Player> players, MainFrame mainFrame) {
		super(players, mainFrame.getGameEngine(), "View Player:");
		addActionListenerToViewBtn(mainFrame);
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

	private void addActionListenerToViewBtn(MainFrame mainFrame) {
		viewPlayerBtn.addActionListener(new ViewPlayerBtnListener(mainFrame, this));
	}

}