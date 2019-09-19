package view;

import model.interfaces.Coin;
import model.interfaces.CoinPair;
import model.interfaces.GameEngine;
import model.interfaces.Player;
import model.util.Utilities;
import view.interfaces.GameEngineCallback;

public class GameEngineCallbackGUI implements GameEngineCallback{
	
	MainFrame mainFrame;
	
	public GameEngineCallbackGUI(GameEngine gameEngine) {
		this.mainFrame = new MainFrame(gameEngine);
	}

	@Override
	public void playerCoinUpdate(Player player, Coin coin, GameEngine engine) {
		System.out.println(String.format("%s coin %d flipped to %s", player.getPlayerName(),coin.getNumber()
				,Utilities.titleConvert(coin.getFace())));
		CoinPanel coinPanel = mainFrame.getCoinPanel();
		SpinPlayerForm spinPanel = mainFrame.getSpinPlayerForm();
		coinPanel.update(coin);
	}

	@Override
	public void spinnerCoinUpdate(Coin coin, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void playerResult(Player player, CoinPair coinPair, GameEngine engine) {
		System.out.println(String.format("%s , final result: %s" ,player.getPlayerName(),coinPair));
		
	}

	@Override
	public void spinnerResult(CoinPair coinPair, GameEngine engine) {
		// TODO Auto-generated method stub
		
	}

}
