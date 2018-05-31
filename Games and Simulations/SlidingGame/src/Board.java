import java.util.*;
public class Board {
	private static final int rows = 4;
	private static final int cols = 4;
			
	private Tile[][] state; // records the current state of the matrix
	public Board() { 
		state = new Tile[rows][cols];
		init();
	}
	public Tile getTile(int r, int c) {
		return state[r][c];
	}
	public void init() { 
		List<Tile> perm = new ArrayList<Tile>();
		for(int i = 0; i < rows * cols-1; i++)
			perm.add(new Tile(i/cols, i % cols, "" + (i+1)));
		Collections.shuffle(perm);
		for(int i = 0; i < perm.size(); i++)  
			state[i/cols][i % cols] = perm.get(i);
		state[rows-1][cols-1] = new Tile(rows-1, cols-1, " ");
	}
	
	// Move this tile into the empty square
	// Return true if you can, false if you can't
	public boolean moveTile(int r, int c) { 
		if(!isValidSquare(r, c))
			return false;
		if(isEmptySquare(r,c))
			return false;
		int[] rAdd = {1, -1, 0, 0};
		int[] cAdd = {0, 0, 1, -1};
		for(int i = 0; i < 4; i++) { 
			if(isEmptySquare(r + rAdd[i], c + cAdd[i])) { 
				swap(r, c, r+rAdd[i], c + cAdd[i]);
				return true;
			}	
		}
		return false;
		
		
	}
	
	public String getLabel(int r, int c) { 
		return state[r][c].getLabel();
	}
	// swaps tiles (r1, c1) and (r2, c2)
	public void swap(int r1, int c1, int r2, int c2) { 
		Tile temp = state[r1][c1];
		state[r1][c1] = state[r2][c2];
		state[r2][c2] = temp;
	}
	
	// checks if square is the empty one
	public boolean isEmptySquare(int r, int c) {
		if(!isValidSquare(r, c))
			return false;
		return state[r][c].getLabel().equals(" ");
	}
	// checks if a index is valid
	public boolean isValidSquare(int r, int c) { 
		return r >= 0 && r < rows && c >= 0 && c < cols;
	}
	// Checks to see if the game is over. 
	public boolean checkGameOver() { 
		for(int r = 0; r < rows; r++) 
			for(int c = 0; c < cols; c++) 
				if(!state[r][c].isInCorrectPosition(r, c))
					return false;		
		return true;
	}
	
	public void print() { 
		for(int i = 0; i < rows; i++) { 
			for(int j = 0; j < cols; j++) { 
				System.out.print(state[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
class Tile { 
	private int row, col; // the correct position
	private String label;
	
	public Tile(int row, int col, String label) { 
		this.row = row;
		this.col = col;
		this.label = label;
	}
	
	public int getRow() { 
		return row;
	}
	public int getCol() { 
		return col;
	}
	public String getLabel() { 
		return label;
	}
	
	public boolean isInCorrectPosition(int r, int c) { 
		return row == r && col == c;
	}
	public String toString() { 
		return label;
	}
}
