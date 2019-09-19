package view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JToolBar;


public class Toolbar extends JToolBar{
	
	private JButton viewPlayerBtn;
	private JButton addPlayerBtn;
	private JButton removePlayerBtn;
	private JButton spinPlayerBtn;
	private JComboBox<String> playerList;
	
	
	public Toolbar() {
		this.viewPlayerBtn = new JButton("View Player");
		this.addPlayerBtn = new JButton("Add Player");
		this.removePlayerBtn = new JButton("Remove Player");
		this.spinPlayerBtn = new JButton("Spin Player");
		
		add(this.viewPlayerBtn);
		add(this.addPlayerBtn);
		add(this.removePlayerBtn);
		add(this.spinPlayerBtn);
	}

	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}

	public JButton getRemovePlayerBtn() {
		return removePlayerBtn;
	}

	public JButton getSpinPlayerBtn() {
		return spinPlayerBtn;
	}

	public JButton getViewPlayerBtn() {
		return viewPlayerBtn;
	}

}
