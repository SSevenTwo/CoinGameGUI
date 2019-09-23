package view;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.util.Utilities;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	private MainFrame mainFrame;
	
	public GameEngineCallbackGUI(GameEngine gameEngine) {
		this.mainFrame = new MainFrame(gameEngine);
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		System.out.println(String.format("%s coin %d flipped to %s", player.getPlayerName(),coin.getNumber()
				,Utilities.titleConvert(coin.getFace())));
		CoinPanel coinPanel = mainFrame.getCoinPanel();
		coinPanel.update(coin, coin.getNumber());
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		System.out.println(String.format("Spinner coin %d flipped to %s",coin.getNumber(),
				Utilities.titleConvert(coin.getFace())));
		CoinPanel coinPanel = mainFrame.getCoinPanel();
		coinPanel.update(coin, coin.getNumber());
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		System.out.println(String.format("%s , final result: %s" ,player.getPlayerName(),coinPair));
		mainFrame.getSummaryPanel().refreshSummary();
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		System.out.println(String.format("Spinner, final result: %s", coinPair));
		String message = "";
		for(Player player: engine.getAllPlayers()) {
			message += player + "\n";
		}
		System.out.println(String.format("Final Player Results\n%s",message));
		mainFrame.getSummaryPanel().refreshSummaryWithResults(coinPair);
		
	}

}
