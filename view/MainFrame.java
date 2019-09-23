package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;

import controller.AddPlayerFormBtnListener;
import controller.RemovePlayerFormBtnListener;
import controller.SpinPlayerFormBtnListener;
import controller.ViewPlayerFormBtnListener;
import model.interfaces.GameEngine;
import model.interfaces.Player;

public class MainFrame extends JFrame {

	private GameEngine gameEngine;
	private Toolbar toolbar;
	private ViewPlayerForm viewPlayerForm;
	private AddPlayerForm addPlayerForm;
	private RemovePlayerForm removePlayerForm;
	private SpinPlayerForm spinPlayerForm;
	private CoinPanel coinPanel;
	private CoinPanel playerCoinPanel;
	private SummaryPanel summaryPanel;
	private StatusBar statusBar;
	private Collection<Player> playersWhoHaveSpun;

	public MainFrame(GameEngine gameEngine) {
		super("Coin Game");
		setLayout(new BorderLayout());
		setModel(gameEngine);
		createComponents();
		setMenuBar();
		addToolbarListeners();
		addComponentsToLayout();
		setUpLayoutSettings();
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
		playerCoinPanel = new CoinPanel();
		playerCoinPanel.setVisible(false);
		viewPlayerForm = new ViewPlayerForm(this, coinPanel);
		addPlayerForm = new AddPlayerForm(this, gameEngine);
		playersWhoHaveSpun = new ArrayList<Player>();

		if (PlayerListIsNotEmpty()) {
			createFormsWithPlayers();
		} else {
			createPlayerlessForms();
		}

		summaryPanel = new SummaryPanel(this);
		statusBar = new StatusBar(this);
	}

	private void addToolbarListeners() {
		toolbar.getAddPlayerBtn().addActionListener(new AddPlayerFormBtnListener(this));
		toolbar.getRemovePlayerBtn().addActionListener(new RemovePlayerFormBtnListener(this, gameEngine));
		toolbar.getSpinPlayerBtn().addActionListener(new SpinPlayerFormBtnListener(this, gameEngine));
		toolbar.getViewPlayerBtn().addActionListener(new ViewPlayerFormBtnListener(this, gameEngine));
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
		add(addPlayerForm, BorderLayout.WEST);
		add(summaryPanel, BorderLayout.EAST);
		add(statusBar, BorderLayout.SOUTH);
	}

	private void setUpLayoutSettings() {
		setMinimumSize(new Dimension(600, 300));
		setSize(1400, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void setModel(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}

	private void createPlayerlessForms() {
		removePlayerForm = new RemovePlayerForm(this);
		spinPlayerForm = new SpinPlayerForm(this);
	}

	private void createFormsWithPlayers() {
		removePlayerForm = new RemovePlayerForm(gameEngine.getAllPlayers(), this);
		spinPlayerForm = new SpinPlayerForm(gameEngine.getAllPlayers(), this);
	}

	public CoinPanel getCoinPanel() {
		return coinPanel;
	}

	public SpinPlayerForm getSpinPlayerForm() {
		return spinPlayerForm;
	}

	public ViewPlayerForm getViewPlayerForm() {
		return viewPlayerForm;
	}

	public AddPlayerForm getAddPlayerForm() {
		return addPlayerForm;
	}

	public RemovePlayerForm getRemovePlayerForm() {
		return removePlayerForm;
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
		setJMenuBar(createMenuBar());
	}

	private JMenuBar createMenuBar() {
		JMenuBar menu = new JMenuBar();

		// Set up menu item
		JMenu file = new JMenu("File");
		JMenuItem exitItem = new JMenuItem("Exit");
		file.addSeparator();
		file.add(exitItem);

		JMenu window = new JMenu("Window");
		JMenu show = new JMenu("Show");
		JMenu help = new JMenu("Help");
		JMenuItem aboutItem = new JMenuItem("About Program");
		JCheckBoxMenuItem displayForms = new JCheckBoxMenuItem("Display Forms");
		displayForms.setSelected(true);
		help.add(aboutItem);

		show.add(displayForms);
		window.add(show);

		menu.add(file);
		menu.add(window);
		menu.add(help);

		// Adding mnemonics (These are shortcuts))
		// Will always be ALT + some key.
		file.setMnemonic(KeyEvent.VK_F); // KeyEvent actually returns an int
		exitItem.setMnemonic(KeyEvent.VK_X);

		// Action Listener for CheckBox
		displayForms.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBoxMenuItem button = (JCheckBoxMenuItem) e.getSource();
				addPlayerForm.setVisible(button.isSelected());
				viewPlayerForm.setVisible(button.isSelected());
				removePlayerForm.setVisible(button.isSelected());
				spinPlayerForm.setVisible(button.isSelected());
			}
		});

		aboutItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(MainFrame.this, "Created by Ian Nguyen \nID: S3788210", "About",
						JOptionPane.INFORMATION_MESSAGE);
			}

		});

		exitItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				// This creates a confirmation box. I pass MainFrame.this, because if it is just
				// this,
				// it will pass the anonymous class where I want it to be at the MainFrame.
				int action = JOptionPane.showConfirmDialog(MainFrame.this, "Are you sure you want to exit?",
						"Confirm Exit", JOptionPane.YES_NO_OPTION); // This method returns an int where yes = 0, no = 1

				if (action == JOptionPane.OK_OPTION) {
					System.exit(0);
				}

			}
		});

		// Adding accelerators (These are hot keys)
		// Accelerators have to get a keystroke object with 2.
		exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));

		return menu;
	}

}
