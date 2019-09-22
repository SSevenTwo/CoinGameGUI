package view;

import java.util.Collection;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import model.interfaces.Player;


public class SummaryTableModel extends AbstractTableModel{

	private List<Player> players;
	private String[] colNames = {"ID","Name","Points","Bet Type","Bet Amnt.",
			"Result"
	};
	
	public SummaryTableModel(Collection<Player> players) {
		this.players = (List<Player>) players;
	}

	// This grabs the column names. It will never be null as our colomn count is set
	@Override
	public String getColumnName(int column) {
		System.out.println(colNames[column]);
		return colNames[column];
	}

	@Override
	public int getColumnCount() {
		// Count how many columns we need and return it. Each attribute of our person.
		return 6;
	}

	@Override
	public int getRowCount() {
		// Number of rows is the equivalent of the number of people in db.
		return players.size();
	}

	@Override
	public Object getValueAt(int row, int col) {
		// Goes through each column and return what the value is
		Player player = players.get(row);
		
		switch(col) {
		case 0:
			return player.getPlayerId();
		case 1:
			return player.getPlayerName();
		case 2:
			return player.getPoints();
		case 3:
			return player.getBetType();
		case 4:
			return player.getBet();
		case 5:
			return player.getResult();
		}
		
		return null;
	}

}
