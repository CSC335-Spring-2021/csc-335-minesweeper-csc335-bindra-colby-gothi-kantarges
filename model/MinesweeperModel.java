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
	final static int TEST_TIME = 12;
	final static int TEST_DIFFICULTY = 0;
	/**
	 * 2D array of Cell objects that represents game grid
	 */
	
	private Cell[][] grid;
	private int time;
	private int flagsLeft;
	private int rows;
	private int cols;
	private int difficulty;
	private boolean firstClick;
	
	/**
	 * Constructor for model
	 */
	public MinesweeperModel(Observer view) {

		//TODO: Change Default values for default game
		this.time = TEST_TIME;
		this.flagsLeft = TEST_MINE_COUNT;
		this.rows = TEST_SIZE;
		this.cols = TEST_SIZE;
		this.difficulty = TEST_DIFFICULTY;
		this.firstClick = true;
		
		grid = new Cell[rows][cols];
		
		this.initialize();
		
		this.addObserver(view);
	}

	/**
	 * Constructor for when there is a saved board
	 * @param board saved board
	 * @param view GUI view
	 */
	public MinesweeperModel(MinesweeperBoard board, Observer view){
		this.grid = board.getBoard();
		this.time = board.getTime();
		this.flagsLeft = board.getFlagsLeft();
		this.rows = board.getRows();
		this.cols = board.getCols();
		this.difficulty = board.getDifficulty();
		this.firstClick = board.isFirstClick();
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
	
	// Dummy change cell to revealed method to test observer hookup
	// FIXME: implement real change method
	public void changeCellModel(int r, int c) {
		
		grid[r][c].reveal();
		
		updateView(new MinesweeperBoard(grid));
	}

	public MinesweeperBoard getBoard(){
		return new MinesweeperBoard(grid,this.time,this.flagsLeft,this.rows,this.cols,this.difficulty,this.firstClick);
	}

	public boolean getIsFirstClick(){
		return this.firstClick;
	}

	public void changeFirstClick(){
		this.firstClick = false;
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