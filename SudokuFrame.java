import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class SudokuFrame extends JFrame {

    private Sudoku sudoku;
    private final int SIZE = 35;
    private final int PANEL_SIZE = SIZE * 10;
    private Level level;
    
    private SudokuButton[][] button_board;
    private Number[] number;
    
    public SudokuFrame(Sudoku s, Level l) {
    	
    	level = l;
        sudoku = s;
        button_board = new SudokuButton[9][9];
        number = new Number[9];
        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        
        JPanel p1 = new JPanel(new FlowLayout());
        int ll = level.level();
        if(ll == 36) {
        	p1.add(new JLabel("Easy"));
        }
        else if(ll ==45) {
        	p1.add(new JLabel("Medium"));
        }
        else
        	p1.add(new JLabel("Hard"));
        cp.add(p1,BorderLayout.NORTH);
        
        JPanel p2 = new JPanel(new GridLayout(9,9));
        for(int row = 0; row<9; row++)
        	for(int col = 0; col<9; col++) {
        		button_board[row][col] = new SudokuButton(sudoku, row, col);
        		p2.add(button_board[row][col]);
        	}
        cp.add(p2,BorderLayout.CENTER);
        
        JPanel p3 = new JPanel(new GridLayout());
        for(int i =0;i<9;i++) {
        	number[i] = new Number(Integer.toString(i+1),sudoku,this);
    	    p3.add(number[i]); 
        }
        cp.add(p3,BorderLayout.SOUTH);
        
        update();

        
        setTitle("Sudoku");
        setSize(PANEL_SIZE, PANEL_SIZE+40);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
   
    public void update() {
    	boolean on = true;
    	int[] count = {1,1,1,1,1,1,1,1,1};
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                int sp = sudoku.getPuzzleBoard()[row][col];
                if (sp != 0) {
                    String n = Integer.toString(sp);
                    button_board[row][col].setText(n);
//            		button_board[row][col].setEnabled(false);
                }
                else
                    button_board[row][col].setText("");
                for(int i = 1; i<10;i++) {
                	if(sp==i)
                		count[i-1] +=1;
                }
            }
        }
        for(int i = 0;i<9; i++) {
        	if(count[i]==10) {
        		number[i].setText("");
        		number[i].setEnabled(false);
        	}
        	else {
        		on = false;
        	}
        }
        if(on) {
        	for(int i =0;i<9;i++) {
        		number[i].setText(Integer.toString(i+1));
        		number[i].setEnabled(true);
        	}
        }
    }

}