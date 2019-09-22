package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import model.interfaces.GameEngine;

public class SummaryPanel extends JPanel{
	private JTable table;
	private SummaryTableModel tableModel;
	
	public SummaryPanel(GameEngine gameEngine) {
		tableModel = new SummaryTableModel(gameEngine.getAllPlayers());
		table = new JTable(tableModel);
		
		add(new JScrollPane(table),BorderLayout.CENTER);
	}
	
	public void refreshTable() {
		tableModel.fireTableDataChanged();
	}
}
