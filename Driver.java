import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import client.SimpleTestClient;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;
import view.MainFrame;

public class Driver {

	private static final Logger logger = Logger.getLogger(SimpleTestClient.class.getName());

	public static void main(String[] args) {

		// Configure logger to show FINE levels and above.
		try {
			FileInputStream fis = new FileInputStream("p.properties");
			LogManager.getLogManager().readConfiguration(fis);
			logger.setLevel(Level.FINE);
			logger.addHandler(new java.util.logging.ConsoleHandler());
			logger.setUseParentHandlers(false);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		final GameEngine gameEngine = new GameEngineImpl();

		// create some test players
		Player[] players = new Player[] { new SimplePlayer("1", "The Coin Master", 1000),
				new SimplePlayer("2", "The Loser", 750), new SimplePlayer("3", "The Dabbler", 500) };

		// Add players
		for (Player player : players) {
			gameEngine.addPlayer(player);
		}

		GameEngineCallbackImpl game1 = new GameEngineCallbackImpl();
				
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new MainFrame(gameEngine);
			}
		});
		
		gameEngine.addGameEngineCallback(game1);
	}

}
