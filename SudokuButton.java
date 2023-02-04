import java.awt.event.*;
import javax.swing.*;


public class SudokuButton extends JButton implements ActionListener{
	
	private Sudoku sudoku;
	public int row, col;
	public String binkan;
	
	public SudokuButton(Sudoku s,int r, int c) {
		sudoku = s;
		row=r;
		col=c;
		addActionListener(this);
		
	}	
	

	
	public void actionPerformed(ActionEvent e) {
		binkan = getText();
		if(binkan=="") {
			sudoku.row_col(row, col);
		}
	}
	
}