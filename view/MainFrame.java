package view;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.AddPlayerFormBtnListener;
import controller.RemovePlayerFormBtnListener;
import controller.SpinPlayerFormBtnListener;
import controller.ViewPlayerFormBtnListener;
import model.interfaces.GameEngine;

public class MainFrame extends JFrame {

	private GameEngine gameEngine;
	private Toolbar toolbar;
	private ViewPlayerForm viewPlayerForm;
	private AddPlayerForm addPlayerForm;
	private RemovePlayerForm removePlayerForm;
	private SpinPlayerForm spinPlayerForm;
	private CoinPanel coinPanel;

	public MainFrame(GameEngine gameEngine) {
		super("Coin Game");
		setLayout(new BorderLayout());

		// Connecting to controller
		this.gameEngine = gameEngine;

		// Making components
		createComponents();

		// Add listeners
		addToolbarListeners();

		// Adding to layout
		add(toolbar, BorderLayout.NORTH);
		add(coinPanel, BorderLayout.CENTER);
		add(addPlayerForm, BorderLayout.WEST);

		// Set Minimum Size
		setMinimumSize(new Dimension(600, 300));
		setSize(1200, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public void hideForms() {
		addPlayerForm.setVisible(false);
		removePlayerForm.setVisible(false);
		spinPlayerForm.setVisible(false);
		viewPlayerForm.setVisible(false);
	}

	private void createComponents() {
		toolbar = new Toolbar();
		coinPanel = new CoinPanel();
		viewPlayerForm = new ViewPlayerForm(gameEngine, coinPanel);
		addPlayerForm = new AddPlayerForm(gameEngine);
		if (PlayerListIsNotEmpty()) {
			removePlayerForm = new RemovePlayerForm(gameEngine.getAllPlayers(), gameEngine);
			spinPlayerForm = new SpinPlayerForm(gameEngine.getAllPlayers(), gameEngine);
		} else {
			removePlayerForm = new RemovePlayerForm(gameEngine);
			spinPlayerForm = new SpinPlayerForm(gameEngine);
		}
	}

	private void addToolbarListeners() {
		toolbar.getAddPlayerBtn().addActionListener(new AddPlayerFormBtnListener(this));
		toolbar.getRemovePlayerBtn().addActionListener(new RemovePlayerFormBtnListener(this, gameEngine));
		toolbar.getSpinPlayerBtn().addActionListener(new SpinPlayerFormBtnListener(this, gameEngine));
		toolbar.getViewPlayerBtn().addActionListener(new ViewPlayerFormBtnListener(this, gameEngine));
	}
	
	private boolean PlayerListIsNotEmpty() {
		if(gameEngine.getAllPlayers().size() != 0 || gameEngine.getAllPlayers() != null) {
			return true;
		}
		else return false;
	}

	public CoinPanel getCoinPanel() {
		return coinPanel;
	}

	public SpinPlayerForm getSpinPlayerForm() {
		return spinPlayerForm;
	}

	public ViewPlayerForm getViewPlayerPanel() {
		return viewPlayerForm;
	}

	public AddPlayerForm getAddPlayerForm() {
		return addPlayerForm;
	}

	public RemovePlayerForm getRemovePlayerForm() {
		return removePlayerForm;
	}

}
