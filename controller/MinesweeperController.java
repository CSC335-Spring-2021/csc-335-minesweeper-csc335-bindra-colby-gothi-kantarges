package controller;

import model.CellState;
import model.MinesweeperBoard;
import model.MinesweeperModel;

/**
 * Controller of MVC architecture.
 * 
 * View communicates user's interactions to this controller,
 * which in-turn calls methods that can change the model.
 * 
 * This controller is responsible for performing all kinds of
 * calculations/computations/checking, and making changes to the board.
 * 
 * @author Prabhkirat Singh Bindra
 * @author James Colby
 * @author Denson Selm Gothi
 * @author Joshua Paul Kantarges
 *
 */
public class MinesweeperController {

	/**
	 * the model that represents the game state internally
	 */
	MinesweeperModel model;
	
	/**
	 * Parameterized Constructor
	 * 
	 * @param model The {@code MinesweeperModel} that this controller is responsible for
	 */
	public MinesweeperController(MinesweeperModel model) {
		this.model = model;
	}

	/**
	 * Game logic for handling click on a cell
	 * 
	 * @param r row of cell clicked
	 * @param c col of cell clicked
	 */
	
	public void handleCellLeftClick(int r, int c) {
		
		model.changeCellModel(r, c);
	}

	/**
	 * Checks whether cell at row/col is revealed already
	 * 
	 * @param r row to check
	 * @param c col to check
	 * @return true is cell is revealed, else false
	 */
	public boolean isRevealedCell(int r, int c) {
		
		return model.getCell(r, c).isRevealed();
	}

	/**
	 * Gets the board from the model
	 * @return MinesweeperBoard
	 */
	public MinesweeperBoard getBoard(){
		return model.getBoard();
	}
}