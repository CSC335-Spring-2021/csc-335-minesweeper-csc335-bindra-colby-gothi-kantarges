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

	// TODO: Remove hardcoded testing board info

	final static int TEST_TIME  =  12;
	final static int TEST_SCORE = 300;

	/**
	 * 2D array of {@code Cell}s that represents game grid
	 */
	private Cell[][] grid;
	
	private HighScores highScores;

	private int time;
	private int score;
	
	private int flagsLeft;
	
	private int rows;
	private int cols;
	
	private int difficulty;
	private boolean firstClick;
	
	private boolean newGame;		// FIXME: ?

	/**
	 * Constructor for model
	 */
	public MinesweeperModel(Observer view) {
		this.addObserver(view);
	}

	/**
	 * Initializes grid.
	 * 
	 * Sets game-state to either blank or given saved-state
	 */
	public void initialize(MinesweeperBoard board, int difficulty, HighScores highScores) {

		if (board == null) {

			// FIXME: Change Default values for default game
			this.time  = TEST_TIME;
			this.score = TEST_SCORE;

			this.rows = calculateRows(difficulty);
			this.cols = calculateCols(difficulty);
			
			this.flagsLeft = calculateFlags(difficulty);

			this.difficulty = difficulty;
			this.firstClick = true;
			
			this.newGame = false;
			
			this.grid = new Cell[rows][cols];

			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {

					grid[i][j] = new Cell();
				}
			}

		} else {

			this.time  = board.getTime();
			this.score = board.getScore();

			this.flagsLeft = board.getFlagsLeft();

			this.rows = board.getRows();
			this.cols = board.getCols();

			this.difficulty = board.getDifficulty();
			this.firstClick = board.isFirstClick();
			
			// FIXME: what about newGame?

			this.grid = board.getBoard();
		}

		// Check for HighScores loaded file
		if (highScores == null) {
			this.highScores = new HighScores();
		} else {
			this.highScores = highScores;
		}

		updateView(this.getBoard());
	}

	/**
	 * Calculates columns for game based on difficulty
	 * 
	 * @param difficulty setting of game
	 * 
	 * @return number of columns for game
	 */
	public int calculateCols(int difficulty) {

		int cols = -1;
		
		switch (difficulty) {
		
			case Difficulty.EASY:
				cols = Difficulty.EASY_COL;
			break;
			
			case Difficulty.MEDIUM:
				cols = Difficulty.MEDIUM_COL;
			break;
			
			case Difficulty.EXPERT:
				cols = Difficulty.EXPERT_COL;
			break;
		}
		
		return cols;
	}

	/**
	 * Calculate rows for game based on difficulty
	 * 
	 * @param difficulty setting of game
	 * 
	 * @return number of columns for game
	 */
	public int calculateRows(int difficulty) {

		int rows = -1;
		
		switch (difficulty) {
		
			case Difficulty.EASY:
				rows = Difficulty.EASY_ROW;
			break;
			
			case Difficulty.MEDIUM:
				rows = Difficulty.MEDIUM_ROW;
			break;
			
			case Difficulty.EXPERT:
				rows = Difficulty.EXPERT_ROW;
			break;
		}
		
		return rows;
	}

	/**
	 * Calculate flags for game based on difficulty
	 * 
	 * @param difficulty setting of game
	 * 
	 * @return number of flags/mines for game
	 */
	private int calculateFlags(int difficulty) {
		return (int) Math.floor((this.rows * this.cols) * 0.15);
	}

	/**
	 * Returns the {@code Cell} at specified row/col
	 * 
	 * @param r row to query
	 * @param c col to query
	 * 
	 * @return Cell at row and col
	 */
	public Cell getCell(int r, int c) {
		return grid[r][c];
	}

	/**
	 * Checks to see if specified cell is in bounds
	 * 
	 * @param row of cell to check
	 * @param col of cell to check
	 * 
	 * @return {@code true} if in bounds, {@code false} otherwise
	 */
	public boolean isInBounds(int row, int col) {

		// FIXME: Remove hardcoding
		if (row >= this.rows || col >= this.cols) {
			return false;

		} else if (row < 0 || col < 0) {
			return false;

		} else {
			return true;
		}
	}

	/**
	 * reveals the {@code Cell} at the given position in the game-grid
	 * 
	 * @param r The row at which a flag needs to be placed/removed
	 * @param c The column at which a flag needs to be placed/removed
	 */
	public void revealCell(int r, int c) {

		grid[r][c].reveal();

		updateView(this.getBoard());
	}

	/**
	 * Checks to see if the {@code Cell} at the given position in the game-grid has
	 * a mine.
	 * 
	 * @param r The row at which a mine may or may not be
	 * @param c The column at which a mine may or may not be
	 * 
	 * @return {@code true} if contains a mine, {@code false} otherwise
	 */
	public boolean cellHasMine(int r, int c) {
		return grid[r][c].hasMine();
	}

	/**
	 * places/removes flag at the given position in the game-grid
	 * 
	 * @param r The row at which a flag needs to be placed/removed
	 * @param c The column at which a flag needs to be placed/removed
	 */
	public void toggleFlag(int r, int c) {

		if (flagsLeft > 0) {
			
			grid[r][c].flag();
			
			// if we placed a flag, decrement the number of flags left
			if (grid[r][c].hasFlag()) {
				
				this.flagsLeft = (flagsLeft > 0) ? flagsLeft-1 : 0;
			}
			
			// if we removed a flag, increment the number of flags left
			if (!grid[r][c].hasFlag()) {
				
				this.flagsLeft++;
			}
			
		} else if ((flagsLeft == 0) && grid[r][c].hasFlag()) {
			
			grid[r][c].flag();
			
			this.flagsLeft++;
		}
			
		updateView(this.getBoard());
	}
	
	public boolean hasFlag(int r, int c) {
		return grid[r][c].hasFlag();
	}

	public MinesweeperBoard getBoard() {
		return new MinesweeperBoard(this.grid, this.time, this.score, this.flagsLeft, this.rows, this.cols, this.difficulty, this.firstClick);
	}

	public boolean getIsFirstClick() {
		return this.firstClick;
	}

	public void changeFirstClick() {
		this.firstClick = false;
	}

	/**
	 * Sends updated game-state to the view
	 * 
	 * @param mb A {@code MinesweeperBoard} {@code Object} that represents the most
	 *           up-to-date game-state
	 */
	private void updateView(MinesweeperBoard mb) {
		setChanged();
		notifyObservers(mb);
	}

	/**
	 * Returns high score object
	 * 
	 * @return
	 */
	public HighScores getHighScores() {
		return highScores;
	}

	/**
	 * Return difficulty setting int of current model
	 * 
	 * @return Integer representation of difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	public int getCols() {
		return cols;
	}

	public int getRows() {
		return rows;
	}
}