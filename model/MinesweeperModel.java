package model;

import java.util.Observable;
import java.util.Observer;

/**
 * Model class of the Minesweeper MVC architecture
 * 
 * TODO: brief description
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
@SuppressWarnings("deprecation")
public class MinesweeperModel extends Observable {
	
	//TODO: Remove hardcoded testing board size
	final static int TEST_SIZE = 8;
	final static int TEST_MINE_COUNT = 10;
	/**
	 * 2D array of Cell objects that represents game grid
	 */
	
	private Cell[][] grid;
	private int time;
	private int flagsLeft;
	private int rows;
	private int cols;
	private int difficulty;
	
	/**
	 * Constructor for model
	 * 
	 * Creates 
	 * @param difficultySetting Integer representing difficulty level
	 * of game, which changes board size and number of mines.
	 */
	public MinesweeperModel(int difficultySetting, Observer view) {
		
		//TODO: remove hardcoding
		this.time = 0;
		this.flagsLeft = TEST_MINE_COUNT;
		this.rows = TEST_SIZE;
		this.cols = TEST_SIZE;
		this.difficulty = difficultySetting;
		
		grid = new Cell[rows][cols];
		
		this.addObserver(view);
	}
	
	/**
	 * Initializes grid, sets all cells to starting blank state
	 */
	public void initialize() {
		
		for (int i = 0; i < rows ; i++) {
			for (int j = 0; j < cols; j++) {
				
				grid[i][j] = new Cell();
			}
		}
	}
	
	/**
	 * Checks to see if specified cell is in game bounds
	 * 
	 * @param row of cell to check
	 * @param col of cell to check
	 * @return true if in bounds, else false
	 */
	public boolean isInBounds(int row, int col) {
		//TODO: Remove hardcoding
		if (row >= TEST_SIZE || col >= TEST_SIZE) { return false; }
		if (row < 0 || col < 0) { return false; }
		
		return true;
	}
	
	/**
	 * Returns cell object at specified row/col
	 * @param r row to query
	 * @param c col to query
	 * @return Cell at row and col
	 */
	public Cell getCell(int r, int c) {
		return grid[r][c];
	}
	
	
	/**
	 * Sends updated game-state to the view
	 * 
	 * @param mb A {@code MinesweeperBoard} {@code Object} that represents the most up-to-date game-state
	 */
	private void updateView(MinesweeperBoard mb) {
		setChanged();
		notifyObservers(mb);
	}
}