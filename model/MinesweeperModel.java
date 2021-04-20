package model;

import java.util.Observable;

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

	/**
	 * 
	 * @param difficultySetting Integer representing difficulty level
	 * of game, which changes board size and number of mines.
	 */
	public MinesweeperModel(int difficultySetting) {
		
		//TODO: remove hardcoding
		time = 0;
		flagsLeft = TEST_MINE_COUNT;
		
		
		for (int i = 0; i < TEST_SIZE ; i++) {
			for (int j = 0; j < TEST_SIZE; j++) {
				
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
	 * Sends updated game-state to the view
	 * 
	 * @param mb A {@code MinesweeperBoard} {@code Object} that represents the most up-to-date game-state
	 */
	private void updateView(MinesweeperBoard mb) {
		setChanged();
		notifyObservers(mb);
	}
}