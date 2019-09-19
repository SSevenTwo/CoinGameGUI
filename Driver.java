import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;

import client.SimpleTestClient;
import model.GameEngineImpl;
import model.SimplePlayer;
import model.enumeration.BetType;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.GameEngineCallbackImpl;
import view.GameEngineCallbackGUI;

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

		SwingUtilities.invokeLater(new Runnable() {

			public void run() {
				GameEngineCallbackGUI game = new GameEngineCallbackGUI(gameEngine);
				//GameEngineCallbackImpl game1 = new GameEngineCallbackImpl();
				gameEngine.addGameEngineCallback(game);
				//gameEngine.addGameEngineCallback(game1);

			}

		});

//
//		      // create some test players
//		      Player[] players = new Player[] { new SimplePlayer("1", "The Coin Master", 1000),
//		         new SimplePlayer("2", "The Loser", 750), new SimplePlayer("3", "The Dabbler", 500) };
//
//		      // add logging callback
//		      gameEngine.addGameEngineCallback(new GameEngineCallbackImpl());
//
//		      // main loop to add players and place a bet
//		      int enumOrdinal = 0;
//		      for (Player player : players)
//		      {
//		         gameEngine.addPlayer(player);
//		         // mod with BetType length so we always stay in range even if num players increases
//		         // NOTE: we are passing a different BetType each time!
//		         gameEngine.placeBet(player, 100, BetType.values()[enumOrdinal++ % BetType
//		            .values().length]);
//		         try {
//		        	 gameEngine.spinPlayer(player, 100, 1000, 100, 50, 500, 50);
//		         } catch (Exception e) {
//		        	 System.out.println(e.getMessage());
//		         }
//		      }
//
//		      logger.log(Level.INFO, "SPINNING ...");
//		      // OutputTrace.pdf was generated with these parameter values (using only first 3 params as per spec)
//		      try {
//		    	  gameEngine.spinSpinner(100, 1000, 200, 50, 500, 25);
//		      } catch (Exception e) {
//		    	  System.out.println(e.getMessage());
//		      }
//
//		      // TODO reset bets for next round if you were playing again
//		      for(Player player:players) {
//		    	  player.resetBet();
//		      }
	}

}

//Note to self:
// - May have to make listeners in control class. Eg. AddButtonListener extends ActionListener pass in mainframe
// - May have to change the toolbar/forms to have mainframe passed in too