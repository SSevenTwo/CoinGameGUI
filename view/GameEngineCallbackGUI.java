package view;

import javax.swing.SwingUtilities;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback {

	private MainFrame mainFrame;

	public GameEngineCallbackGUI(GameEngine gameEngine) {
		this.mainFrame = new MainFrame(gameEngine);
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CoinPanel coinPanel = mainFrame.getCoinPanel();
				coinPanel.update(coin, coin.getNumber());
			}
		});

	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				CoinPanel coinPanel = mainFrame.getCoinPanel();
				coinPanel.update(coin, coin.getNumber());
			}
		});
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		System.out.println(String.format("%s , final result: %s", player.getPlayerName(), coinPair));
	//	SwingUtilities.invokeLater(new Runnable() {
	//		public void run() {
				mainFrame.getSummaryPanel().refreshSummary();
	//		}
	//	});
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		System.out.println(String.format("Spinner, final result: %s", coinPair));
		String message = "";
		for (Player player : engine.getAllPlayers()) {
			message += player + "\n";
		}
		System.out.println(String.format("Final Player Results\n%s", message));
		//SwingUtilities.invokeLater(new Runnable() {
		//	public void run() {
				mainFrame.getSummaryPanel().refreshSummaryWithResults(coinPair);
		//	}
		//});

	}

}
