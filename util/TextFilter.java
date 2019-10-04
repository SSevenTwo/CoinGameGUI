package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import view.MainFrame;

public class TextFilter extends DocumentFilter {

	private MainFrame mainFrame;

	public TextFilter(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}
	
	@Override
	public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
		super.remove(fb, offset, length);
		//Not needed
	}

	@Override
	public void insertString(FilterBypass fb, int offset, String text, AttributeSet attr) throws BadLocationException {
		StringBuilder sb = new StringBuilder();
		sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.insert(offset, text);
		if (!containsOnlyNumbers(sb.toString())) {
			JOptionPane.showMessageDialog(mainFrame, "Bet's must be numbers only!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		fb.insertString(offset, text, attr);
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attr)
			throws BadLocationException {
		StringBuilder sb = new StringBuilder();
		sb.append(fb.getDocument().getText(0, fb.getDocument().getLength()));
		sb.replace(offset, offset + length, text);
		if (!containsOnlyNumbers(sb.toString())) {
			JOptionPane.showMessageDialog(mainFrame, "Points must be numbers only!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		fb.replace(offset, length, text, attr);
	}

	public boolean containsOnlyNumbers(String text) {
		Pattern pattern = Pattern.compile("\\d*(\\.\\d{0,3})?");
		Matcher matcher = pattern.matcher(text);
		boolean isMatch = matcher.matches();
		return isMatch;
	}

}
