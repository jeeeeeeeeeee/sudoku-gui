import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Level extends JFrame implements ActionListener {

	private int level;
	private JButton easy, medium, hard;
	
	public Level() {
        Container cp = getContentPane();
        cp.setLayout(new FlowLayout());
        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");
        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);
        cp.add(easy); cp.add(medium); cp.add(hard);
        setTitle("Select Level");
        setSize(300, 65);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	
	public int level() {
		return level;
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == easy)
			level = 36;
		else if(e.getSource()==medium)
			level = 45;
		else
			level = 54;
		new SudokuFrame(new Sudoku(level), this);
		dispose();
	}
}
