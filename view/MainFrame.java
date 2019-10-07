package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JFrame;

import controller.AddPlayerBtnListener;
import controller.PlaceBetBtnListener;
import controller.PlayerListListener;
import controller.RemoveBetBtnListener;
import controller.RemovePlayerBtnListener;
import controller.SpinPlayerBtnListener;
import controller.SpinSpinnerBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private GameEngine gameEngine;
	private GameEngineCallbackGUI gameEngineCallBackGUI;
	private Toolbar toolbar;
	private CoinPanel coinPanel;
	private CoinPanel playerCoinPanel;
	private SummaryPanel summaryPanel;
	private StatusBar statusBar;
	private Collection<Player> playersWhoHaveSpun;

	public MainFrame(GameEngine gameEngine) {
		super("Coin Game");
		gameEngineCallBackGUI = new GameEngineCallbackGUI(this);
		gameEngine.addGameEngineCallback(gameEngineCallBackGUI);
		setLayout(new BorderLayout());
		setModel(gameEngine);
		createComponents();
		addToolbarListeners();
		setMenuBar();
		addComponentsToLayout();
		setUpLayoutSettings();
	}
	
	private void addToolbarListeners() {
		toolbar.getAddPlayerBtn().addActionListener(new AddPlayerBtnListener(this));
		toolbar.getRemovePlayerBtn().addActionListener(new RemovePlayerBtnListener(this));
		toolbar.getPlaceBetBtn().addActionListener(new PlaceBetBtnListener(this));
		toolbar.getRemoveBetBtn().addActionListener(new RemoveBetBtnListener(this));
		toolbar.getSpinPlayerBtn().addActionListener(new SpinPlayerBtnListener(this));
		toolbar.getPlayerList().addActionListener(new PlayerListListener(this));
		toolbar.getSpinSpinnerBtn().addActionListener(new SpinSpinnerBtnListener(this));
	}

	private void createComponents() {
		coinPanel = new CoinPanel();
		playerCoinPanel = new CoinPanel();
		playerCoinPanel.setVisible(false);
		playersWhoHaveSpun = new ArrayList<Player>();
		createToolbar();

		summaryPanel = new SummaryPanel(this);
		statusBar = new StatusBar();
	}

	private boolean PlayerListIsNotEmpty() {
		if (gameEngine.getAllPlayers().size() != 0 || gameEngine.getAllPlayers() != null) {
			return true;
		} else
			return false;
	}

	private void addComponentsToLayout() {
		add(toolbar, BorderLayout.NORTH);
		add(coinPanel, BorderLayout.CENTER);
		add(summaryPanel, BorderLayout.WEST);
		add(statusBar, BorderLayout.SOUTH);
	}

	private void setUpLayoutSettings() {
		setMinimumSize(new Dimension(670, 300));
		setSize(1150, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setModel(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	private void createToolbar() {
		if (PlayerListIsNotEmpty()) {
			toolbar = new Toolbar(gameEngine.getAllPlayers(),this);
			toolbar.updateButtonState();
		} else {
			toolbar = new Toolbar(this);
			toolbar.updateButtonState();
		}
	}
	public CoinPanel getCoinPanel() {
		return coinPanel;
	}


	public SummaryPanel getSummaryPanel() {
		return summaryPanel;
	}

	public GameEngine getGameEngine() {
		return gameEngine;
	}

	public CoinPanel getPlayerCoinPanel() {
		return playerCoinPanel;
	}

	public StatusBar getStatusBar() {
		return statusBar;
	}

	public Toolbar getToolbar() {
		return toolbar;
	}

	public Collection<Player> getPlayersWhoHaveSpun() {
		return playersWhoHaveSpun;
	}

	public boolean playerHasNotAlreadySpun(Player player) {
		if (!playersWhoHaveSpun.contains(player)) {
			return true;
		} else
			return false;
	}

	public boolean readyToSpinSpinner() {
		if (playersWhoHaveSpun.containsAll(gameEngine.getAllPlayers()) && !gameEngine.getAllPlayers().isEmpty()) {
			return true;
		} else
			return false;
	}

	private void setMenuBar() {
		setJMenuBar(new MenuBar(this));
	}

}
