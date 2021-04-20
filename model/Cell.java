package model;

/**
 *  Class representing each cell in main game grid. 
 *  
 *  Keeps track of info about cell's state, and is able to mutate
 *  this state. 
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */

public class Cell {
	
	private boolean revealed;
	private boolean flagged;
	private boolean mined;
	
	/**
	 * Constructor for cell.
	 * 
	 * Initially each cell is set to be unmined, unrevealed, and unflagged.
	 */
	public Cell() {
		
		this.revealed = false;
		this.flagged = false;
		this.mined = false;
	}
	
	/**
	 * Sets cell status to revealed
	 */
	public void reveal() {
		
		revealed = true;
	}
	
	/**
	 * Adds mine to cell
	 */
	public void addMine() {
		
		this.mined = true;
	}
	
	/**
	 * Checks to see if cell contains mine
	 * @return boolean true if cell has mine, else false
	 */
	public boolean hasMine() {
		
		return mined;
	}
	
	/**
	 * Adds flag to an unrevealed cell. If cell already has flag,
	 * removes it. If cell is revealed, do nothing.
	 */
	public void flag() {
		
		if (!revealed && !flagged) {
			
			flagged = true;
		}
		
		if (!revealed && flagged) {
			
			flagged = false;
		}
	}
	
	
	

}
