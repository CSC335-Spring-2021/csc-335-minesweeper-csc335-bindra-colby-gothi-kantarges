package model;

import java.io.Serializable;

/**
 *  Class representing each cell in main game grid. 
 *  
 *  Keeps track of info about cell's state,
 *  and is able to mutate this state.
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
public class Cell implements Serializable {
	
	/**
	 * generated serial version ID
	 */
	private static final long serialVersionUID = 5561380671575047521L;
	
	private boolean revealed;
	private boolean flagged;
	private boolean mined;
	
	private int neighborMines;
	
	/**
	 * Default Constructor
	 * 
	 * Initially, each {@code Cell} is unmined, unrevealed, and unflagged.
	 */
	public Cell() {
		this.revealed  = false;
		this.flagged   = false;
		this.mined     = false;
		this.neighborMines = 0;
	}

	/**
	 * Sets this {@code Cell}'s status to revealed
	 */
	public void reveal() {
		revealed = true;
	}
	
	/**
	 * Getter that checks if {@code Cell} is revealed
	 * 
	 * @return {@code true} if revealed
	 */
	public boolean isRevealed() {
		return revealed;
	}
	
	/**
	 * Adds mine to this {@code Cell}
	 */
	public void addMine() {
		this.mined = true;
	}
	
	/**
	 * Checks to see if this {@code Cell} contains mine
	 * 
	 * @return {@code true} if {@code Cell} has mine, {@code false} otherwise
	 */
	public boolean hasMine() {
		return mined;
	}
	
	/**
	 * Checks to see if this {@code Cell} is flagged
	 * 
	 * @return {@code true} if this {@code Cell} is flagged
	 */
	public boolean hasFlag() {
		return flagged;
	}

	/**
	 * Adds flag to this {@code Cell} if it is unrevealed.
	 * OR
	 * If this {@code Cell} already has flag, removes it.
	 * 
	 * Does nothing if this {@code Cell} is already revealed.
	 */
	public void flag() {
		if (!revealed) {
			flagged = !flagged;
		}
	}
	
	/**
	 * Gets number of mines around this {@code Cell}.
	 * 
	 * @return number of neighboring mines
	 */
	public int getNeighbors() {
		return neighborMines;
	}
	
	/**
	 * Sets number of mines surrounding this this {@code Cell}.
	 * 
	 * @param neighborCount The number of mines around this {@code Cell}
	 */
	public void setNeighbors(int neighborCount) {
		this.neighborMines = neighborCount;
	}
}