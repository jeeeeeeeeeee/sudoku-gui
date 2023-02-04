import java.util.*;

public class Sudoku {

	private int[][] solution = new int[9][9];
	private int hole_count;
	private int[][] puzzle_board = new int[9][9];
		
	private int row, col;
	
	public void row_col(int r, int c) {
		row = r;
		col = c;
	}
	

	public Sudoku(int count) {
		createSolutionBoard();
		hole_count = count;
		createPuzzleBoard(count);
	}
	
	public int[][] getPuzzleBoard(){
		return puzzle_board;
	}
	
	public int[][] getSolutionBoard(){
		return solution;
	}
	
	public int countHoles() {
		return hole_count;
	}
	
	private void createSolutionBoard() {
		int[] row = generateRandomPermutation(9);
		int k =0;
		for(int i=0; i<3; i++) {
			if(i==1) {k=3;}
			else if (i==2) {k=6;}
			for (int j=0;j<9;j++) {
				int a=k+j;
				if(a>=9) {a-=9;}
				solution[i][j]=row[a]+1;
			}
		}
		int a;
		for(int i=3; i<6; i++) {
			for(int j=0;j<9;j++) {
				if (j==2 || j==5 || j==8) {
					a=-2;
				}else a = 1;
				solution[i][j] = solution[i-3][j+a];
			}
		}
		int b;
		for(int i=6; i<9; i++) {
			for(int j=0;j<9;j++) {
				if (j==0 || j==3 || j==6) {
					b=2;
				}else b = -1;
				solution[i][j] = solution[i-6][j+b];
			}
		}
	
		shuffleRibbons();
		// 세로줄 바꾸기
		transpose();
		shuffleRibbons();
		transpose();
		// 테스트용 메소드
		showBoard(solution);
	}

	private int[] generateRandomPermutation(int n) {
		Random random = new Random();
		int[] permutation = new int[n];
		for (int i=0; i<n; i++) {
			int d = random.nextInt(i+1);
			permutation[i] = permutation[d];
			permutation[d] = i;
		}
		return permutation;
	}
	
	private void shuffleRibbons() {
		int[][] shuffled = new int[9][9];
		int[] random_index;
		for (int i = 0; i<3; i++) {
			random_index = generateRandomPermutation(3);
			for (int j=0; j<3; j++)
				shuffled[i*3 + random_index[j]] = solution[i*3+j];
		}
		solution = shuffled;
	}
	
	private void transpose() {
		int[][] transposed = new int[9][9];
		for (int i = 0; i<9; i++)
			for(int j = 0; j<9; j++)
				transposed[i][j] = solution[j][i];
		solution = transposed;
	}

	private void showBoard(int[][] b) {
		System.out.println("스도쿠 보드");
		for (int i= 0; i<9; i++) {
			for(int j = 0; j<9; j++)
				System.out.print(b[i][j] + " ");
			System.out.println();
		}
	}

	private void createPuzzleBoard(int count) {
		for (int i=0;i<9;i++) {
			for(int j=0;j<9;j++)
				puzzle_board[i][j] = solution[i][j];
		}
		while (count != 0) {
			int i = new Random().nextInt(9);
			int j = new Random().nextInt(9);
			if(puzzle_board[i][j] != 0) {
				puzzle_board[i][j] = 0;
				count --;
			}
		}
		
	}
	
	public boolean check(int digit) {
		
		if(puzzle_board[row][col] == 0 && digit == solution[row][col]) {
			puzzle_board[row][col] = digit;
			return true;
		}
		else {
			return false;
		}	
	}
}