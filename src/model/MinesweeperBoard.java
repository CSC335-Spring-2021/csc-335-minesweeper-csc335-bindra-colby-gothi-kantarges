package model;

import java.io.Serializable;

/**
 * Class that can hold the state of the game board and is {@code Serializable}
 *
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
public class MinesweeperBoard implements Serializable {

	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = -4510255117516896736L;

	private Cell[][] boardArrToPass;
	
	private int time;
	private int score;
	
	private int flagsLeft;
	
	private int rows;
	private int cols;
	
	private int difficulty;
	private boolean firstClick;

	private int gameState;

	/**
	 * Constructor for {@code MinesweeperBoard} save game
	 *
	 * Accepts model's array of cell objects and creates
	 * a packaged version to pass to the view
	 *
	 * @param passedBoard Array of current cell states
	 * @param time        left in game
	 * @param flagsLeft   to place for scoreboard
	 * @param rows        of current board
	 * @param cols        of current board
	 * @param difficulty  of current game
	 */
	public MinesweeperBoard(Cell[][] passedBoard, int time, int score, int flagsLeft, int rows, int cols, int difficulty, boolean firstClick, int gameState) {
		
		this.boardArrToPass = passedBoard;
		
		this.time  = time;
		this.score = score;
		
		this.flagsLeft = flagsLeft;
		
		this.rows = rows;
		this.cols = cols;
		
		this.difficulty = difficulty;
		this.firstClick = firstClick;
		
		this.gameState = gameState;
	}

	/**
	 * Getter for the current state of board
	 *
	 * @return {@code Cell} array that represents the state
	 */
	public Cell[][] getBoard() {
		return boardArrToPass;
	}

	/**
	 * Getter for time
	 * 
	 * @return int time
	 */
	public int getTime() {
		return time;
	}

	/**
	 * Getter for score
	 * 
	 * @return int score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Getter for cols
	 * 
	 * @return int cols
	 */
	public int getCols() {
		return cols;
	}

	/**
	 * Getter for rows
	 * 
	 * @return int rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * Getter for difficulty
	 * 
	 * @return int difficulty
	 */
	public int getDifficulty() {
		return difficulty;
	}

	/**
	 * Getter for flags
	 * 
	 * @return int flags
	 */
	public int getFlagsLeft() {
		return flagsLeft;
	}

	/**
	 * Getter for state of gameplay 
	 * 
	 * @return integer representation of play state
	 */
	public int getGameState() {
		return gameState;
	}
	
	/**
	 * Getter for whether it is the first click of the game
	 * 
	 * @return true or false
	 */
	public boolean isFirstClick() {
		return this.firstClick;
	}
}