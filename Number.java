import java.awt.event.*;
import javax.swing.*;

public class Number extends JButton implements ActionListener {
		
	private int getNumber=0;
	private Sudoku sudoku;
	private SudokuFrame frame;
	
	public Number(String i, Sudoku s, SudokuFrame f) {
		super(i);
		sudoku = s;
		frame = f;
		addActionListener(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		getNumber = Integer.parseInt(getText());
		sudoku.check(getNumber);
		frame.update();
	}
	
}