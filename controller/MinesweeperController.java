package controller;

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
	
	// TODO: implement controller class
}