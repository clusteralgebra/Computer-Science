import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

//Logic behind the game
/**
 * 
 * @author Alan Yan
 *
    Any live cell with fewer than two live neighbors dies, as if by under population.
    Any live cell with two or three live neighbors lives on to the next generation.
    Any live cell with more than three live neighbors dies, as if by overpopulation.
    Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.

 */
public class Board extends JPanel{
	public static final int rows = 50;
	public static final int cols = 50;
	public static final int scale = 50;
	private int[][] state; // 1 - black, 0 - white
	private int generation;
	public Board(int x) {
		state = new int[rows][cols];
		init(x);
	}
	
	public void init(int x) { 
		generation = 0;
		
		for(int i = 0; i < rows; i++)
			for(int j = 0; j < cols; j++) 
				state[i][j] = gen(x);
	}
	
	public int getSpace(int i, int j) {
		return state[i][j];
	}
	
	public void nextIteration() { 
		int[] a = {1,-1, 1,-1, 0, 0, 1, -1};
		int[] b = {0, 0, 1, 1, 1, -1, -1, -1};
		int[][] nextState = new int[rows][cols];
		for(int i = 0; i < rows; i++) { 
			for(int j = 0; j < cols; j++) {
				int numNeighbors = 0;
				for(int k = 0; k < 8; k++) 
					if(state[modRow(i + a[k])][modCol(j + b[k])] == 1)
						numNeighbors++;
				if(numNeighbors < 2)
					nextState[i][j] = 0;
				else if((numNeighbors == 2 || numNeighbors == 3) && state[i][j] == 1)
					nextState[i][j] = 1;
				else if(numNeighbors > 3)
					nextState[i][j] = 0;
				else if(numNeighbors == 3 && state[i][j] == 0)
					nextState[i][j] = 1;
			}
		}
		state = nextState;
		generation++;
	}
	
	public int modRow(int x) { 
		return ((x % rows) + rows) % rows;
	}
	
	public int modCol(int x) { 
		return ((x % cols) + cols) % cols;
	}
	// B(x/rows*cols)
	public int gen(int x) { 
		int rand = (int) (Math.random() * (rows * cols));
		if(rand < x)
			return 1;
		return 0;
	}
	
	public void print() {
		for(int i = 0; i < rows; i++) { 
			for(int j = 0; j < cols; j++) 
				System.out.print(state[i][j] + " ");
			System.out.println();
		}
		System.out.println();
	}
	
	@Override
	public Dimension getPreferredSize() {
        return new Dimension(rows * scale, cols * scale);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Color gColor = g.getColor();
        
        g.setColor(Color.black);
        g.drawString("Generation: " + generation, 0, scale);
        for (int i = 0; i < rows; i++) 
        for (int j = 0; j < cols; j++) {
        	g.setColor(Color.gray);
        	if (state[i][j] == 1) 
        		g.setColor(Color.red);
            g.fillRect(j * scale, i * scale, scale, scale);
        }
        g.setColor(gColor);
        nextIteration();
    }
}
